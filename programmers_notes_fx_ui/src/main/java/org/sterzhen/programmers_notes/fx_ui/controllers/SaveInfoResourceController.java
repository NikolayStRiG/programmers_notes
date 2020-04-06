package org.sterzhen.programmers_notes.fx_ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sterzhen.programmers_notes.fx_ui.dto.InfoResourceDto;
import org.sterzhen.programmers_notes.rest_api.core.Pageable;
import org.sterzhen.programmers_notes.rest_api.dto.InfoResourceApi;
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
    private Label errorLog;
    @FXML
    private TextField infoResourceName;
    @FXML
    private TextField infoResourceAddress;
    @FXML
    private TextArea infoResourceDescription;

    @FXML
    private TextField infoResourceIdUpdate;
    @FXML
    private TextField infoResourceNameUpdate;
    @FXML
    private TextField infoResourceAddressUpdate;
    @FXML
    private TextArea infoResourceDescriptionUpdate;

    @FXML
    private ListView<InfoResourceApi> infoResourceList;

    @FXML
    public TextField pageInfo;
    @FXML
    public ListView infoResourcePage;

    @FXML
    public void save() {
        try {
            logger.log(INFO, "Create info");
            final var resource = new InfoResourceDto();
            resource.setName(infoResourceName.getText());
            resource.setAddress(infoResourceAddress.getText());
            resource.setDescription(infoResourceDescription.getText());

            var r = infoResourceService.create(resource);
            logger.log(INFO, Objects.toString(r, DATA_NOT_FOUND));
            errorLog.setText(Objects.toString(r));
            infoResourceList.getItems().add(r);
        } catch (Exception ex) {
            logger.log(ERROR, "Error create InfoResource", ex);
            errorLog.setText("Error create InfoResource: " + getMessage(ex));
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

    @FXML
    public void update() {
        try {
            logger.log(INFO, "Update info");
            final var resource = new InfoResourceDto();
            resource.setId(Long.valueOf(infoResourceIdUpdate.getText()));
            resource.setName(infoResourceNameUpdate.getText());
            resource.setAddress(infoResourceAddressUpdate.getText());
            resource.setDescription(infoResourceDescriptionUpdate.getText());

            var r = infoResourceService.update(resource);
            logger.log(INFO, Objects.toString(r, DATA_NOT_FOUND));
            errorLog.setText(Objects.toString(r));
            infoResourceList.getItems().set(infoResourceList.getSelectionModel().getSelectedIndex(), r);

            infoResourceName.clear();
            infoResourceAddress.clear();
            infoResourceDescription.clear();
        } catch (Exception ex) {
            logger.log(ERROR, "Error update InfoResource", ex);
            errorLog.setText("Error update InfoResource: " + getMessage(ex));
        }
    }

    public void clickToInfoResourceList() {
        try {
            var model = infoResourceList.getSelectionModel();
            var item = model.getSelectedItem();

            infoResourceIdUpdate.setText(item.getId().toString());
            infoResourceNameUpdate.setText(item.getName());
            infoResourceAddressUpdate.setText(item.getAddress());
            infoResourceDescriptionUpdate.setText(item.getDescription());
        } catch (Exception ex) {
            logger.log(ERROR, "Error clickToInfoResourceList InfoResource", ex);
            errorLog.setText("Error clickToInfoResourceList InfoResource: " + getMessage(ex));
        }
    }

    public void refresh() {
        try {
            logger.log(INFO, "Refresh info");
            var data = infoResourceService.getAll();
            infoResourceList.getItems().clear();
            infoResourceList.getItems().addAll(data);
        } catch (Exception ex) {
            logger.log(ERROR, "Error refresh InfoResource", ex);
            errorLog.setText("Error refresh InfoResource: " + getMessage(ex));
        }

    }

    public void delete() {
        try {
            var id = Long.valueOf(infoResourceIdUpdate.getText());
            infoResourceService.deleteById(id);
            deleteById(id);

            infoResourceIdUpdate.clear();
            infoResourceNameUpdate.clear();
            infoResourceAddressUpdate.clear();
            infoResourceDescriptionUpdate.clear();
        } catch (Exception ex) {
            logger.log(ERROR, "Error delete InfoResource", ex);
            errorLog.setText("Error delete InfoResource: " + getMessage(ex));
        }
    }

    private void deleteById(Long id) {
        var items = infoResourceList.getItems();
        for (int i = 0; i < items.size(); i++) {
            var item = items.get(i);
            if (item.getId().equals(id)) {
                infoResourceList.getItems().remove(i);
            }
        }
    }

    public void refreshPage() {
        var pageable = new Pageable(0, 10);
        var result = infoResourceService.getPage(pageable);
        pageInfo.setText(Objects.toString(result.getNumber(), ""));
        infoResourcePage.getItems().clear();
        infoResourcePage.getItems().addAll(result.getContent());
    }

    public void nextPage() {
        int num = pageInfo.getText().isEmpty() ? 0 : Integer.parseInt(pageInfo.getText());
        var pageable = new Pageable(++num, 10);
        var result = infoResourceService.getPage(pageable);
        pageInfo.setText(Objects.toString(result.getNumber(), ""));
        infoResourcePage.getItems().clear();
        infoResourcePage.getItems().addAll(result.getContent());
    }

    public void previousPage() {
        int num = pageInfo.getText().isEmpty() ? 0 : Integer.parseInt(pageInfo.getText());
        var pageable = new Pageable(--num, 10);
        var result = infoResourceService.getPage(pageable);
        pageInfo.setText(Objects.toString(result.getNumber(), ""));
        infoResourcePage.getItems().clear();
        infoResourcePage.getItems().addAll(result.getContent());
    }
}
