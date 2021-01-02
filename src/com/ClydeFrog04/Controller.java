package com.ClydeFrog04;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
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
    private int textDelay = 2;//default to 2 seconds

    public void buttonClicked(){
        isClicking = !isClicking;
        System.out.println(isClicking + " " + setDelayField.getText());
    }


}
