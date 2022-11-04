module com.example.los {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens los to javafx.fxml;
    exports los;
    exports los.model;
    opens los.model to javafx.fxml;
    exports los.controller;
    opens los.controller to javafx.fxml;
}