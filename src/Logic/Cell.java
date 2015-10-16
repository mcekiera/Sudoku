package Logic;

import java.util.*;

public class Cell implements Iterable<Integer>{
    final private int row;
    final private int column;
    final private int block;
    private int value;
    private int saved;
    private List<Integer> availableValues;

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        availableValues = Util.randomOrderDigits();
        block = Util.specifyBlock(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return  value;
    }

    public int getBlock(){
        return block;
    }

    public boolean addRandom(){
        if(availableValues.isEmpty()){
            return false;
        }else {
            value = availableValues.get(0);
            availableValues.remove(Integer.valueOf(value));
            return true;
        }
    }

    public void excludeValue(int value){
        availableValues.remove(Integer.valueOf(value));
    }

    public List<Integer> availableValues(){
        return availableValues;
    }

    public void reset(){
        value = 0;
        availableValues = Util.randomOrderDigits();
    }

    public void clearMemory(){
        availableValues = Util.randomOrderDigits();
    }

    @Override
    public String toString(){
        return getRow() + "," + getColumn() + ":" + String.valueOf(getValue());
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
        return availableValues.listIterator();
    }

    public void save(){
        saved = value;
    }

    public void load(){
        value = saved;
    }
}
