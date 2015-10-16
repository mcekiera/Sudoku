package Logic;

import java.util.*;

public class StandardBoard implements Iterable<Cell>, Cloneable{

    final private Cell[][] grid;
    final private List<List<Cell>> blocks;
    final private List<Cell> cells;
    private Iteration iteration = Iteration.LINEAR;

    public StandardBoard(){
        grid = new Cell[9][9];
        blocks = Util.createBlocks(9);
        cells = new ArrayList<Cell>(81);
        createCells();
    }

    public void setIterationOrder(Iteration order){
        iteration = order;
    }

    public Cell getCell(int x, int y){
        return grid[x][y];
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


    public void updateAllCells(){
        for(Cell cell : this){
            updateSingleCell(cell);
        }
    }

    public void updateSingleCell(Cell cell){
        cell.clearMemory();
        for(int i : Util.randomOrderDigits()){
            if(!testConditions(cell, i)){
                cell.excludeValue(i);
            }
        }
    }

    public void setCells(ArrayList<Cell> cells){
        Iterator<Cell> cellIterator = cells.iterator();
        for(Cell cell : this.cells){
            cell.setValue(cellIterator.next().getValue());
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
                System.out.println("linear");
                return linearOrderIterator();
            case RANDOM:
                System.out.println("random");
                return randomOrderIterator();
            case S_SHAPE:
                System.out.println("s_shaped");
                return sShapeOrderIterator();
            case CIRCULAR:
                System.out.println("circular");
                return linkedIterator();
            default:
                System.out.println("linear");
                return linearOrderIterator();
        }
    }

    public ListIterator<Cell> randomOrderIterator() {
        ArrayList<Cell> randomOrder = new ArrayList<Cell>(cells);
        Collections.shuffle(randomOrder);
        return randomOrder.listIterator();
    }

    public ListIterator<Cell> linearOrderIterator() {
        return cells.listIterator();
    }

    public ListIterator<Cell> sShapeOrderIterator(){
        return Util.sShapedList(grid).listIterator();
    }

    public ListIterator<Cell> linkedIterator(){
        List<Cell> linked = new LinkedList<Cell>(cells);
        return linked.listIterator();
    }
}
