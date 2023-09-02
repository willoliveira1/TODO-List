package br.com.acelerazg.todolist.infra.util;

import br.com.acelerazg.todolist.domain.Base;

import java.util.List;

public class ObjectHandler {

    public static <T extends Base> int getLastId(List<T> objects) {
        int highestId = 0;

        for (T object : objects) {
            if (object.getId() > highestId) {
                highestId = object.getId();
            }
        }
        return highestId;
    }

    public static <T extends Base> int getNextId(List<T> objects) {
        return getLastId(objects) + 1;
    }

}
