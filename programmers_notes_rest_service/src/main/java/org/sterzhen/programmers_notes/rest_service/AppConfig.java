package org.sterzhen.programmers_notes.rest_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;
import org.sterzhen.programmers_notes.core.services.InfoResourceService;
import org.sterzhen.programmers_notes.core.services.InfoResourceServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public InfoResourceService infoResourceService(InfoResourceRepository infoResourceRepository) {
        return new InfoResourceServiceImpl(infoResourceRepository);
    }
}
