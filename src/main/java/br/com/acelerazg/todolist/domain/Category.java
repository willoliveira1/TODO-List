package br.com.acelerazg.todolist.domain;

public class Category implements Comparable<Category> {

    private String title;

    public Category(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int compareTo(Category otherCategory) {
        return title.compareTo(String.valueOf(otherCategory));
    }

}
