package com.pfe22.ava.batch;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


    @Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    @Autowired private AvaReader avaReader;
    @Autowired private AvaItemWriter avaItemWriter;
    @Autowired private AvaItemProcessor avaItemProcessor;




   // @Bean
    public Job avaJob(){
       return jobBuilderFactory.get("my-job")
               .incrementer(new RunIdIncrementer())
               .flow(createStep()).end().build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("my-step")
                .<Ava,Ava>chunk(100)
                .reader(avaReader)
                .processor(avaItemProcessor)
                .writer(avaItemWriter)
                .build();

    }




}
