package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class LandMindCell implements Cell {

    private final CellStatue cellStatus = CellStatue.initialize();

    @Override
    public boolean isLandMind() {
        return true;
    }

    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public CellSnapshot getSnapshot() {
        if (cellStatus.isOpened()) {
            return CellSnapshot.ofLandMine();
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
        return cellStatus.isChecked();
    }

    @Override
    public boolean isOpened() {
        return cellStatus.isOpened();
    }
}
