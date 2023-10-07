package com.progressoft.FX.Deals.Data.Warehouse.controller;

import com.progressoft.FX.Deals.Data.Warehouse.dto.FXDealDto;
import com.progressoft.FX.Deals.Data.Warehouse.model.ApiResponse;
import com.progressoft.FX.Deals.Data.Warehouse.service.FXDealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class FXDealController {
    private final FXDealService fxDealService;

    @Autowired
    public FXDealController(FXDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping("/save-deal")
    public ResponseEntity<ApiResponse> saveDeal(@Valid @RequestBody FXDealDto fxDealDto) {
        ApiResponse response = fxDealService.saveDeal(fxDealDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all-deals")
    public ResponseEntity<ApiResponse> getAllDeals() {
        ApiResponse response = fxDealService.getAllDeals();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/fetch-deal/{deal_id}")
    public ResponseEntity<ApiResponse> getDealByID(@PathVariable("deal_id") long dealId) {
        ApiResponse response = fxDealService.getDealByID(dealId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}