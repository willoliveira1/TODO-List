package br.com.acelerazg.todolist;

import br.com.acelerazg.todolist.infra.services.BoardService;

import java.io.IOException;

public class ApiApplication {

    public static void main(String[] args) throws IOException {

        BoardService boardService = new BoardService();
        boardService.boardGenerate();

    }

}
