package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solver object solves a given Sudoku board. It is used in stage of filling an empty Sudoku board with digits,
 * in verifying the uniqueness of solution and in checking a solution given by User.
 */
public class Solver {
    private Board board;
    private int count = 0;
    int index = 0;

    public Solver setBoard(Board board){
        this.board = board;
        count = 0;
        index = 0;
        return this;
    }

    /**
     * Recursive and backtracking method, which fills up the empty or partially solved Sudoku game board. It puts
     * a digit into the blank Cell objects, if it pass the game conditions. If there is no such value, it backtrace
     * to the last filled Cell object, which value could be changed. In effect it creates or solves a Sudoku puzzle.
     * @param blankCells List of blank Cell objects from given Sudoku game board.
     * @param limit randomly selected number of blank Cells the Sudoku board on given difficulty level could have.
     * @return the number of found solutions.
     */
    public int solve(List<Cell> blankCells, int limit){
        if(index < blankCells.size()){
            for(int i : randomOrderDigits()){
                if(testValue(blankCells.get(index), i)){
                    index += 1;
                    if(solve(blankCells, limit)>= limit){
                        return count;
                    }
                }
            }
            return backtrace(blankCells);
        } else {
            return finish();
        }
    }

    /**
     * Part of solve() method responsible for decrease of specified variables, what effects in backtracking during the
     * solve() method execution. It returns a same value as the salve() method, because if there is no more solutions
     * for given game board, and therefore solve() method would be terminated, it still returns counted number of
     * found solutions.
     * @param cells List of blank Cell objects from given Sudoku game board.
     * @return the number of found solution.
     */
    private int backtrace(List<Cell> cells){
        cells.get(index).setValue(0);
        index -= 1;
        return count;
    }

    /**
     * Increases the value of variable which holds the number of found solutions, and begin a backtracking process,
     * after the solve() method reaches a valid solution for the given game board. It returns a same value as the
     * salve() method, because if there is no more solutions for given game board, and therefore solve() method would
     * be terminated, it still returns counted number of found solutions.
     * @return the number of found solution.
     */
    public int finish(){
        count++;
        index -= 1;
        return count;
    }

    /**
     * Tests the game conditions for a given Cell object and value.
     * @param cell the tested Cell object.
     * @param i value which could be potentially placed in the given Cell object.
     * @return true if the Call with the given value fulfill the game conditions.
     */
    private boolean testValue(Cell cell, int i) {
        if (!board.testConditions(cell, i)) {
            return false;
        } else{
            cell.setValue(i);
            return true;
        }
    }

    /**
     * @return return a List containing a digits (without 0) in random order.
     */
    public List<Integer> randomOrderDigits() {
        List<Integer> values = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++) {
            values.add(i);
        }
        Collections.shuffle(values);
        return values;
    }
}
