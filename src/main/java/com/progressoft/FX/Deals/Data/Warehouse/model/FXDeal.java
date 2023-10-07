package com.progressoft.FX.Deals.Data.Warehouse.model;

import com.progressoft.FX.Deals.Data.Warehouse.dto.FXDealDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fx_deals")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FXDeal {
    @Id
    private long dealId;
    private String fromCurrency;
    private String toCurrency;
    private String dealTimestamp;
    private double dealAmount;

    public FXDeal toFXDeal(FXDealDto fxDealDto) {
        return FXDeal.builder()
                .dealId(fxDealDto.getDealId())
                .fromCurrency(fxDealDto.getFromCurrency().toUpperCase())
                .toCurrency(fxDealDto.getToCurrency().toUpperCase())
                .dealTimestamp(fxDealDto.getDealTimestamp())
                .dealAmount(fxDealDto.getDealAmount())
                .build();
    }
}
