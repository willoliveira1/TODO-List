package br.com.acelerazg.todolist;

import br.com.acelerazg.todolist.infra.view.BoardView;

public class ApiApplication {

    public static void main(String[] args) {

        BoardView boardService = new BoardView();
        boardService.boardGenerate();

    }

}
