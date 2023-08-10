package br.com.acelerazg.todolist.infra.fileProcessors;

import java.io.IOException;
import java.util.List;

public interface Processor<T> {

    String filePath = null;

    List<T> readFile() throws IOException;
    void writeLine(T t) throws IOException;
    void deleteLine(int textLine) throws IOException;
    void updateLine(int textLine, T t) throws IOException;

}
