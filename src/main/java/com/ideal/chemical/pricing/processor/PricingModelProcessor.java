package com.ideal.chemical.pricing.processor;

import com.ideal.chemical.pricing.model.PricingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PricingModelProcessor implements ItemProcessor<PricingModel, PricingModel> {
    @Override
    public PricingModel process(PricingModel pricingModel) throws Exception {
        log.info("Received - "+ pricingModel);
        return pricingModel;
    }
}
