package Logic;

import java.util.Random;

/**
 * Enum class for difficulty levels. Its min and max fields set a range, from which the number of blank cells
 * is randomly selected.
 */
public enum Level {

    VERY_EASY(28,30,Iteration.RANDOM),
    EASY(31,44,Iteration.RANDOM),
    MODERATE(45,49,Iteration.S_LIKE),
    HARD(49,54,Iteration.S_LIKE),
    VERY_HARD(55,61,Iteration.LINEAR);

    final private int min;
    final private  int max;
    final private  Iteration iterationType;

    Level(int min, int max, Iteration iterationType) {
        this.min = min;
        this.max = max;
        this.iterationType = iterationType;
    }

    /**
     * It causes a differentiation of number of blank cells in game boards on the same difficulty level,
     * in separate games.
     * @return random number from range from minimal to maximal number of blank cells for given difficulty level.
     */
    public int getBlankCellsNumber(){
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * @return a iteration type used in creation of game boards on given difficulty level.
     */
    public Iteration getIterationType(){
        return iterationType;
    }



}
