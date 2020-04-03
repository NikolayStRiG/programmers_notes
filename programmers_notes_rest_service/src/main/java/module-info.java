open module org.sterzhen.programmers_notes.rest_service {
    exports org.sterzhen.programmers_notes.rest_service;
    exports org.sterzhen.programmers_notes.rest_service.dao.repositories;
    exports org.sterzhen.programmers_notes.rest_service.controllers;

    requires transitive com.fasterxml.classmate;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive net.bytebuddy;
    requires transitive java.instrument;

    requires org.sterzhen.programmers_notes.rest_api;
    requires org.sterzhen.programmers_notes.core;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.beans;
    requires spring.web;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.core;
    requires java.persistence;
    requires java.annotation;
}