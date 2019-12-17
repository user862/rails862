package com.example.task_4;

import java.util.List;

public class Project {
    private int id;
    private String title;
    private List<Todo> todos;

    /*public Project(int id,String title) {
        this.id = id;
        this.title = title;
        //this.todos=todos;
    }

    public int getId(){
        return id;
    }*/

    public String getTitle(){
        return title;
    }
    public List<Todo> getTodos(){
        return todos;
    }
    public int getId(){
        return id;
    }
}
