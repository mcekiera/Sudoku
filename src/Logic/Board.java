package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    
    final private Cell[][] grid;
    final private List<List<Cell>> blocks;
    final private List<Cell> cells;
    int count = 0;
    int row = 0;
    int column = 0;


    public Board(){
        cells = new ArrayList<Cell>(81);
        grid = createCells();
        blocks = createBlocks(9);
        init();
        System.out.println(this.toString());
    }

    public void init(){
        fillBlocks();
        fillGrid();
        clearCellsMemory();
        makeHoles();
    }

    public Cell[][] fillGrid(){
        if(count == 81){
            return grid;
        }else if(addNumber()){
            forward();
            return fillGrid();
        }else {
            backtrace();
            return fillGrid();
        }
    }

    private Cell[][] getGrid(){
        return grid;
    }

    private boolean addNumber(){
        int value = (int)(Math.random()*9+1);
        int attempts = 0;
        while(attempts < 9) {
            if (passValue(value)) {
                cells.get(count).setValue(value);
                return true;
            } else {
                value = value < 9 ? ++value : 1;
                attempts++;
            }
        }
        return false;
    }

    private Cell[][] createCells() {
        Cell[][] cells = new Cell[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                Cell cell = new Cell(i,j);
                cells[i][j] = cell;
                this.cells.add(cell);
            }
        }
        return cells;
    }

    private void fillBlocks(){

        for(Cell[] cells : grid){
            for(Cell cell : cells){
                blocks.get(cell.getGroup()).add(cell);
            }
        }
    }

    private boolean checkRow(int i){
        for(Cell cell : grid[row]){
            if(i == cell.getValue()) return false;
        }
        return true;
    }

    private boolean checkColumn(int i){
        for(Cell[] cells : grid){
            if(i == cells[column].getValue()) return false;
        }
        return true;
    }

    private boolean checkBlock(int i){
        for(Cell cell : blocks.get(cells.get(count).getGroup())){
            if(cell.getValue() == i){
                return false;
            }
        }
        return true;
    }

    private boolean passValue(int i){
        return checkRow(i) && checkColumn(i) && checkBlock(i) && cells.get(count).isValidValue(i);
    }

    private void forward(){
        count++;
        if(column <8){
            column++;
        }else{
            column = 0;
            row++;
        }
    }

    private void backtrace(){
        grid[row][column].reset();
        count--;
        if(column > 0){
            column--;
        }else{
            column = 8;
            row--;
        }

    }

    private <T> List<List<T>> createBlocks(int capacity){
        List<List<T>> list = new ArrayList<List<T>>(capacity);

        for(int i = 0; i < capacity; i++){
            List<T> temp = new ArrayList<T>();
            list.add(temp);
        }
        return list;
    }

    public void clearCellsMemory(){
        for(Cell cell : cells){
            cell.clearMemory();
        }
    }

    public Cell[][] gridCopy(){
        Cell[][] copy = new Cell[9][9];
        for(int x = 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                Cell cell = new Cell(x,y);
                cell.setValue(grid[x][y].getValue());
                copy[x][y] = cell;
            }
        }
        return copy;
    }

    public void makeHoles(){
        List<Cell> random = new ArrayList<Cell>(cells);
        List<Cell> empty = new ArrayList<Cell>();
        Collections.shuffle(random);
        int temp = 0;
        int x = 0;
        while(x < 3) {
            for (Cell cell : random) {
                temp = cell.getValue();
                cell.setValue(0);
                if (testUniqueness(random)) {
                    empty.add(cell);
                } else {
                    cell.setValue(temp);
                }
            }
            x++;
        }

    }

    public boolean testUniqueness(List<Cell> list){
        for(Cell cell : list) {
            row = cell.getRow();
            column = cell.getColumn();
            count = cells.indexOf(cell);
            int counter = 0;
            for (int i = 1; i <= 9; i++) {
                if (passValue(i)){ counter++; }
                if (counter > 1){ return false; }
            }
        }
        return true;
    }


    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                result += grid[i][j].toString() + "  ";
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args){
        Board board = new Board();
    }
}
