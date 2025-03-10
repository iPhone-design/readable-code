package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class NumberCell implements Cell {

    private final int nearByLandMineCount;

    private final CellStatue cellStatus = CellStatue.initialize();

    public NumberCell(int nearByLandMineCount) {
        this.nearByLandMineCount = nearByLandMineCount;
    }

    @Override
    public boolean isLandMind() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public CellSnapshot getSnapshot() {
        if (cellStatus.isOpened()) {
            return CellSnapshot.ofNumber(nearByLandMineCount);
        }
        if (cellStatus.isFlagged()) {
            return CellSnapshot.ofFlag();
        }
        return CellSnapshot.ofUnchecked();
    }

    @Override
    public void flag() {
        cellStatus.flag();
    }

    @Override
    public void open() {
        cellStatus.open();
    }

    @Override
    public boolean isChecked() {
        return cellStatus.isOpened();
    }

    @Override
    public boolean isOpened() {
        return cellStatus.isOpened();
    }

}
