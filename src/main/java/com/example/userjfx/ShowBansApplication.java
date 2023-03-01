package com.example.userjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowBansApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShowBansApplication.class.getResource("ShowBans.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1300,900);
        primaryStage.setTitle("Register Form WORKING !");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
