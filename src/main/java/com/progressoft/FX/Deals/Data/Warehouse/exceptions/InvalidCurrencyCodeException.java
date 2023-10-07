package com.progressoft.FX.Deals.Data.Warehouse.exceptions;

public class InvalidCurrencyCodeException extends RuntimeException{
    public InvalidCurrencyCodeException(){
        super("Currency Code Is Not Valid");
    }
}
