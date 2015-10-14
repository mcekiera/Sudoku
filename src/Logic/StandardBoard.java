package Logic;

import java.util.*;

public class StandardBoard implements Iterable<Cell>{

    final private Cell[][] grid;
    final private List<List<Cell>> blocks;
    final private List<Cell> cells;

    public StandardBoard(){
        grid = new Cell[9][9];
        blocks = new ArrayList<List<Cell>>(9);
        cells = new ArrayList<Cell>(81);
        createCells();
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

    public boolean addValue(Cell cell, int value){
        if(testConditions(cell,value)){
            return true;
        }else{
            return false;
        }
    }

    public List<Cell> getRandomOrder(){
        List<Cell> randomOrder = new ArrayList<Cell>(cells);
        Collections.shuffle(randomOrder);
        return randomOrder;
    }

    public boolean testConditions(Cell cell, int value){
        return checkBlock(cell,value) && checkColumn(cell.getColumn(),value) && checkRow(cell.getRow(),value);
    }

    private boolean checkRow(int row, int value){
        for(Cell cell : grid[row]){
            if(value == cell.getValue()) return false;
        }
        return true;
    }

    private boolean checkColumn(int column, int value){
        for(Cell[] cells : grid){
            if(value == cells[column].getValue()) return false;
        }
        return true;
    }

    private boolean checkBlock(Cell testedCell, int value){
        for(Cell cell : blocks.get(testedCell.getBlock())){
            if(cell.getValue() == value){
                return false;
            }
        }
        return true;
    }






    @Override
    public ListIterator<Cell> iterator() {
        return cells.listIterator();
    }
}
