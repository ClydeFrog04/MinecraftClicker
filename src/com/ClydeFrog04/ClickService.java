package com.ClydeFrog04;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


public class ClickService extends ScheduledService<Void>{
    Task<Void> task;
    int n = 1000000000;//this could be the click delay passed in

    @Override
    protected Task<Void> createTask(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < n; i++) {
                    System.out.println("Hello" + n);
                    Thread.sleep(2000);
                    n--;
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