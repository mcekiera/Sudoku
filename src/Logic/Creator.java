package Logic;

import java.util.List;
import java.util.ListIterator;

public class Creator {
    Solver solver;
    StandardBoard board;

    public Creator(){
        solver = new Solver();
    }

    public StandardBoard create(Level level) {
        board = new StandardBoard();
        getFullBoard();
        if(level.equals(Level.MODERATE) || level.equals(Level.HARD)){
            holes(board, 30, Iteration.RANDOM);
        }
        holes(board, level.getBlankCellsNumber(), level.getIterationType());
        board.save();
        return board;
    }

    private void getFullBoard(){
        solver.setBoard(board).solve(board.getCells(), 1);
    }

    public void holes(StandardBoard board, int limit, Iteration iteration){
        List<Cell> blanks;
        board.setIterationOrder(iteration);
        ListIterator<Cell> iterator = board.iterator();
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
            if (solver.solve(blanks,5) != 1) {
                board.load();
            }else{
                current.save();
            }
            current = iterator.next();
        }
        board.setIterationOrder(Iteration.LINEAR);
    }



    public static void main (String[] args) {
        //for(int i = 0; i < 1000; i++) {
        Creator creator = new Creator();
        System.out.println(creator.create(Level.VERY_HARD));
        //}
    }
}
