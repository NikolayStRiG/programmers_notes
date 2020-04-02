package org.sterzhen.programmers_notes.rest_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;
import org.sterzhen.programmers_notes.core.services.InfoResourceService;
import org.sterzhen.programmers_notes.core.services.InfoResourceServiceImpl;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;
import org.sterzhen.programmers_notes.rest_service.dto.InfoResourceDto;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public InfoResourceService infoResourceService(InfoResourceRepository infoResourceRepository) {
        return new InfoResourceServiceImpl(infoResourceRepository);
    }

    @PostConstruct
    public void init() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(InfoResourceApi.class, InfoResourceDto.class);
        objectMapper.registerModule(simpleModule);
    }

}
