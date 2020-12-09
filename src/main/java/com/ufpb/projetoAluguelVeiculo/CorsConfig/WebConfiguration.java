package com.ufpb.projetoAluguelVeiculo.CorsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    
    @Bean(name = "pathLojas")
    public String pathLojas() {
        return new String();
    }

    @Bean(name = "pathVeiculos")
    public String pathVeiculos() {
        return new String();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }
}
