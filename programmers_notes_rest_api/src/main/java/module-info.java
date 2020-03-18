module org.sterzhen.programmers_notes.rest_api {
    exports org.sterzhen.programmers_notes.rest_api.service_interface;
    exports org.sterzhen.programmers_notes.rest_api.core;
    exports org.sterzhen.programmers_notes.rest_api.dto;

    requires transitive java.xml.bind;

    requires java.ws.rs;
}