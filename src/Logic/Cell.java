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
    private int solution;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        block = specifyBlock();
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
     * Save a value which is solution for given Cell in particular game board for future use.
     */
    public void setSolution(){
        solution = value;
    }

    /**
     * Retrieves saved value as solution.
     * @return value which is solution for given Cell.
     */
    public int getSolution(){
        return solution;
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
     * @return Returns true if both Cells have same value,  row and column field values, or false if compare
     * Cell objects have different values or one or both objects are null.
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && ((Cell) obj).getValue() == this.getValue() &&
                ((Cell) obj).getRow() == this.getRow() && ((Cell) obj).getColumn() == this.getColumn();
    }

    /**
     * @return block of Sudoku game board, to which given Cell belongs.
     */
    public int specifyBlock() {
        int x = row /3;
        int y = column /3;
        int modifier = row <3 ? 0 : row <6 ? 2 : 4;
        return x+y+modifier;
    }
}
