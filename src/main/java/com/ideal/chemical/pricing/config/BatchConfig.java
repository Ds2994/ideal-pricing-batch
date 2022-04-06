package com.ideal.chemical.pricing.config;

import com.ideal.chemical.pricing.model.PricingModel;
import com.ideal.chemical.pricing.processor.PricingModelProcessor;
import com.ideal.chemical.pricing.writer.PricingModelWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<PricingModel> reader() {
        return new FlatFileItemReaderBuilder<PricingModel>()
                .name("pricingModelItemReader")
                .resource(new ClassPathResource("product-price-list-7.csv"))
                .delimited()
                .names(new String[]{"productCode", "productName", "hsn", "pack", "price"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<PricingModel>() {{
                    setTargetType(PricingModel.class);
                }})
                .build();
    }

    @Bean
    public PricingModelProcessor processor() {
        return new PricingModelProcessor();
    }

    @Bean
    public PricingModelWriter<PricingModel> writer() {
        return new PricingModelWriter();
    }

    @Bean
    public Job job(Step step1) {
        return jobBuilderFactory.get("Pricing Import Job")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step(PricingModelWriter<PricingModel> writer) {
        return stepBuilderFactory.get("Pricing Import Step")
                .<PricingModel, PricingModel> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
