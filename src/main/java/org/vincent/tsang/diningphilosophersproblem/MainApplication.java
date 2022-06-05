package org.vincent.tsang.diningphilosophersproblem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader startLoader = new FXMLLoader(MainApplication.class.getResource("start-view.fxml"));
        Parent startRoot = startLoader.load();
        Scene StartScene = new Scene(startRoot, 640, 480);
        stage.setTitle("Dining Philosophers Problem: 方法选择");
        stage.setScene(StartScene);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.show();
        StartScreenController startScreenController = startLoader.getController();
        startScreenController.setStage(stage);
    }
}