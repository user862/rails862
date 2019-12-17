package com.example.task_4;

public class Todo  {
    private int id;
    private String text;
    private Boolean isCompleted;
    //int project_id;

    /*public Todo (int id,String text,boolean isCompleted){
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
    }*/
    public String getText(){
        return text;
    }
    public Boolean getStatus(){
        return isCompleted;
    }
    public void changeStatus(){
        isCompleted = !isCompleted;
    }
}
