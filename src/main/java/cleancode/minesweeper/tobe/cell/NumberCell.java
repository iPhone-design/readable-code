package cleancode.minesweeper.tobe.cell;

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
    public String getSign() {
        if (cellStatus.isOpened()) {
            return String.valueOf(nearByLandMineCount);
        }
        if (cellStatus.isFlagged()) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
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
        return cellStatus.isChecked();
    }

    @Override
    public boolean isOpened() {
        return cellStatus.isOpened();
    }

}
