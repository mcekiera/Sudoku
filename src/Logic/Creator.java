package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Creator {

    public void create() {
        StandardBoard board = new StandardBoard();

        Solver solver = new Solver();
        solver.setBoard(board).solve();
        System.out.println(board.toString());
        digHoles(board);
        System.out.println(board.toString());
        //tryOther(board);
        System.out.println("tryother\n"+board.toString());
        board.setIterationOrder(Iteration.LINEAR);
        solver.setBoard(board).solve();
        System.out.println(board.toString());

    }

    public StandardBoard digHoles(StandardBoard board) {
        board.setIterationOrder(Iteration.RANDOM);
        for(Cell cell : board) {
            testCellForErasure(cell, board);
        }
        board.setIterationOrder(Iteration.LINEAR);

        return board;
    }

    public void tryOther(StandardBoard board){
        List<Cell> filled = new ArrayList<Cell>();
        for(Cell cell : board){

            if(!cell.isHidden()){
                filled.add(cell);
                System.out.println(cell.getAvailabilityList());
            }
        }
        for(Cell cell : filled){
            cell.hideValue(true);
            for(int i : cell.getAvailabilityList()){
                cell.setValue(i);
                if(new Solver().setBoard(board).solve()){
                    if(i != cell.getHiddenValue()){
                        cell.hideValue(false);
                        break;
                    }
                }
            }
        }
    }

    public void testCellForErasure(Cell cell, StandardBoard board){
        cell.hideValue(true);
        board.updateSingleCell(cell);
        if(cell.getAvailabilityList().size()>1) {
            cell.setValue(0);
            cell.hideValue(false);
        }
    }

    public StandardBoard copy(StandardBoard board){
        StandardBoard copy = new StandardBoard();
        ListIterator<Cell> cellListIterator = board.iterator();
        for(Cell cell : copy){
            cell.setValue(cellListIterator.next().getValue());
        }
        return copy;
    }

    public static void main (String[] args) {
            Creator creator = new Creator();
            creator.create();
    }
}
