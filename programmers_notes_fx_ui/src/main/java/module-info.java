open module org.programmers_notes_fx_ui {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.sterzhen.programmers_notes.fx_ui;

    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires java.desktop;
    requires org.sterzhen.programmers_notes.rest_api;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires spring.context;
    requires org.sterzhen.rest.client;
}