package com.example.sharebackend.controller;

import com.example.sharebackend.domain.CarImg;
import com.example.sharebackend.domain.RentalOffer;
import com.example.sharebackend.mapper.RentalOfferMapper;
import com.example.sharebackend.request.RentalOfferAddRequest;
import com.example.sharebackend.response.RentalOfferAddResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RentalOfferController {
    final RentalOfferMapper rentalOfferMapper;

    @Transactional
    @PostMapping("/rental-offer")
    public RentalOfferAddResponse addRentalOffer(@ModelAttribute RentalOfferAddRequest rentalOfferAddRequest,
                                                 @RequestAttribute String currentAccountId) throws IOException {

        RentalOffer rentalOffer = new RentalOffer();
        rentalOffer.setAccountId(currentAccountId);
        rentalOffer.setCarIdx(rentalOfferAddRequest.getCarIdx());
        rentalOffer.setRentalPrice(rentalOfferAddRequest.getRentalPrice());
        rentalOffer.setDescription(rentalOfferAddRequest.getDescription());

        int rentalOfferInsertResult = rentalOfferMapper.insertRentalOffer(rentalOffer);

        if (rentalOfferInsertResult != 1) {
            throw new IllegalStateException("렌탈 오퍼 DB 저장에 실패했습니다.");
        }

        int generatedRentalOfferIdx = rentalOffer.getIdx();

        List<MultipartFile> images = rentalOfferAddRequest.getImg();
        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("하나 이상의 이미지를 업로드해야 합니다.");
        }

        // 고정된 베이스 경로 설정
        Path baseUploadPath = Path.of(System.getProperty("user.home"), "share", "car-images");
        Files.createDirectories(baseUploadPath);

        String folderUuid = UUID.randomUUID().toString();
        Path specificUploadPath = baseUploadPath.resolve(folderUuid);
        Files.createDirectories(specificUploadPath);

        for (MultipartFile file : images) {
            if (!file.isEmpty()) {
                if (file.getContentType() == null || !file.getContentType().startsWith("image")) {
                    throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
                }
                String originalFilename = file.getOriginalFilename();
                String fileExtension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
                Path filePath = specificUploadPath.resolve(uniqueFileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                CarImg carImg = new CarImg();
                carImg.setRentalOfferIdx(generatedRentalOfferIdx);
                carImg.setImg(folderUuid + "/" + uniqueFileName);

                int carImgInsertResult = rentalOfferMapper.insertCarImg(carImg);
                if (carImgInsertResult != 1) {
                    throw new IllegalStateException("차량 이미지 DB 저장에 실패했습니다.");
                }
            }
        }

        // --- 성공 시 ---
        return RentalOfferAddResponse.builder()
                .success(true)
                .message("렌탈 매물이 성공적으로 등록되었습니다.")
                .registeredRentalOfferIdx(generatedRentalOfferIdx)
                .rentalOffer(rentalOffer)
                .build();
    }
}
