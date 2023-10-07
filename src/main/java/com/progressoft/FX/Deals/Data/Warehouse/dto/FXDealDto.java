package com.progressoft.FX.Deals.Data.Warehouse.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class FXDealDto {

    @NotNull(message = "Deal ID must be provided")
    @Min(value = 1, message = "Deal ID must be greater than 0")
    private long dealId;

    @NotBlank(message = "From currency ISO code must not be null")
    private String fromCurrency;

    @NotBlank(message = "To currency ISO code must not be null")
    private String toCurrency;

    @NotBlank(message = "Deal timestamp must be provided")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$",
            message = "Deal timestamp must be in the format 'yyyy-MM-ddTHH:mm:ss'"
    )
    private String dealTimestamp;

    @NotNull(message = "Deal Amount must not be null")
    @DecimalMin(value = "0.0", message = "Deal amount must be greater than 0")
    private double dealAmount;
}