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

    opens com.example.los to javafx.fxml;
    exports com.example.los;
}