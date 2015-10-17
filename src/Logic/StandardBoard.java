package Logic;

import java.util.*;

public class StandardBoard implements Iterable<Cell>, Cloneable{

    final private Cell[][] grid;
    final private List<List<Cell>> blocks;
    final private List<Cell> cells;
    private List<Integer> save;
    private Iteration iteration = Iteration.LINEAR;

    public StandardBoard(){
        grid = new Cell[9][9];
        blocks = Util.createBlocks(9);
        cells = new ArrayList<Cell>(81);
        save = new ArrayList<Integer>(81);
        createCells();
    }

    public List<Cell> getCells(){
        return cells;
    }

    public void setIterationOrder(Iteration order){
        iteration = order;
    }

    public void createCells(){
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                Cell cell = new Cell(row,col);
                grid[row][col] = cell;
                cells.add(cell);
                blocks.get(cell.getBlock()).add(cell);
            }
        }
    }

    public boolean testConditions(Cell cell, int value){
        return testBlock(cell, value) && testColumn(cell.getColumn(), value) && testRow(cell.getRow(), value);
    }

    private boolean testRow(int row, int value){
        for(Cell cell : grid[row]){
            if(value == cell.getValue()) return false;
        }
        return true;
    }

    private boolean testColumn(int column, int value){
        for(Cell[] cells : grid){
            if(value == cells[column].getValue()) return false;
        }
        return true;
    }

    private boolean testBlock(Cell testedCell, int value){
        for(Cell cell : blocks.get(testedCell.getBlock())){
            if(cell.getValue() == value){
                return false;
            }
        }
        return true;
    }

    public void updateSingleCell(Cell cell){
        cell.clearMemory();
        for(int i : Util.randomOrderDigits()){
            if(!testConditions(cell, i)){
                cell.excludeValue(i);
            }
        }
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                result += grid[i][j].toString() + ",";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public ListIterator<Cell> iterator() {
        switch (iteration) {
            case LINEAR:
                System.out.println("SB linear");
                return linearOrderIterator();
            case RANDOM:
                System.out.println("SB random");
                return randomOrderIterator();
            case S_SHAPE:
                System.out.println("SB s_shaped");
                return sShapeOrderIterator();
            case EVERY_SECOND:
                System.out.println("SB every_second");
                return everySecondIterator();
            default:
                System.out.println("SB linear");
                return linearOrderIterator();
        }
    }

    private ListIterator<Cell> randomOrderIterator() {
        ArrayList<Cell> randomOrder = new ArrayList<Cell>(cells);
        Collections.shuffle(randomOrder);
        return randomOrder.listIterator();
    }

    private ListIterator<Cell> everySecondIterator() { return Util.jumpOneCell(cells).listIterator(); }

    private ListIterator<Cell> linearOrderIterator() {
        return cells.listIterator();
    }

    private ListIterator<Cell> sShapeOrderIterator(){
        return Util.sShapedList(grid).listIterator();
    }

    public void save(){
        save.clear();
        for(Cell cell : this){
            cell.save();
        }
    }

    public StandardBoard load(){
        for(Cell cell : this){
            cell.load();
        }
        return this;
    }
}
