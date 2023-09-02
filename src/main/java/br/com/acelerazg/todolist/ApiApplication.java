package br.com.acelerazg.todolist;

import br.com.acelerazg.todolist.infra.view.BoardView;

import java.io.IOException;

public class ApiApplication {

    public static void main(String[] args) throws IOException {

        BoardView boardService = new BoardView();
        boardService.boardGenerate();

    }

}
