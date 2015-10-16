package Logic;

import java.util.ListIterator;

public class Creator {

    public void create() {
        StandardBoard board = new StandardBoard();

        Solver solver = new Solver();
        solver.setBoard(board).solve();
        System.out.println(board.toString());
        //digHoles(board);
        System.out.println(board.toString());
        tryOther(board);
        //System.out.println("tryother\n"+board.toString());
        board.setIterationOrder(Iteration.LINEAR);
        //solver.setBoard(board).solve();
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
        int counter = 0;
        for(Cell cell : board){
            if(!cell.isHidden()){
                //cell.hideValue(true);
                for(int i = 0; i < 9; i++){
                    board.updateBoard();
                    cell.setValue(i);
                    if(new Solver().setBoard(board).solve()) {
                        counter++;
                    }
                }
                if(counter>1){
                //    cell.hideValue(false);
                }
            }
        }
    }

    public void testCellForErasure(Cell cell, StandardBoard board){
        board.updateSingleCell(cell);
        if(cell.getAvailabilityList().size()>1) {
            cell.setValue(0);
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
}
