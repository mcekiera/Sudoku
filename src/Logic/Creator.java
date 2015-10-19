package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Creates a Sudoku game board according to given parameters.
 */
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

    /**
     * @return a value of limit for blank cells for given Sudoku board.
     */
    public int getLimit() {
        return limit;
    }

    public Board getBoard(){
        return board;
    }

    /**
     * Generates game board full of numbers.
     */
    private void getFullBoard() {
        solver.setBoard(board).solve(board.getCells(), 1);
    }

    /**
     * It is used before generateBlankCells() method in cases when S-like iteration type is used with number of blank
     * Cells lower or near the half of Cells number. This prevents the game board to be unevenly distributed on board.
     * @param level which determines iteration type.
     */
    private void randomizeBlankCellPositions(Level level) {
        if(level.equals(Level.MODERATE) || level.equals(Level.HARD)) {
            generateBlankCells(30, Iteration.RANDOM);
        }
    }

    /**
     * Generates a blank Cells in full Sudoku game board.
     * @param limit number of blank cells given puzzle should have.
     * @param iteration used iteration type through Boards cells.
     */
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
            blankCells = getBlankCells(board);
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

    /**
     * Determines when there is too many number of solution for given type of task.
     * @param limit restraining value.
     * @return true if there is more or equal number of blank Cells in caparison to demanded fo given difficulty level,
     */
    public boolean isOutOfLimits(int limit) {
        return blankCells.size() >= limit;
    }

    /**
     * @param blanks List of blank Cells, which combination with hint numbers, is tested for solution uniqueness.
     * @return true if given set of values has one solution.
     */
    private boolean hasMoreThanOneSolution(List<Cell> blanks) {
        return solver.solve(blanks, 3) != 1;
    }

    /**
     * @param board Sudoku game board.
     * @return List of blank Cells in given Board.
     */
    public static List<Cell> getBlankCells(Board board) {
        List<Cell> blank = new ArrayList<Cell>();
        for(Cell cell : board) {
            if(cell.isBlank()) {
                blank.add(cell);
            }
        }
        return blank;
    }
}
