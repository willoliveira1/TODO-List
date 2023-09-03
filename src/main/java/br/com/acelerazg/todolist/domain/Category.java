package br.com.acelerazg.todolist.domain;

public class Category extends Base implements Comparable<Category> {

    public Category(String title) {
        super(title);
    }

    public Category(int id, String title) {
        super(id, title);
    }

    @Override
    public String toString() {
        return this.getId() + "," +
                this.getTitle();
    }

    @Override
    public int compareTo(Category otherCategory) {
        return this.getTitle().compareTo(String.valueOf(otherCategory));
    }

}
