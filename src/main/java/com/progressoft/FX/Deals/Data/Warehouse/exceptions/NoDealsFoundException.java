package com.progressoft.FX.Deals.Data.Warehouse.exceptions;

public class NoDealsFoundException extends RuntimeException {
    public NoDealsFoundException(){
        super("No deals found");
    }
}
