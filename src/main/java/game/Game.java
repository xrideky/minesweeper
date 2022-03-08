package game;

import game.tiles.MineRevealedException;
import minesweeper.utility.ZKlavesnice;

public class Game {

    private Board board;

     public Game() {
        this.board=new Board();
    }

    public void play(){
        try {
                while(true) {
                    System.out.println(this.board.draw());
                    this.board.reveal(getNextMove());
                }
             } catch (MineRevealedException e) {
                 System.out.println("mina");
             }
    }

    private Move getNextMove(){
        char row= ZKlavesnice.readChar(String.format("Enter row (a-%s)\n",(char)('a'+Board.BOARD_SIZE-1)));
        int col= ZKlavesnice.readInt(String.format("Enter colum(1-%d)",Board.BOARD_SIZE-1));
        return new Move(col,row);
    }
}
