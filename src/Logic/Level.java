package Logic;

import java.util.Random;

public enum Level {

    VERY_EASY(0,30,Iteration.RANDOM),
    EASY(31,44,Iteration.RANDOM),
    MODERATE(45,49,Iteration.S_SHAPE),
    HARD(40,54,Iteration.S_SHAPE),
    VERY_HARD(55,61,Iteration.LINEAR);

    private final int min;
    private final int max;
    private int blankCells;
    private final Iteration iterationType;

    Level(int min, int max, Iteration iterationType){
        this.min = min;
        this.max = max;
        this.iterationType = iterationType;
    }

    public int getBlankCellsNumber(){
        return new Random().nextInt((max - min) + 1) + min;
    }

    public Iteration getIterationType(){
        return iterationType;
    }



}
