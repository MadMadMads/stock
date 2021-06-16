package com.trade.tiger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: Luo
 * @description:
 * @time: 2021/6/12 19:41
 * Modified By:
 */
@Configuration

    public class WebMvcConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOriginPatterns("*") // SpringBoot2.4.0 [allowedOriginPatterns]代替[allowedOrigins]
                    .allowedMethods("*")
                    .maxAge(3600)
                    .allowCredentials(true);
        }
}