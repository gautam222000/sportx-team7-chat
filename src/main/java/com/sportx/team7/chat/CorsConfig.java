package com.sportx.team7.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")// all endpoints
                .allowedOrigins("http://localhost:3000")// your React app
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*");
    }
}
