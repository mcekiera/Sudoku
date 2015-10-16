package Logic;

import java.util.*;

public class Solver {
    private StandardBoard board;
    private ListIterator<Cell> iterator;
    private List<Integer> size;
    private Cell current;

    public boolean solve(StandardBoard board){
        this.board = board;
        iterator = Util.getBlankCells(board).listIterator();
        current = iterator.next();
        while (true){
            if (passValue(current)) {
                if(!iterator.hasNext()){
                    return true;
                }
                current = iterator.next();
            }else{
                if(iterator.hasPrevious()) {
                    current.reset();
                    current = iterator.previous();
                }else {
                    return false;
                }
            }
        }
    }

    private boolean passValue(Cell cell) {
        ListIterator<Integer> iterator = cell.iterator();
        while(iterator.hasNext()) {
            if (!board.testConditions(cell, iterator.next())) {
                iterator.remove();
            }
        }
        return cell.addRandom();
    }

}
