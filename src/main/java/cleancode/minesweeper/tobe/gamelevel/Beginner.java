package cleancode.minesweeper.tobe.gamelevel;

public class Beginner implements GameLevel {

    @Override
    public int getRowsize() {
        return 8;
    }

    @Override
    public int getColsize() {
        return 10;
    }

    @Override
    public int getLandMineCount() {
        return 10;
    }

}
