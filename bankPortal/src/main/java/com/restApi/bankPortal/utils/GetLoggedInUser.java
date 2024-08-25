package com.restApi.bankPortal.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@Slf4j
public class GetLoggedInUser {


//    @Bean
//    public UserDetails getLoggedInUserDetails(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication.getPrincipal() instanceof UserDetails && authentication.isAuthenticated())
//            return (UserDetails) authentication.getPrincipal();
//        return null;
//    }

}


