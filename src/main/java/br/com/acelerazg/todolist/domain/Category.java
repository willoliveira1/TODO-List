package br.com.acelerazg.todolist.domain;

public class Category {

    private String title;

    public Category(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
