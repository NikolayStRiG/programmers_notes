package org.sterzhen.programmers_notes.desktop_ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sterzhen.programmers_notes.core.repositories.InfoResourceRepository;
import org.sterzhen.programmers_notes.core.services.InfoResourceService;
import org.sterzhen.programmers_notes.core.services.InfoResourceServiceImpl;

import java.awt.print.Pageable;

@Configuration
public class DesktopConfiguration {

    @Bean
    public InfoResourceService infoResourceService(InfoResourceRepository infoResourceRepository) {
        return new InfoResourceServiceImpl(infoResourceRepository);
    }
}
