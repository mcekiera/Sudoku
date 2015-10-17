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
            generateBlankCells(30, Iteration.RANDOM);
        }
        generateBlankCells(level.getBlankCellsNumber(), level.getIterationType());
        board.save();
        return board;
    }

    private void getFullBoard(){
        solver.setBoard(board).solve(board.getCells(), 1);
    }

    public void generateBlankCells(int limit, Iteration iteration){
        List<Cell> blanks;
        board.setIterationOrder(iteration);
        ListIterator<Cell> iterator = board.iterator();
        Cell current;
        board.save();

        while (iterator.hasNext()){
            current = iterator.next();
            current.save();
            if (current.isBlank()){
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
        }
        board.setIterationOrder(Iteration.LINEAR);
    }



    public static void main (String[] args) {
        Creator creator = new Creator();
        System.out.println(creator.create(Level.MODERATE));
    }
}
