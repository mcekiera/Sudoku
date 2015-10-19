package Logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Arrays;

/**
 * Sudoku game board composed of Cell objects.
 */
public class Board implements Iterable<Cell>, Cloneable {

    final private Cell[][] grid;
    final private List<List<Cell>> blocks;
    final private List<Cell> cells;
    private Iteration iteration = Iteration.LINEAR;

    public Board() {
        grid = new Cell[9][9];
        blocks = createBlocks(9);
        cells = new ArrayList<Cell>(81);
        createCells();
    }

    /**
     * @return List of Cells objects of given game board.
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * @return List of Lists of Cells, which are grouping Cells by it position within board blocks.
     */
    public List<List<Cell>> getBlocks() { return blocks; }

    /**
     * Set type of iteration through Boards cells. It use Iteration enum class objects.
     * @param order Iteration enum type object assigned to given iteration type.
     */
    public void setIterationOrder(Iteration order) {
        iteration = order;
    }

    /**
     * Creates 2D array of Cells object, which make game board.
     */
    public void createCells() {
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                Cell cell = new Cell(row,col);
                grid[row][col] = cell;
                cells.add(cell);
                blocks.get(cell.getBlock()).add(cell);
            }
        }
    }

    /**
     * It tests all three game conditions for particular Cell and value.
     * @param cell given Cell.
     * @param value which wil be assigned to Cell if it fulfills all conditions.
     * @return true if value in given Cell fulfills all three game conditions.
     */
    public boolean testConditions(Cell cell, int value) {
        return testBlock(cell, value) && testColumn(cell.getColumn(), value) && testRow(cell.getRow(), value);
    }

    /**
     * It tests if given value is unique in given game board row.
     * @param row checked row.
     * @param value tested value.
     * @return true if value is unique in given row.
     */
    private boolean testRow(int row, int value) {
        for(Cell cell : grid[row]) {
            if(value == cell.getValue()) return false;
        }
        return true;
    }

    /**
     * It tests if given value is unique in given game board column.
     * @param column checked column.
     * @param value tested value.
     * @return true if value is unique in given column.
     */
    private boolean testColumn(int column, int value) {
        for(Cell[] cells : grid) {
            if(value == cells[column].getValue()) return false;
        }
        return true;
    }

    /**
     * It tests if given value is unique in given game board block (3 x 3 Cells group).
     * @param testedCell tested Cell.
     * @param value tested value.
     * @return true if value is unique in given block.
     */
    private boolean testBlock(Cell testedCell, int value) {
        for(Cell cell : blocks.get(testedCell.getBlock())) {
            if(cell.getValue() == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calls save() method on every Cell within Board.
     */
    public void save() {
        for(Cell cell : this) {
            cell.save();
        }
    }

    /**
     * Calls load() method on every Cell within Board.
     */
    public void load() {
        for (Cell cell : this) {
            cell.load();
        }
    }

    /**
     * @return ListIterator of random order List of all Cells of a given Board.
     */
    private ListIterator<Cell> randomOrderIterator() {
        ArrayList<Cell> randomOrder = new ArrayList<Cell>(cells);
        Collections.shuffle(randomOrder);
        return randomOrder.listIterator();
    }

    /**
     * @return ListIterator of sequential order List of all Cells of a given Board.
     */
    private ListIterator<Cell> linearOrderIterator() {
        return cells.listIterator();
    }

    /**
     * @return ListIterator of S-like order List of all Cells of a given Board.
     */
    private ListIterator<Cell> sLikeOrderIterator() {
        return s_LikeList().listIterator();
    }

    /**
     * @return ListIterator object of given Board object with set iteration order.
     */
    @Override
    public ListIterator<Cell> iterator() {
        switch (iteration) {
            case LINEAR:
                return linearOrderIterator();
            case RANDOM:
                return randomOrderIterator();
            case S_LIKE:
                return sLikeOrderIterator();
            default:
                return linearOrderIterator();
        }
    }

    /**
     * @return String contains all values of Cells within given Board.
     */
    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                result += grid[i][j].toString() + ",";
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Creates the List of Lists of Cells of given Board.
     * @param capacity number of Cells in given Sudoku game board.
     * @return List of Lists of Cells assigned to specific blocks.
     */
    public List<List<Cell>> createBlocks(int capacity) {
        List<List<Cell>> list = new ArrayList<List<Cell>>(capacity);
        for(int i = 0; i < capacity; i++) {
            list.add(new ArrayList<Cell>());
        }
        return list;
    }

    /**
     * Using a 2D Cell array containing all Cell objects of given Sudoku game board, this method crates List of
     * same elements with changed order, which allows to iterate through Sudoku game board in S-like order.
     * @return List of Cells of given game board with changed order.
     */
    public List<Cell> s_LikeList() {
        List<Cell> sShape = new ArrayList<Cell>();
        List<Cell> temp;
        for(int i = 0; i < 9; i++) {
            if((i+1)%2==0) {
                temp = new ArrayList<Cell>(Arrays.asList(grid[i]));
                Collections.reverse(temp);
                sShape.addAll(temp);
            } else {
                sShape.addAll(Arrays.asList(grid[i]));
            }
        }
        return sShape;
    }

}
