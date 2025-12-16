package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Car;
import com.example.sharebackend.domain.CarImg;
import com.example.sharebackend.domain.RentalOffer;
import com.example.sharebackend.mapper.RentalOfferMapper;
import com.example.sharebackend.request.RentalOfferAddRequest;
import com.example.sharebackend.response.*;
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
@Transactional
public class RentalOfferController {
    final RentalOfferMapper rentalOfferMapper;

    @PostMapping("/rental-offer")
    public RentalOfferAddResponse addRentalOffer(@ModelAttribute RentalOfferAddRequest rentalOfferAddRequest,
                                                 @RequestAttribute String currentAccountId,
                                                 @RequestAttribute String currentNickName) throws IOException {

        RentalOffer rentalOffer = new RentalOffer();
        rentalOffer.setAccountId(currentAccountId);
        rentalOffer.setCarIdx(rentalOfferAddRequest.getCarIdx());
        rentalOffer.setRentalPrice(rentalOfferAddRequest.getRentalPrice());
        rentalOffer.setDescription(rentalOfferAddRequest.getDescription());

        int rentalOfferInsertResult = rentalOfferMapper.insertRentalOffer(rentalOffer);

        if (rentalOfferInsertResult != 1) {
            return RentalOfferAddResponse.builder().success(false).message("렌탈 오퍼 DB 저장에 실패했습니다.").build();
        }

        int generatedRentalOfferIdx = rentalOffer.getIdx();

        List<MultipartFile> images = rentalOfferAddRequest.getImg();
        if (images == null || images.isEmpty()) {
            return RentalOfferAddResponse.builder().success(false).message("하나 이상의 이미지를 업로드해야 합니다.").build();
        }
        if (images.size() > 5) {
            return RentalOfferAddResponse.builder().success(false).message("최대 5장의 이미지만 업로드할 수 있습니다.").build();
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
                    return RentalOfferAddResponse.builder().success(false).message("이미지 파일만 업로드 가능합니다.").build();
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
                    return RentalOfferAddResponse.builder().success(false).message("차량 이미지 DB 저장에 실패했습니다.").build();
                }
            }
        }

        List<CarImg> carImages = rentalOfferMapper.findCarImgs(generatedRentalOfferIdx);

        // --- 성공 시 ---
        return RentalOfferAddResponse.builder()
                .success(true)
                .message("렌탈 매물이 성공적으로 등록되었습니다.")
                .registeredRentalOfferIdx(generatedRentalOfferIdx)
                .rentalOffer(rentalOffer)
                .carImg(carImages)
                .build();
    }

    @GetMapping("/rental-offer")
    public RentalOfferListResponse rentalOfferInfoHandle() {

        List<RentalOfferResponse> rentalOffersWithImages = rentalOfferMapper.findAllRentalOffersWithImages();
        int count = rentalOfferMapper.countAllRentalOffer();

        return RentalOfferListResponse.builder()
                .success(true).rentalOfferResponseList(rentalOffersWithImages)
                .countAllRentalOffer(count).build();
    }
}
