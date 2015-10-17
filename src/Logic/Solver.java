package Logic;

import java.util.*;

public class Solver {
    private StandardBoard board;
    private int count = 0;
    int index = 0;
    int trial = 0;

    public Solver setBoard(StandardBoard board){
        this.board = board;
        count = 0;
        index = 0;
        trial = 0;
        return this;
    }

    public int solve(List<Cell> cells, int limit){
        if(index < cells.size()){
            for(int i : Util.randomOrderDigits()){
                trial += 1;
                if(passValue(cells.get(index),i)){
                    index += 1;
                    if(solve(cells, limit)>= limit){
                        return count;
                    }
                }
            }
            return backtrace(cells);
        } else {
            return finish();
        }
    }

    public int backtrace(List<Cell> cells){
        cells.get(index).reset();
        index -= 1;
        return count;
    }

    public int finish(){
        count++;
        index -= 1;
        return count;
    }

    private List<Integer> getSolution(List<Cell> cells){
        List<Integer> integers = new ArrayList<Integer>();
        for(Cell cell : cells){
            integers.add(cell.getValue());
        }
        return integers;
    }

    private boolean passValue(Cell cell, int i) {
        if (!board.testConditions(cell, i)) {
            return false;
        }else{
            cell.setValue(i);
            return true;
        }
    }

}
