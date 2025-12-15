package com.example.sharebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RentalOfferController {
 final

    @GetMapping
    public void addRentalOffer(@RequestParam String accountId, @RequestParam int carIdx,
                               @RequestParam int rentalPrice, @RequestParam String description) {

    }
}
