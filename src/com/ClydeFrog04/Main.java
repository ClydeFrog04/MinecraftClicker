package com.ClydeFrog04;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        //set textfield to be numeric only
        controller.setDelayField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*\\.")) {
                controller.setDelayField.setText(newValue.replaceAll("[^\\d\\.]", ""));
            }
        });

        primaryStage.setTitle("OP Farms");
        Scene mainScene = new Scene(root, 500, 500);
        mainScene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE){
                System.out.println("escape pressed");
                controller.shiftService.pause();
                controller.holdShiftCheckBox.setSelected(false);
            }
        });
        primaryStage.setScene(mainScene);


        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("Closing");
    }


    public static void main(String[] args) {
        launch(args);
    }
}