package br.com.acelerazg.todolist.infra.service;

import java.util.List;

public interface BaseService<T> {

    List<T> getAll();
    void add(T t);
    void update(int id, T t);
    void remove(int id);

}
