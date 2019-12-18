package com.example.task_4;

public class Todo  {
    private int id;
    private String text;
    private boolean isCompleted;

    public String getText(){
        return text;
    }
    public Boolean getStatus(){
        return isCompleted;
    }
    public void changeStatus(){
        isCompleted = !isCompleted;
    }
    public int getId(){
        return id;
    }
}
