package cleancode.minesweeper.tobe.minesweeper.gamelevel;

public class Middle implements GameLevel {

    @Override
    public int getRowsize() {
        return 14;
    }

    @Override
    public int getColsize() {
        return 18;
    }

    @Override
    public int getLandMineCount() {
        return 40;
    }

}
