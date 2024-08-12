package com.restApi.bankPortal.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class GenerateUUID {


    @Bean
    public String generateCustomerID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    @Bean
    public Long generateRandomNumber(){
      return (long) ThreadLocalRandom.current().nextInt(10000000, 100000000);
    }
}
