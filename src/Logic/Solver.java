package Logic;

import java.util.ListIterator;

public class Solver {
    StandardBoard board;
    ListIterator<Cell> iterator;
    Cell current;

    public Solver(StandardBoard board){
        this.board = board;
        iterator = board.iterator();
        current = iterator.next();
    }

    public StandardBoard solve(){
        if(iterator.hasNext()){
            if(passValue(iterator.next())) {
                current = iterator.next();
                return solve();
            }else{
                current.reset();
                current = iterator.previous();
                return solve();
            }
        }else {
            return board;
        }
    }

    private boolean passValue(Cell cell){
        for(int i : cell.getAvailabilityList()){
            if(!board.testConditions(cell,i)){
                cell.excludeValue(i);
            }
        }

        return cell.addRandom();
    }
}
