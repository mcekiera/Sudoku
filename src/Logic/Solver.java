package Logic;

import java.util.*;

public class Solver {
    private StandardBoard board;
    private ListIterator<Cell> iterator;
    private List<Integer> size;
    private Cell current;

    public Solver setBoard(StandardBoard board){
        this.board = board;
        iterator = board.iterator();
        current = iterator.next();
        size = new ArrayList<Integer>(81);
        return this;
    }

    public boolean solve() {
            if (current.getValue()!=0 || passValue(current)) {
                if (iterator.hasNext()) {
                    current = iterator.next();

                    return solve();
                } else {
                    return true;
                }
            } else {
                current.reset();
                current = iterator.previous();
                current.setValue(0);
                return solve();
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

    public static void main(String[] args) {
        StandardBoard board = new StandardBoard();
        Solver solver = new Solver();
        solver.setBoard(board).solve();
        board.updateAllCells();
        System.out.println(board.toString());
    }
}
