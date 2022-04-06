package com.ideal.chemical.pricing.writer;

import com.ideal.chemical.pricing.model.PricingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class PricingModelWriter<PricingModel> implements ItemWriter<PricingModel> {
    @Override
    public void write(List<? extends PricingModel> list) throws Exception {
        log.info("Writing");
    }
}
