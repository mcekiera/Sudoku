package Logic;

public enum Level {

    VERY_EASY(30,Iteration.RANDOM),
    EASY(45,Iteration.RANDOM),
    MODERATE(49,Iteration.RANDOM),
    HARD(54,Iteration.S_SHAPE),
    VARY_HARD(61,Iteration.LINEAR);

    private final int blankCells;
    private final Iteration iterationType;

    Level(int blankCells, Iteration iterationType){
        this.blankCells = blankCells;
        this.iterationType = iterationType;

    }



}
