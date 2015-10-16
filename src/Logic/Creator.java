package Logic;

public class Creator {
    Solver solver;

    public void create() {
        StandardBoard board = new StandardBoard();

        solver = new Solver();
        board.setIterationOrder(Iteration.LINEAR);
        solver.solve(board);
        System.out.println(board.toString());
        board = digHoles(board);
        //tryOtherCells(board);
        System.out.println(board.toString());
        //System.out.println("tryother\n"+board.toString());
        board.setIterationOrder(Iteration.LINEAR);
        solver.solve(board);
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

    public void testCellForErasure(Cell cell, StandardBoard board){
        int temp = cell.getValue();
        cell.setValue(0);
        board.updateSingleCell(cell);
        if(cell.availableValues().size()>1) {
            cell.setValue(temp);
        }
    }

    public void tryOtherCells(StandardBoard board){
        int count = 0;
        for(Cell cell : board){
            System.out.println(cell);
            if(cell.getValue()!=0){
                cell.save();
                for(int i = 0; i < 9; i++){
                    cell.setValue(i);
                    if(solver.solve(board)){
                        count++;
                        System.out.println(count);
                    }
                }

            }
        }


    }

    public static void main (String[] args) {
        //for(int i = 0; i < 1000; i++) {
            Creator creator = new Creator();
            creator.create();
        //}
    }
}
