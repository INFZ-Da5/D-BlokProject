package infdpacman;

/**
 *
 * @author CVD
 */
final class GridPlace {
    private final int row;
    private final int column;

    public GridPlace(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
