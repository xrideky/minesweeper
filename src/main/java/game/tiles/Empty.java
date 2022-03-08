package game.tiles;

public class Empty extends Tile{
    @Override
    protected String drawRevealed() {
        return ".";
    }
}
