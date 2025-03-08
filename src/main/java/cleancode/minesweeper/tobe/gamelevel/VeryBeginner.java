package cleancode.minesweeper.tobe.gamelevel;

public class VeryBeginner implements GameLevel {

    @Override
    public int getRowsize() {
        return 4;
    }

    @Override
    public int getColsize() {
        return 5;
    }

    @Override
    public int getLandMineCount() {
        return 2;
    }

}
