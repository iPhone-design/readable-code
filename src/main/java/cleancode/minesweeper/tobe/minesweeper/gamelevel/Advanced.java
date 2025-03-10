package cleancode.minesweeper.tobe.minesweeper.gamelevel;

public class Advanced implements GameLevel{

    @Override
    public int getRowsize() {
        return 120;
    }

    @Override
    public int getColsize() {
        return 124;
    }

    @Override
    public int getLandMineCount() {
        return 9;
    }

}
