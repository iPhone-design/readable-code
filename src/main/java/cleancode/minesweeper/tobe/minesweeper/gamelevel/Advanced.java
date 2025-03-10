package cleancode.minesweeper.tobe.minesweeper.gamelevel;

public class Advanced implements GameLevel{

    @Override
    public int getRowsize() {
        return 20;
    }

    @Override
    public int getColsize() {
        return 24;
    }

    @Override
    public int getLandMineCount() {
        return 99;
    }

}
