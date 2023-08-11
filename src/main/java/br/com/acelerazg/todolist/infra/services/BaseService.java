package br.com.acelerazg.todolist.infra.services;

import java.io.IOException;
import java.util.List;

public interface BaseService<T> {

    public List<T> getAll() throws IOException;
    public void add(T t) throws IOException;
    public void update(int line, T t) throws IOException;
    public void remove(int line) throws IOException;

}
