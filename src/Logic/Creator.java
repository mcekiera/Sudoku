package Logic;

import java.util.List;
import java.util.ListIterator;

public class Creator {
    private Solver solver;
    private Board board;
    private List<Cell> blankCells;
    private int limit;

    public Creator() {
        solver = new Solver();
    }

    public Board create(Level level) {
        board = new Board();
        getFullBoard();
        randomizeBlankCellPositions(level);
        generateBlankCells(level.getBlankCellsNumber(), level.getIterationType());
        board.save();
        return board;
    }

    public int getLimit() {
        return limit;
    }

    private void getFullBoard() {
        solver.setBoard(board).solve(board.getCells(), 1);
    }

    private void randomizeBlankCellPositions(Level level) {
        if(level.equals(Level.MODERATE) || level.equals(Level.HARD)) {
            generateBlankCells(30, Iteration.RANDOM);
        }
    }

    public void generateBlankCells(int limit, Iteration iteration) {
        this.limit = limit;
        board.setIterationOrder(iteration);
        ListIterator<Cell> iterator = board.iterator();
        board.save();

        while (iterator.hasNext()) {
            Cell current = iterator.next();
            current.save();

            if (!current.isBlank()) {
                current.setValue(0);
            } else {
                continue;
            }

            solver.setBoard(board);
            blankCells = Util.getBlankCells(board);
            if(isOutOfLimits(limit)) {
                break;
            }

            if (hasMoreThanOneSolution(blankCells)) {
                board.load();
            } else {
                current.save();
            }
        }
        board.setIterationOrder(Iteration.LINEAR);
    }

    public boolean isOutOfLimits(int limit) {
        return blankCells.size() >= limit;
    }

    private boolean hasMoreThanOneSolution(List<Cell> blanks) {
        return solver.solve(blanks,3) != 1;
    }

    public static void main (String[] args) {
        Creator creator = new Creator();
        System.out.println(creator.create(Level.VERY_EASY));
    }
}
