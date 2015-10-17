package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Creator {
    Solver solver;

    public void create() {
        StandardBoard board = new StandardBoard();
        int i = 0;
        int[] list = {0,0,0,4,7,0,0,3,0,
                0,0,0,2,0,5,9,6,0,
                0,0,3,0,0,9,0,2,4,
                0,0,0,0,0,4,5,0,9,
                0,0,0,5,0,1,3,0,0,
                0,0,0,7,0,0,4,1,2,
                0,0,9,1,8,2,6,4,0,
                0,0,2,6,0,0,0,9,1,
                0,0,6,9,0,7,2,5,0};
        int[] list2 = {0,9,0,4,7,0,0,3,0,
                0,1,0,2,0,5,9, 6, 0,
                0,5,3,0,0,9,0,2,4,
                8,0,0,0,0,4,5,0,9,
                9,4,0,5,0,1,3,0,0,
                0,6,0,7,0,0,4,1,2,
                0,0,9,1,8,2,6,4,0,
                4,7,2,6,0,0,0,9,1,
                1,0,6,9,0,7,2,5,0};
        solver = new Solver();
        board.setIterationOrder(Iteration.LINEAR);
        solver.setBoard(board);
        solver.solve(board.getCells(), 1);
        System.out.println(board.toString());

        //board = digHoles(board);
        //board.save();

        holes(board,40, Iteration.EVERY_SECOND);
        //System.out.println("HOLES:\n" + board.toString());
        //Util.getBlankCells(board);
        //tryOtherCells(board);
        //System.out.println(board.toString());
        //System.out.println("tryother\n"+board.toString());
        //board.setIterationOrder(Iteration.LINEAR);
        //for(Cell cell : board){
        //    cell.setValue(list[i++]);
        //}
        System.out.println(board.toString());
        solver.setBoard(board);
        System.out.println("T: " + solver.getTrialsNumber());
        solver.solve(Util.getBlankCells(board), 1);

        System.out.println("T2: " + solver.getTrialsNumber());
        System.out.println(board.toString());
        System.out.println(board.getCells());
        System.out.println(Util.jumpOneCell(board.getCells()));

    }

    public StandardBoard digHoles(StandardBoard board) {
        board.setIterationOrder(Iteration.RANDOM);
        for(Cell cell : board) {
            testCellForErasure(cell, board);
        }
        board.setIterationOrder(Iteration.LINEAR);

        return board;
    }

    public void testCellForErasure(Cell cell, StandardBoard board){
        int temp = cell.getValue();
        cell.setValue(0);
        board.updateSingleCell(cell);
        if(cell.availableValues().size()>1) {
            cell.setValue(temp);
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
        //for(int i = 0; i < 1000; i++) {
            Creator creator = new Creator();
            creator.create();
        //}
    }

    public void holes(StandardBoard board, int limit, Iteration iteration){
        board.setIterationOrder(iteration);
        ListIterator<Cell> iterator = board.iterator();
        List<Cell> blanks = new ArrayList<Cell>();
        Cell current = iterator.next();
        board.save();
        while (iterator.hasNext()){
            current.save();
            if (current.getValue()!=0){
                current.setValue(0);
            }
            solver.setBoard(board);
            blanks = Util.getBlankCells(board);
            if(blanks.size() >= limit) {
                break;
            }
            if (solver.solve(blanks,2) != 1) {
                board.load();
            }else{
                current.save();
            }
            current = iterator.next();
        }
    }
}
