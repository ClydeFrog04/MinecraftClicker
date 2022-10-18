package com.ClydeFrog04;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Date;


public class ClickService extends ScheduledService<Void> {
//    Task<Void> task;
    private TextField delayTextField;
    private int delay;
    Robot bot;
    private boolean shouldRightClick = false;

    public ClickService(TextField delayTextField) throws AWTException {
        this.delayTextField = delayTextField;
        bot = new Robot();
    }

    public void setShouldRightClick(boolean shouldRightClick) {
        this.shouldRightClick = shouldRightClick;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                delay = (delayTextField.getText().length() > 0) ? (int) (Double.parseDouble(delayTextField.getText()) * 1000) : 2000;
                while (delay > 0) {
                    System.out.println("Click at " + new Date(System.currentTimeMillis()));
                    Thread.sleep(delay);
                    System.out.print("Right clicking?");
                    System.out.println(shouldRightClick);
//                    System.out.printf("right click? %b%n", rightClickCheckBox.isSelected());
                    if (shouldRightClick) {
                        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                    } else {
                        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }


                }
                return null;
            }
        };
    }

    public void pause() {
        this.cancel();
    }

    public void resume() {
        this.restart();
    }
}