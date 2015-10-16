package Logic;

import java.util.*;

public class Solver {
    private StandardBoard board;
    private ListIterator<Cell> iterator;
    private List<Integer> size;
    private Cell current;

    public Solver setBoard(StandardBoard board){
        this.board = board;
        //iterator = board.iterator();
        ArrayList<Cell> blank = new ArrayList<Cell>();
        board.setIterationOrder(Iteration.LINEAR);
        for(Cell cell : board){
            if(cell.getValue()==0){
                blank.add(cell);
            }
        }
        System.out.println(blank.size());
        iterator = blank.listIterator();

        size = new ArrayList<Integer>(81);
        return this;
    }

    public boolean solvee() {
        if (passValue(current)) {
            //current.hideValue(false);
            if (iterator.hasNext()) {
                current = iterator.next();
                return solve();
            } else {
                return true;
            }
        } else {
            if(iterator.hasPrevious()) {
                current.reset();
                current = iterator.previous();
                //current.hideValue(true);
                return solve();
            } else {
                return false;
            }
        }
    }

    public boolean solve(){
        current = iterator.next();

        while (iterator.hasNext()){
            //System.out.println(board.toString());
            System.out.println("In:" + current);
            if (passValue(current)) {
                current = iterator.next();
                //System.out.println("next: "+current);
            }else{
                if(iterator.hasPrevious()) {
                    current.reset();
                    current = iterator.previous();
                    //System.out.println("previous"+current);
                }else {
                    return false;
                }
            }


        }
        return true;
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

    public static void main(String[] args) {
        StandardBoard board = new StandardBoard();
        Solver solver = new Solver();
        solver.setBoard(board).solve();
        board.updateAllCells();
        System.out.println(board.toString());
    }
}
