package Logic;

import java.util.ListIterator;

public class Solver {
    private StandardBoard board;
    private ListIterator<Cell> iterator;
    private Cell current;

    public Solver setBoard(StandardBoard board){
        this.board = board;
        iterator = board.iterator();
        current = iterator.next();
        return this;
    }

    public StandardBoard solve() {
        if(passValue(current)) {
            if(iterator.hasNext()) {
                current = iterator.next();
                return solve();
            }else {
                return board;
            }
        }else {
            current.reset();
            current = iterator.previous();
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
