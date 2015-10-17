package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Util {


    public static <T> List<T> sShapedList(T[][] grid){
        List<T> sShape = new ArrayList<T>();
        List<T> temp;
        for(int i = 0; i < 9; i++){
            if((i+1)%2==0){
                temp = new ArrayList<T>(Arrays.asList(grid[i]));
                Collections.reverse(temp);
                sShape.addAll(temp);
            }else{
                sShape.addAll(Arrays.asList(grid[i]));
            }
        }
        return sShape;
    }

    public static <T> List<T> jumpOneCell(List<T> list){
        List<T> result = new ArrayList<T>(list.size());
        for (int i = 0, j = 0; i <= list.size(); i += 2, j++) {
            if(j == list.size()-2){
                break;
            }
            if(i == list.size()-1){
                i = 1;
            }
            result.add(list.get(i));
        }
        return result;
    }

    public static int specifyBlock(int row, int column){
        int x = row /3;
        int y = column /3;
        int modifier = row <3 ? 0 : row <6 ? 2 : 4;
        return x+y+modifier;
    }

    public static List<Integer> randomOrderDigits(){
        List<Integer> values = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++){
            values.add(i);
        }
        Collections.shuffle(values);
        return values;
    }

    public static <T> List<List<T>> createBlocks(int capacity){
        List<List<T>> list = new ArrayList<List<T>>(capacity);
        for(int i = 0; i < capacity; i++){
            List<T> temp = new ArrayList<T>();
            list.add(temp);
        }
        return list;
    }

    public static ArrayList<Cell> getBlankCells(StandardBoard board){
        ArrayList<Cell> blank = new ArrayList<Cell>();
        for(Cell cell : board){
            if(cell.getValue()==0){
                blank.add(cell);
            }
        }
        System.out.println("Utils BLANKS: "+blank.size());
        return blank;
    }
}
