package Logic;

import java.util.ArrayList;
import java.util.List;

public class Board {
    
    final private Cell[][] board;
    final private List<List<Cell>> groups;
    static int count = 0;
    static int x = 0;
    static int y = 0;

    public Board(){
        board = createCells();
        groups = createGroups(9);
        fillGroups();
        fillBoard();
    }

    public Cell[][] fillBoard(){
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
                attempt = attempt < 9 ? ++attempt : 1;
                attempts++;
            }
        }
        return false;
    }

    public Cell[][] createCells() {
        Cell[][] cells = new Cell[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
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
        return checkRow(i) && checkColumn(i) && checkArea(i) && board[x][y].isValidValue(i);
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

    public <T> List<List<T>> createGroups(int capacity){
        List<List<T>> list = new ArrayList<List<T>>(capacity);

        for(int i = 0; i < capacity; i++){
            List<T> temp = new ArrayList<T>();
            list.add(temp);
        }

        return list;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                result += board[i][j].toString();
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args){
        Board board = new Board();
    }
}
