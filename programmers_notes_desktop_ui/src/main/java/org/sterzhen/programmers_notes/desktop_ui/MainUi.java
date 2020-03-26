package org.sterzhen.programmers_notes.desktop_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceServiceApi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

import static java.lang.System.Logger.Level.ERROR;
import static java.lang.System.Logger.Level.INFO;

@SpringBootApplication
public class MainUi implements CommandLineRunner {

    public static final String DATA_NOT_FOUND = "Нет данных";
    private static System.Logger logger = System.getLogger(MainUi.class.getName());

    private final InfoResourceServiceApi resourceService;

    @Autowired
    public MainUi(InfoResourceServiceApi resourceService) {
        this.resourceService = resourceService;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(MainUi.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {

        JFrame frame = new JFrame("Spring Boot Swing App");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(300,300);
        JPanel panel = new JPanel(new BorderLayout());
        JTextField text = new JTextField("Spring Boot can be used with Swing apps");
        panel.add(text, BorderLayout.CENTER);
        Button button = createButton("get info", e -> {
            try {
                logger.log(INFO, "get info");
                var r = resourceService.getById(1L);
                logger.log(INFO, Objects.toString(r, DATA_NOT_FOUND));
                text.setText(Objects.toString(r));
            } catch (Exception ex) {
                logger.log(ERROR, "Error getById", ex);
                text.setText(ex.getMessage());
            }
        });
        panel.add(button, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private Button createButton(String s, ActionListener listener) {
        Button button = new Button(s);
        button.addActionListener(listener);
        return button;
    }
}
