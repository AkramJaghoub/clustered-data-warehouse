package com.progressoft.FX.Deals.Data.Warehouse.exceptions;

public class DealAlreadyExistsException extends RuntimeException{
    public DealAlreadyExistsException(){
        super("Deal Already Exists");
    }
}