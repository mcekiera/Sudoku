package Logic;

import java.util.ArrayList;

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
        solver.solve(board.getCells(),1);
        System.out.println(board.toString());
        //board = digHoles(board);
        //tryOtherCells(board);
        System.out.println(board.toString());
        //System.out.println("tryother\n"+board.toString());
        //board.setIterationOrder(Iteration.LINEAR);
        //for(Cell cell : board){
        //    cell.setValue(list[i++]);
        //}
        solver.setBoard(board);
        solver.solve(Util.getBlankCells(board), 1);
        //System.out.println(solver.getSolution());
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
        ArrayList<Integer> values = new ArrayList<Integer>();
        for(Cell cell : board){
            values.add(cell.getValue());
        }
        int count = 0;
        for(Cell cell : board){
            System.out.println(cell);
            if(cell.getValue()!=0){
                cell.save();
                for(int i = 0; i < 9; i++){
                    cell.setValue(i);
                    if(solver.solve(board.getCells(),0)>0){
                        count++;
                        System.out.println(count);
                    }
                    int k = 0;
                    for(Cell cellx : board){
                        cellx.setValue(values.get(k));
                        k++;

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
