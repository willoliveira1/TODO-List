package br.com.acelerazg.todolist.infra.fileProcessors;

import java.io.IOException;
import java.util.List;

public interface Processor<T> {

    String filePath = null;

    public List<T> readFile() throws IOException;
    public void writeFile(T t) throws IOException;

}
