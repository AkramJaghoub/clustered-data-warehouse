package com.progressoft.FX.Deals.Data.Warehouse.exceptions;

public class SameCurrenciesException extends RuntimeException{
    public SameCurrenciesException(){
        super("Both the 'from' and 'to' currencies are the same");
    }
}
