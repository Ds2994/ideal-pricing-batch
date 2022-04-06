package com.ideal.chemical.pricing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricingModel {

    private String productCode;
    private String productName;
    private String hsn;
    private String pack;
    private String price;
}
