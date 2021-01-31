package com.ClydeFrog04;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Date;


public class ClickService extends ScheduledService<Void>{
    Task<Void> task;
    private TextField delayTextField;
    private int delay;
    Robot bot;

    public ClickService(TextField delayTextField) throws AWTException {
        this.delayTextField = delayTextField;
        bot = new Robot();
    }

    @Override
    protected Task<Void> createTask(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                delay = (delayTextField.getText().length() > 0) ? (int) (Double.parseDouble(delayTextField.getText()) * 1000) : 2000;
                while(delay > 0){
                    System.out.println("Click at " + new Date(System.currentTimeMillis()));
                    Thread.sleep(delay);
                    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                }
                return null;
            }
        };
    }

    public void pause(){
        this.cancel();
    }

    public void resume(){
        this.restart();
    }
}