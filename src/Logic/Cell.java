package Logic;

/**
 * The smallest separate Element of Sudoku game board, which holds a value from range 1-9 or is blank (value is 0).
 */
public class Cell{
    final private int row;
    final private int column;
    final private int block;
    private int value;
    private int save;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        block = Util.specifyBlock(row, column);
    }

    /**
     * @return row in which given cell is placed on Sudoku board
     */
    public int getRow() {
        return row;
    }

    /**
     * @return column in which given cell is placed on Sudoku board
     */
    public int getColumn() {
        return column;
    }

    /**
     *
     * @param value which the given cell will hold.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return Value currently held by the given Cell object.
     */
    public int getValue() {
        return  value;
    }

    /**
     * @return a integer from range 1-9, which identify in which block of Sudoku game board the given Cell
     * object is placed.
     */
    public int getBlock() {
        return block;
    }

    /**
     * Verifies if value of given Cell object is blank (equal to 0).
     * @return true if value of Cell is 0.
     */
    public boolean isBlank() {
        return value==0;
    }

    /**
     * Saves the value of the given cell for future use. The value is saved until next usage of this method.
     */
    public void save() {
        save = value;
    }

    /**
     * Retrieves the value saved by save() method. It does not affect the stored value.
     */
    public void load() {
        value = save;
    }

    /**
     * @return value currently held by the given cell.
     */
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    /**
     * Compares two objects of Cell class.
     * @param obj object to which given Cell object is compared.
     * @return Returns true if both Cell have same value, or false if compare Cell objects have different value
     * or one or both object are null.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if(obj.getClass() == this.getClass()) {
            return ((Cell) obj).getValue() == this.getValue() && ((Cell) obj).getRow() == this.getRow() && ((Cell) obj).getColumn() == this.getColumn();
        } else {
            return false;
        }
    }
}
