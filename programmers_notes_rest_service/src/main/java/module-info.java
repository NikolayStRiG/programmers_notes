open module org.sterzhen.programmers_notes.rest_service {
    exports org.sterzhen.programmers_notes.rest_service;
    exports org.sterzhen.programmers_notes.rest_service.repositories;
    exports org.sterzhen.programmers_notes.rest_service.controllers;

    requires transitive com.fasterxml.classmate;
    requires transitive com.fasterxml.jackson.databind;

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.beans;
    requires spring.web;
    requires org.sterzhen.programmers_notes.rest_api;
    requires spring.context;
    requires org.sterzhen.programmers_notes.core;
}