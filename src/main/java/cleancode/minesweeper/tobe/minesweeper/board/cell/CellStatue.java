package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class CellStatue {

    private boolean isFlagged;
    private boolean isOpened;

    private CellStatue(boolean isFlagged, boolean isOpened) {
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static CellStatue initialize() {
        return new CellStatue(false, false);
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        isOpened = true;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

}
