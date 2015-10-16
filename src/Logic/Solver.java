package Logic;

import java.util.*;

public class Solver {
    private StandardBoard board;
    private ListIterator<Cell> iterator;
    private List<Cell> blank;
    private List<Integer> solution;
    private Cell current;
    private int count = 0;
    int index = 0;

    public boolean solves(StandardBoard board){
        this.board = board;
        blank = Util.getBlankCells(board);
        iterator = Util.getBlankCells(board).listIterator();
        current = iterator.next();
        while (true){
            if (passValue(current)) {
                if(!iterator.hasNext()){
                    return true;
                }
                current = iterator.next();
            }else{
                if(iterator.hasPrevious()) {
                    current.reset();
                    current = iterator.previous();
                }else {
                    return false;
                }
            }
        }
    }

    public void setBoard(StandardBoard board){
        this.board = board;
    }

    public boolean solve(List<Cell> cells, int limit){
        System.out.println(cells.toString());
        System.out.println(board.toString());
        for(int i : Util.randomOrderDigits()){
            System.out.println(cells.get(index) + " - " + index + " - " + i);
            if(passValue(cells.get(index),i)){
                System.out.println("in");
                if(index < cells.size()-1) {
                    index += 1;
                    solve(cells, limit);
                }else {
                    count++;
                    solution = collectSolution(cells);
                    System.out.println("up " + count);
                    cells.get(index).reset();
                    if(count == limit){
                        limit = 0;
                        return false;
                    }
                    return true;
                }
            }

        }
        index -= 1;
        cells.get(index).reset();
        System.out.println(board.toString());
        System.out.println(count);
        return false;
    }

    public List<Integer> getSolution(){
        return solution;
    }

    private List<Integer> collectSolution(List<Cell> cells){
        List<Integer> integers = new ArrayList<Integer>();
        for(Cell cell : cells){
            integers.add(cell.getValue());
        }
        return integers;
    }



    private boolean passValue(Cell cell) {
        ListIterator<Integer> iterator = cell.iterator();
        while(iterator.hasNext()) {
            if (!board.testConditions(cell, iterator.next())) {
                iterator.remove();
            }
        }
        return cell.addRandom();
    }

    private boolean passValue(Cell cell, int i) {
        if (!board.testConditions(cell, i)) {
            return false;
        }else{
            cell.setValue(i);
            return true;
        }
    }

}
