package com.ClydeFrog04;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.CheckBox;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ShiftService extends ScheduledService<Void> {
    Task<Void> task;
    private CheckBox holdShiftCheckBox;
    private Robot bot;

    public ShiftService(CheckBox holdShiftCheckBox) throws AWTException{
        this.holdShiftCheckBox = holdShiftCheckBox;
        bot = new Robot();
    }

    @Override
    protected Task<Void> createTask(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                bot.keyPress(KeyEvent.VK_SHIFT);
                return null;
            }
        };
    }

    public void pause(){
        bot.keyRelease(KeyEvent.VK_SHIFT);
        this.cancel();
    }

    public void resume(){
        this.restart();
    }
}