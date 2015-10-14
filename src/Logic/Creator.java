package Logic;

public class Creator {

    public void create() {
            StandardBoard board = new StandardBoard();
            Solver solver = new Solver();
            board = solver.setBoard(board).solve();
        System.out.println(board.toString());
            board = digHoles(board);
        System.out.println(board.toString());
            board.setIteration(Iteration.LINEAR);
        board.updateAllCells();
            board = solver.setBoard(board).solve();

    }

    public StandardBoard digHoles(StandardBoard board) {
        int tempValue;
        board.setIteration(Iteration.RANDOM);
        for(Cell cell : board) {
            tempValue = cell.getValue();
            cell.setValue(0);
            board.updateSingleCell(cell);
            if(cell.getAvailabilityList().size()>1) {
                cell.setValue(tempValue);
            }
        }

        board.setIteration(Iteration.LINEAR);

        return board;
    }

    /**public StandardBoard tryOther(StandardBoard board){
        List<Cell> filled = new ArrayList<Cell>();
        for(Cell cell : board){
            if(cell.getValue()!=0){
                filled.add(cell);
            }
        }
        for(Cell cell : filled){

        }
    }   */


    public static void main (String[] args) {
        for(int i = 0; i < 10; i++) {
            Creator creator = new Creator();
            creator.create();
        }
    }
}
