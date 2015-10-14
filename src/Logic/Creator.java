package Logic;

import java.util.ArrayList;

public class Creator {

    public StandardBoard create(){
        StandardBoard board = new StandardBoard();
        Solver solver = new Solver();
        board = solver.setBoard(board).solve();
        System.out.println(board.toString());
        board = digHoles(board);
        System.out.println(board.toString());
        return null;
    }

    public StandardBoard digHoles(StandardBoard board){
        int tempValue;
        ArrayList<Cell> doubles = new ArrayList<Cell>();
        board.setIteration(Iteration.RANDOM);
        for(Cell cell : board){
            tempValue = cell.getValue();
            cell.setValue(0);
            board.updateSingleCell(cell);
            if(cell.getAvailabilityList().size()>1){
                cell.setValue(tempValue);
            }
        }

        board.setIteration(Iteration.LINEAR);

        return board;
    }

    public static void main (String[] args){
        Creator creator = new Creator();
        creator.create();
    }
}
