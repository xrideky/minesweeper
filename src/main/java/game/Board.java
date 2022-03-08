package game;

import game.tiles.Empty;
import game.tiles.Tile;

public class Board {

    public static final int BOARD_SIZE=10;

    private Tile[][] board;

    public Board() {
        this.board = new Tile[BOARD_SIZE][BOARD_SIZE];
        fillBoard();
    }

    private void fillBoard(){
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                this.board[i][j]=new Empty();
            }
        }
    }

    public String draw(){
        StringBuilder builder=new StringBuilder();

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                builder.append(this.board[i][j].draw());
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}