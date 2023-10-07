package com.progressoft.FX.Deals.Data.Warehouse.service;

import com.progressoft.FX.Deals.Data.Warehouse.dto.FXDealDto;
import com.progressoft.FX.Deals.Data.Warehouse.exceptions.DealAlreadyExistsException;
import com.progressoft.FX.Deals.Data.Warehouse.exceptions.NoDealsFoundException;
import com.progressoft.FX.Deals.Data.Warehouse.model.ApiResponse;
import com.progressoft.FX.Deals.Data.Warehouse.model.FXDeal;
import com.progressoft.FX.Deals.Data.Warehouse.repository.FXDealRepository;
import com.progressoft.FX.Deals.Data.Warehouse.util.CurrencyValidatorUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class FXDealService {

    private final Logger logger = LoggerFactory.getLogger(FXDealService.class);

    private final FXDealRepository dealRepository;

    @Autowired
    public FXDealService(FXDealRepository dealRepository){
        this.dealRepository = dealRepository;
    }

    public ApiResponse saveDeal(FXDealDto fxDealDto) {
        FXDeal fxDeal = new FXDeal().toFXDeal(fxDealDto);
        long dealId = fxDeal.getDealId();
        String fromCurrency = fxDeal.getFromCurrency();
        String toCurrency = fxDeal.getToCurrency();

        logger.info("Validating currencies for deal with ID {}: fromCurrencyCode={}, toCurrencyCode={}", dealId, fromCurrency, toCurrency);
        // validate if currency is the same or valid (one of the existing ISO codes)
        CurrencyValidatorUtil.validateCurrencyCode(fromCurrency, toCurrency);
        logger.info("The currencies for deal with ID {}: fromCurrencyCode={}, toCurrencyCode={} are valid", dealId, fromCurrency, toCurrency);

        logger.info("Trying to save deal with ID {} into the database", dealId);
        if (dealRepository.existsById(dealId)) {
            logger.error("Deal with ID {} already exists try again", dealId);
            throw new DealAlreadyExistsException();
        }

        try {
            dealRepository.save(fxDeal);
        } catch (Exception e) {
            logger.error("Error while saving the deal: " + e.getMessage(), e);
            throw e;
        }

        logger.info("Deal with ID {} has been saved successfully", fxDealDto.getDealId());
        return new ApiResponse("Deal with ID " + dealId + " has been saved successfully", HttpStatus.CREATED);
    }

    public ApiResponse getAllDeals() {
        List<FXDeal> allDeals = dealRepository.findAll();

        if (allDeals.isEmpty()) {
            logger.error("No deals found");
            throw new NoDealsFoundException();
        }

        logger.info("Retrieved {} deals", allDeals.size());
        return new ApiResponse("Deals retrieved successfully", HttpStatus.OK, allDeals);
    }


    public ApiResponse getDealByID(long dealId) {
        Optional<FXDeal> fxDeal = dealRepository.findById(dealId);

        if (fxDeal.isEmpty()) {
            logger.error("Deal with ID {} was not found", dealId);
            throw new NoDealsFoundException();
        }

        logger.info("Deal with ID {} was retrieved successfully", dealId);
        return new ApiResponse("Deal with ID " + dealId + " has been retrieved successfully", HttpStatus.OK, fxDeal);
    }

}