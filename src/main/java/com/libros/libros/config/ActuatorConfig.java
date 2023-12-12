package com.libros.libros.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {

    @Bean
    public InfoContributor customInfoContributor() {
        return info -> info.withDetail("customKey", "customValue");
    }
}
