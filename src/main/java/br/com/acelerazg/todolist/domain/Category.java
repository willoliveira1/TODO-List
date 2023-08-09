package br.com.acelerazg.todolist.domain;

public class Category {

    private int id;
    private String title;

    public Category(String title) {
        this.title = title;
    }

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

}
