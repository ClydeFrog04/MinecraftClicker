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
    private int textDelay = 2;//default to 2 seconds
    private ClickService clickService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clickService = new ClickService();
        Platform.runLater(() -> {
            clickService.start();
        });
    }

    //click service
    Service<Void> service = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    //Background work
                    final CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                //fx stuff here
                                System.out.println("service running");
                                for (int i = 0; i < 1000000; i++) {
                                    System.out.println(i);
                                }
                            }finally {
                                latch.countDown();
                            }
                        }
                    });
                    latch.await();
                    //keep with the background work
                    return null;
                }
            };
        }
    };


    public void buttonClicked(){
//        if(service.getState().equals(Worker.State.RUNNING)) service.cancel();
        if(isClicking) clickService.pause();
        else clickService.resume();
        isClicking = !isClicking;
        System.out.println(isClicking + " " + setDelayField.getText());
    }


}
