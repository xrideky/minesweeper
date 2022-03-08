package game.tiles;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Tile{

    private int nearByMines;

    private List<Tile> neighbours;

    public Empty() {
        this.nearByMines=0;
        this.neighbours =new ArrayList<>();
    }

    @Override
    public void reveal() throws MineRevealedException {
        boolean wasHidden=!this.isRevealed();
        super.reveal();
        if(this.nearByMines==0 && wasHidden){
            for(Tile t: this.neighbours){
                t.reveal();
            }
        }
    }

    @Override
    protected String drawRevealed() {
        if(nearByMines==0) {
            return ".";
        }
        return nearByMines + "";
    }

    public void addNeighbours(List<Tile> neighbours){
        this.neighbours.addAll(neighbours);
        neighbours.forEach(tile ->{
            if(tile instanceof Mine){
                this.nearByMines++;
            }
        } );
    }

}
