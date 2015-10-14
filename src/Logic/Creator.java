package Logic;

import java.util.ArrayList;
import java.util.List;

public class Creator {

    public void create() {
        StandardBoard board = new StandardBoard();
        Solver solver = new Solver();
        solver.setBoard(board).solve();
        System.out.println(board.toString());
        digHoles(board);
        tryOther(board);
        System.out.println(board.toString());
        board.setIteration(Iteration.LINEAR);
        solver.setBoard(board).solve();
        System.out.println(board.toString());

    }

    public StandardBoard digHoles(StandardBoard board) {
        board.setIteration(Iteration.S_SHAPE);
        for(Cell cell : board) {
            testCellForErasure(cell, board);
        }
        board.setIteration(Iteration.LINEAR);

        return board;
    }

    public void tryOther(StandardBoard board){
        List<Cell> filled = new ArrayList<Cell>();
        for(Cell cell : board){
            if(cell.getValue()!=0){
                filled.add(cell);
                System.out.println(cell.getAvailabilityList());
            }
        }
        System.out.println(filled);
    }

    public void testCellForErasure(Cell cell, StandardBoard board){
        int tempValue = cell.getValue();
        cell.setValue(0);
        board.updateSingleCell(cell);
        if(cell.getAvailabilityList().size()>1) {
            cell.setValue(tempValue);
        }
    }

    public static void main (String[] args) {
            Creator creator = new Creator();
            creator.create();
    }
}
