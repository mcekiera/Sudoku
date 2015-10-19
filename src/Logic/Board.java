package Logic;

import java.util.*;

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

    public List<Cell> getCells() {
        return cells;
    }

    public List<List<Cell>> getBlocks() { return blocks; }

    public void setIterationOrder(Iteration order) {
        iteration = order;
    }

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

    public boolean testConditions(Cell cell, int value) {
        return testBlock(cell, value) && testColumn(cell.getColumn(), value) && testRow(cell.getRow(), value);
    }

    private boolean testRow(int row, int value) {
        for(Cell cell : grid[row]) {
            if(value == cell.getValue()) return false;
        }
        return true;
    }

    private boolean testColumn(int column, int value) {
        for(Cell[] cells : grid) {
            if(value == cells[column].getValue()) return false;
        }
        return true;
    }

    private boolean testBlock(Cell testedCell, int value) {
        for(Cell cell : blocks.get(testedCell.getBlock())) {
            if(cell.getValue() == value) {
                return false;
            }
        }
        return true;
    }

    public void save() {
        for(Cell cell : this) {
            cell.save();
        }
    }

    public void load() {
        for (Cell cell : this) {
            cell.load();
        }
    }

    private ListIterator<Cell> randomOrderIterator() {
        ArrayList<Cell> randomOrder = new ArrayList<Cell>(cells);
        Collections.shuffle(randomOrder);
        return randomOrder.listIterator();
    }

    private ListIterator<Cell> linearOrderIterator() {
        return cells.listIterator();
    }

    private ListIterator<Cell> sShapeOrderIterator() {
        return s_ShapedList().listIterator();
    }

    @Override
    public ListIterator<Cell> iterator() {
        switch (iteration) {
            case LINEAR:
                return linearOrderIterator();
            case RANDOM:
                return randomOrderIterator();
            case S_SHAPE:
                return sShapeOrderIterator();
            default:
                return linearOrderIterator();
        }
    }

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

    public List<List<Cell>> createBlocks(int capacity) {
        List<List<Cell>> list = new ArrayList<List<Cell>>(capacity);
        for(int i = 0; i < capacity; i++) {
            List<Cell> temp = new ArrayList<Cell>();
            list.add(temp);
        }
        return list;
    }

    /**
     * Using a 2D Cell array containing all Cell objects of given Sudoku game board, this method crates List of
     * same elements with changed order, which allows to iterate through Sudoku game board in S-like order.
     * @return List of Cells of given game board with changed order.
     */
    public List<Cell> s_ShapedList() {
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
