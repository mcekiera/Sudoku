package Logic;

import java.util.ArrayList;
import java.util.List;

public class Board {

    final int side;
    final Cell[][] board;
    final int total;
    static int count = 0;
    static int x = 0;
    static int y = 0;
    private List<List<Integer>> checkedNumbers;
    private List<List<Cell>> groups;

    public Board(int side){
        this.side = side;
        total = side * side;
        board = createCells();
        checkedNumbers = prepareIntegerList(side * side);
        groups = prepareCellList(side);
        fillGroups();
        fillBoard();
        System.out.println(count);
        System.out.println(this.toString() + "\n");
    }



    public Cell[][] fillBoard(){
        int value = (int)(Math.random()*0+1);

        if(count == 81){
            return board;
        }else if(addNumber()){
            forward();
            return fillBoard();
        }else {
            previous();
            return fillBoard();
        }
    }

    public boolean addNumber(){
        int attempt = (int)(Math.random()*9+1);
        int attempts = 0;
        while(attempts < 9) {
            if (passValue(attempt)) {
                board[x][y].setValue(attempt);
                return true;
            } else {
                if (attempt < 9) {
                    attempt++;
                } else {
                    attempt = 1;
                }
                attempts++;
            }
        }
        return false;

    }

    public Cell[][] createCells() {
        Cell[][] cells = new Cell[side][side];
        for(int i = 0; i < side; i++) {
            for(int j = 0; j < side; j++) {
                Cell cell = new Cell(i,j);
                cells[i][j] = cell;
            }
        }
        return cells;
    }

    public void fillGroups(){

        for(Cell[] cells : board){
            for(Cell cell : cells){
                groups.get(cell.getGroup()).add(cell);
            }
        }
    }

    public boolean checkRow(int i){
        for(Cell cell : board[x]){
            if(i == cell.getValue()) return false;
        }
        return true;
    }

    public boolean checkColumn(int i){
        for(Cell[] cells : board){
            if(i == cells[y].getValue()) return false;
        }
        return true;
    }

    public boolean checkArea(int i){
        for(Cell cell : groups.get(board[x][y].getGroup())){
            if(cell.getValue() == i){
                return false;
            }
        }
        return true;
    }

    public boolean passValue(int i){
        return checkRow(i) && checkColumn(i) && checkArea(i) && board[x][y].isValid(i);
    }

    public void forward(){
        count++;
        if(y<8){
            y++;
        }else{
            y = 0;
            x++;
        }
    }

    public void previous(){
        board[x][y].reset();
        count--;
        if(y > 0){
            y--;
        }else{
            y = 8;
            x--;
        }

    }

    public List<List<Integer>> prepareIntegerList(int capacity){
        List<List<Integer>> list = new ArrayList<List<Integer>>(capacity);

        for(int i = 0; i < capacity; i++){
          List<Integer> temp = new ArrayList<Integer>();
          list.add(temp);
        }

        return list;
    }

    public List<List<Cell>> prepareCellList(int capacity){
        List<List<Cell>> list = new ArrayList<List<Cell>>(capacity);

        for(int i = 0; i < capacity; i++){
            List<Cell> temp = new ArrayList<Cell>();
            list.add(temp);
        }

        return list;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < side; i++){
            for(int j = 0; j < side; j++){
                result += board[i][j].toString();
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args){
        Board board = new Board(9);
    }
}
