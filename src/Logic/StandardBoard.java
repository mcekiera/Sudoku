package Logic;

import java.util.*;

public class StandardBoard implements Iterable<Cell>{

    final private Cell[][] grid;
    final private List<List<Cell>> blocks;
    final private List<Cell> cells;
    private Iteration iteration = Iteration.LINEAR;

    public StandardBoard(){
        grid = new Cell[9][9];
        blocks = createBlocks(9);
        cells = new ArrayList<Cell>(81);
        createCells();
        prefill();
    }

    public void setIteration(Iteration order){
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

    private <T> List<List<T>> createBlocks(int capacity){
        List<List<T>> list = new ArrayList<List<T>>(capacity);

        for(int i = 0; i < capacity; i++){
            List<T> temp = new ArrayList<T>();
            list.add(temp);
        }
        return list;
    }

    public void updateAllCells(){
        for(Cell cell : this){
            updateSingleCell(cell);
        }
    }

    public void updateSingleCell(Cell cell){
        cell.clearMemory();
        for(int i : Cell.randomOrderList()){
            if(!testConditions(cell, i)){
                cell.excludeValue(i);
            }
        }
    }

    public void prefill(){
        iteration = Iteration.RANDOM;
        int counter = 11;
        for(Cell cell : this){
            int random = (int)(Math.random()*9+1);
            if(testConditions(cell, random)) {
                cell.presetValue(random);
            }
            if(counter==0){
                break;
            }
        }
        iteration = Iteration.LINEAR;
        System.out.println(this.toString());

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
        /*if(iteration.equals(Iteration.LINEAR)){
            System.out.println("linear");
            return linearOrderIterator();
        }else if(iteration.equals(Iteration.RANDOM)){
            System.out.println("random");
            return randomOrderIterator();
        }else if(iteration.equals(Iteration.S_SHAPE)){
            System.out.println("s_shaped");
            return sShapeOrderIterator();
        }else if(iteration.equals(Iteration.CIRCULAR)){
            System.out.println("circular");
            return linkedIterator();
        }  */
        return linearOrderIterator();
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
        List<Cell> sShape = new ArrayList<Cell>();
        List<Cell> temp = new ArrayList<Cell>();
        for(int i = 0; i < 9; i++){
            if((i+1)%2==0){
                temp = new ArrayList<Cell>(Arrays.asList(grid[i]));
                System.out.println(temp);
                Collections.reverse(temp);
                System.out.println(temp);
                sShape.addAll(temp);
            }else{
                sShape.addAll(Arrays.asList(grid[i]));
            }
            System.out.println(this.toString() + "\n");
        }

        return sShape.listIterator();
    }

    public ListIterator<Cell> linkedIterator(){
        List<Cell> linked = new LinkedList<Cell>(cells);
        return linked.listIterator();
    }
}
