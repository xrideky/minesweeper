package game;

import game.tiles.Empty;
import game.tiles.Mine;
import game.tiles.MineRevealedException;
import game.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    public static final int BOARD_SIZE=10;
    public static final int MINE_COUNT=20;


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

        Random rand=new Random();
        int minesLeft= MINE_COUNT;
        while(minesLeft>0){
            int mx=rand.nextInt(BOARD_SIZE);
            int my=rand.nextInt(BOARD_SIZE);
            if(this.board[my][mx] instanceof Empty){
                this.board[my][mx]=new Mine();
                minesLeft--;
            }
        }

        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(this.board[i][j] instanceof Empty) {
                    Empty empty=(Empty) this.board[i][j];
                    List<Tile> orthogonal = new ArrayList<>();
                    if (i - 1 >= 0) {
                        orthogonal.add(this.board[i - 1][j]);
                    }
                    if (i + 1 < BOARD_SIZE) {
                        orthogonal.add(this.board[i + 1][j]);
                    }
                    if (j - 1 >= 0) {
                        orthogonal.add(this.board[i][j - 1]);
                    }
                    if (j + 1 < BOARD_SIZE) {
                        orthogonal.add(this.board[i][j + 1]);
                    }
                    empty.addOrthogonalNeighbours(orthogonal);

                    List<Tile> diagonal = new ArrayList<>();
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        diagonal.add(this.board[i - 1][j-1]);
                    }
                    if (i - 1 >= 0 && j + 1 < BOARD_SIZE) {
                        diagonal.add(this.board[i - 1][j+1]);
                    }
                    if (i + 1 < BOARD_SIZE && j - 1 >= 0) {
                        diagonal.add(this.board[i + 1][j-1]);
                    }
                    if (i + 1 < BOARD_SIZE && j + 1 < BOARD_SIZE) {
                        diagonal.add(this.board[i+1][j + 1]);
                    }
                    empty.addDiagonalNeighbours(diagonal);
                }
            }
        }
    }

    public String draw(){
        StringBuilder builder=new StringBuilder();

        builder.append("  0123456789\n");

        for(int i=0;i<BOARD_SIZE;i++){
            builder.append((char)('a'+i));
            builder.append(" ");
            for(int j=0;j<BOARD_SIZE;j++){
                builder.append(this.board[i][j].draw());
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public void reveal(Move move) throws MineRevealedException {
        this.board[move.y][move.x].reveal();
    }
}
