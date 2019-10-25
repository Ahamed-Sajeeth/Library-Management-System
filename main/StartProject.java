package com.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartProject extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/com/view/fxml/Login.fxml"));
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root));
       // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/sms/other/img/HikmaLogo.jpg")));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
