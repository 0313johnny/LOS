module com.example.los {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires fastjson;
    requires io.netty.transport;
    requires io.netty.codec;
    requires google.oauth.client;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.jackson2;
    requires guava;
    requires google.api.services.youtube.v3.rev222;
    requires logback.classic;
    requires mongo.java.driver;
    requires jackson.mapper.asl;
    requires slf4j.api;
    requires google.api.services.youtubeAnalytics.v2.rev272;
    requires com.fasterxml.jackson.databind;

    opens com.example.los to javafx.fxml;
    exports com.example.los;
}