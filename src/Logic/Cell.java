package Logic;

import java.util.*;

public class Cell implements Iterable<Integer>{
    private int row;
    private int column;
    private int value;
    private int block;
    private boolean pre = false;
    private List<Integer> availabilityList;

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        availabilityList = randomOrderList();
        block = specifyBlock(row,column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void setValue(int value){
        this.value = value;
        availabilityList.remove(Integer.valueOf(value));
    }

    public int getValue(){
        return value;
    }

    public int getBlock(){
        return block;
    }

    public boolean passValue(int value){
        return availabilityList.contains(Integer.valueOf(value));
    }

    public boolean addRandom(){
        if(availabilityList.isEmpty()){
            return false;
        }else {
            value = availabilityList.get(0);
            return true;
        }
    }

    public void excludeValue(int value){
        availabilityList.remove(Integer.valueOf(value));
    }

    public ListIterator getAvailabilityList(){
        return availabilityList.listIterator();
    }

    public void reset(){
        value = 0;
        availabilityList = randomOrderList();
    }

    public void clearMemory(){
        availabilityList = randomOrderList();
    }

    private static int specifyBlock(int row, int column){
        int x = row /3;
        int y = column /3;
        int modifier = row <3 ? 0 : row <6 ? 2 : 4;
        return x+y+modifier;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }else if(obj.getClass() == this.getClass()){
            if(((Cell)obj).getValue() == this.getValue() && ((Cell)obj).getRow() == this.getRow() && ((Cell)obj).getColumn() == this.getColumn()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static List<Integer> randomOrderList(){
        List<Integer> values = new ArrayList<Integer>();
        for(int i = 1; i <= 9; i++){
            values.add(i);
        }
        Collections.shuffle(values);
        return values;
    }






    public void setPre(boolean pre) {
        this.pre = pre;
    }


    public boolean isPre(){
        return pre;
    }

    @Override
    public ListIterator<Integer> iterator() {
        return availabilityList.listIterator();
    }
}
