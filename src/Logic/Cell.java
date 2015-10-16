package Logic;

import java.util.*;

public class Cell implements Iterable<Integer>{
    final private int row;
    final private int column;
    final private int block;
    private int testValue;
    private int value;
    private boolean isHidden;
    private List<Integer> availabilityList;

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        availabilityList = Util.randomOrderDigits();
        block = Util.specifyBlock(row, column);
        isHidden = true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void hideValue(boolean state){
        isHidden = state;
    }

    public boolean isHidden(){
        return isHidden;
    }

    public void setValue(int value){

            this.value = value;

    }

    public int getValue(){
        return  value;
    }

    public int getHiddenValue(){
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
            availabilityList.remove(Integer.valueOf(value));
            return true;
        }
    }

    public void presetValue(int value){
        this.value = value;
        availabilityList.clear();
    }

    public void excludeValue(int value){
        availabilityList.remove(Integer.valueOf(value));
    }

    public ListIterator availabilityList(){
        return availabilityList.listIterator();
    }

    public List<Integer> getAvailabilityList(){
        return availabilityList;
    }



    public void reset(){
        value = 0;
        availabilityList = Util.randomOrderDigits();
    }

    public void clearMemory(){
        availabilityList = Util.randomOrderDigits();
    }

    @Override
    public String toString(){
        return getRow() + "," + getColumn() + ": " + String.valueOf(getValue()) + " - " + getAvailabilityList();
    }

    public String toStringVal(){
        return String.valueOf(getValue());
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

    @Override
    public ListIterator<Integer> iterator() {
        return availabilityList.listIterator();
    }
}
