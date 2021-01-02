/*
references used:
https://stackoverflow.com/questions/25027835/javafx-how-to-pause-a-background-service-with-ui-controller-event
https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
https://stackoverflow.com/questions/16708931/javafx-working-with-threads-and-gui
 */

package com.ClydeFrog04;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class Controller implements Initializable {
    //fxml components
    @FXML
    Label delayLabel;
    @FXML
    TextField setDelayField;
    @FXML
    Button toggleButton;
    @FXML
    Label clickStatus;

    //application state vars
    private boolean isClicking = false;
    private ClickService clickService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            clickService = new ClickService(setDelayField);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            clickService.start();
            clickService.pause();
        });
    }

    public void buttonClicked(){
        if(isClicking) {
            clickService.pause();
            clickStatus.setText("Not Clicking");
        }
        else {
            clickService.resume();
            clickStatus.setText("Clicking");
        }
        isClicking = !isClicking;
        System.out.println(isClicking + " " + setDelayField.getText());
    }


}
