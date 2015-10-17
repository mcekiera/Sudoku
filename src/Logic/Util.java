package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Util {


    public static <T> List<T> s_ShapedList(T[][] grid) {
        List<T> sShape = new ArrayList<T>();
        List<T> temp;
        for(int i = 0; i < 9; i++) {
            if((i+1)%2==0) {
                temp = new ArrayList<T>(Arrays.asList(grid[i]));
                Collections.reverse(temp);
                sShape.addAll(temp);
            } else {
                sShape.addAll(Arrays.asList(grid[i]));
            }
        }
        return sShape;
    }

    public static List<Integer> randomOrderDigits() {
        List<Integer> values = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++) {
            values.add(i);
        }
        Collections.shuffle(values);
        return values;
    }

    public static <T> List<List<T>> createBlocks(int capacity) {
        List<List<T>> list = new ArrayList<List<T>>(capacity);
        for(int i = 0; i < capacity; i++) {
            List<T> temp = new ArrayList<T>();
            list.add(temp);
        }
        return list;
    }

    public static int specifyBlock(int row, int column) {
        int x = row /3;
        int y = column /3;
        int modifier = row <3 ? 0 : row <6 ? 2 : 4;
        return x+y+modifier;
    }

    public static ArrayList<Cell> getBlankCells(Board board) {
        ArrayList<Cell> blank = new ArrayList<Cell>();
        for(Cell cell : board) {
            if(cell.isBlank()) {
                blank.add(cell);
            }
        }
        return blank;
    }
}
