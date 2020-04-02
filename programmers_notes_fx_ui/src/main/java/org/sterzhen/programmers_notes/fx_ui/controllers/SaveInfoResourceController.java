package org.sterzhen.programmers_notes.fx_ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sterzhen.programmers_notes.fx_ui.dto.InfoResourceDto;
import org.sterzhen.programmers_notes.rest_api.service_interface.InfoResourceServiceApi;

import java.util.Objects;

import static java.lang.System.Logger.Level.ERROR;
import static java.lang.System.Logger.Level.INFO;

@Component
public class SaveInfoResourceController {

    private static System.Logger logger = System.getLogger(SaveInfoResourceController.class.getName());

    public static final String DATA_NOT_FOUND = "Нет данных";

    @Autowired
    private InfoResourceServiceApi infoResourceService;

    @FXML
    private Label testDate;
    @FXML
    private TextField infoResourceName;
    @FXML
    private TextField infoResourceAddress;
    @FXML
    private TextArea infoResourceDescription;

    @FXML
    public void save(ActionEvent actionEvent) {
        try {
            logger.log(INFO, "get info");
            final var resource = new InfoResourceDto();
            resource.setName(infoResourceName.getText());
            resource.setAddress(infoResourceAddress.getText());
            resource.setDescription(infoResourceDescription.getText());

            var r = infoResourceService.create(resource);
            logger.log(INFO, Objects.toString(r, DATA_NOT_FOUND));
            testDate.setText(Objects.toString(r));
        } catch (Exception ex) {
            logger.log(ERROR, "Error create InfoResource", ex);
            testDate.setText("Error create InfoResource: " + getMessage(ex));
        }
    }

    private String getMessage(Throwable t) {
        if (t.getMessage() != null) {
            return t.getMessage();
        }
        if (t.getCause() != null) {
            return getMessage(t.getCause());
        }
        return "";
    }
}
