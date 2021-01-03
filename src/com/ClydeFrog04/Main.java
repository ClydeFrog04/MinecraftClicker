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
            System.out.println("Key pressed");
            if(e.getCode() == KeyCode.F12){
                handlef12();
            }
        });
        primaryStage.setScene(mainScene);

        /*


        scene.setOnKeyPressed(e -> {
    if (e.getCode() == KeyCode.A) {
        System.out.println("A key was pressed");
    }
});
         */
        primaryStage.show();
    }

    public void handlef12(){
        //f12 will be used for initializing and ending auto crafting
        System.out.println("F12 pressed");
        if(controller.isCrafting){
            //pause crafting service
            //set status

        }else{
            if(controller.isClicking){//check if the auto clicker is running, if it is, then stop clicking before crafting
                controller.clickService.pause();
                controller.isClicking = false;
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}