package com.ClydeFrog04;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.InputEvent;


public class ClickService extends ScheduledService<Void>{
    Task<Void> task;
    int n = 1000000000;//this could be the click delay passed in
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
                delay = delayTextField.getText().length() > 0 ? (int)(Double.parseDouble(delayTextField.getText()) * 1000) : 2000;
                for (int i = 0; i < n; i++) {
                    System.out.println("Hello" + n);

                    Thread.sleep(delay);
                    n--;
                    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    /*
                    public static void click(int x, int y) throws AWTException{
    Robot bot = new Robot();
    bot.mouseMove(x, y);
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
}
                     */
                }
                return null;
            }
        };
    }

    public void pause(){
        this.cancel();
    }

    public void resume(){
        System.out.println("n=" + n);
        this.restart();
    }
}