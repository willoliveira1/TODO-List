package br.com.acelerazg.todolist.domain;

public class Category extends Base implements Comparable<Category> {

    public Category(int id, String title) {
        super(id, title);
    }

    public Category(String title) {
        super(title);
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
