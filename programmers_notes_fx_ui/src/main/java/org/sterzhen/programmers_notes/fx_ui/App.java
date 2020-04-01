package org.sterzhen.programmers_notes.fx_ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * JavaFX App
 */
@SpringBootApplication
public class App extends Application {

    private static ConfigurableApplicationContext ctx;

    @Override
    public void start(Stage stage) throws IOException {
        var scene = new Scene(loadFXML("saveInfoResource"));
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(ctx::getBean);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        ctx = new SpringApplicationBuilder(App.class).headless(false).run(args);
        launch();
    }
}