package com.progressoft.FX.Deals.Data.Warehouse.util;

import com.progressoft.FX.Deals.Data.Warehouse.exceptions.InvalidCurrencyCodeException;
import com.progressoft.FX.Deals.Data.Warehouse.exceptions.SameCurrenciesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Currency;

public final class CurrencyValidatorUtil {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyValidatorUtil.class);

    private CurrencyValidatorUtil() {}

    public static void validateCurrencyCode(String fromCurrencyCode, String toCurrencyCode) {

        if (isValidCurrencyCode(fromCurrencyCode) || isValidCurrencyCode(toCurrencyCode)) {
            logger.error("Invalid currency code detected: fromCurrencyCode={}, toCurrencyCode={}", fromCurrencyCode, toCurrencyCode);
            throw new InvalidCurrencyCodeException();
        }

        if (fromCurrencyCode.equals(toCurrencyCode)) {
            logger.error("Same currencies detected: fromCurrencyCode={}, toCurrencyCode={}", fromCurrencyCode, toCurrencyCode);
            throw new SameCurrenciesException();
        }
    }

    public static boolean isValidCurrencyCode(String currencyCode) {
        try {
            Currency.getInstance(currencyCode);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}