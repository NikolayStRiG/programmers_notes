package org.sterzhen.programmers_notes.fx_ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sterzhen.programmers_notes.fx_ui.dto.InfoResourceDto;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceServiceApi;
import org.sterzhen.rest.client.RestClientFactory;

@Configuration
public class DesktopConfiguration {
    private static System.Logger logger = System.getLogger(DesktopConfiguration.class.getName());

    @Bean
    public InfoResourceServiceApi infoResourceServiceApi() {
        logger.log(System.Logger.Level.INFO, "Create InfoResourceServiceApi");
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addAbstractTypeMapping(InfoResourceApi.class, InfoResourceDto.class);
        objectMapper.registerModule(simpleModule);
        return RestClientFactory.create(InfoResourceServiceApi.class, "http://localhost:8080", objectMapper);
    }

}
