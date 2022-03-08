package game.tiles;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Tile{

    private int nearByMines;

    private List<Tile> orthogonalNeighbours;

    public Empty() {
        this.nearByMines=0;
        this.orthogonalNeighbours=new ArrayList<>();
    }

    @Override
    public void reveal() throws MineRevealedException {
        boolean wasHidden=!this.isRevealed();
        super.reveal();
        if(this.nearByMines==0 && wasHidden){
            for(Tile t: this.orthogonalNeighbours){
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

    public void addOrthogonalNeighbours(List<Tile> neighbours){
        this.orthogonalNeighbours.addAll(neighbours);
        this.checkNeighboursForMines(neighbours);
    }

    public void addDiagonalNeighbours(List<Tile> neighbours){
        this.checkNeighboursForMines(neighbours);
    }

    private void checkNeighboursForMines(List<Tile> neighbours){
        neighbours.forEach(tile ->{
            if(tile instanceof Mine){
                this.nearByMines++;
            }
        } );
    }
}
