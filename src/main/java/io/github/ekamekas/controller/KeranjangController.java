package io.github.ekamekas.controller;

import io.github.ekamekas.service.KeranjangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("v1/keranjang")
public class KeranjangController {

    @Autowired
    private KeranjangService service;

    @GetMapping("/payment/inquiry")
    public ResponseEntity inquiryPayment(@RequestParam("cart") Double cartPrice){
        return ResponseEntity.ok(
                service.payment(cartPrice, Instant.now().toEpochMilli())
        );
    }
}