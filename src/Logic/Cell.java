package Logic;

import java.util.*;

public class Cell implements Iterable<Integer>{
    final private int row;
    final private int column;
    final private int block;
    private int value;
    private int save;
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

    public boolean isBlank(){
        return value==0;
    }

    public void reset(){
        value = 0;
        availableValues = Util.randomOrderDigits();
    }

    public void save(){
        save = value;
    }

    public void load(){
        value = save;
    }

    @Override
    public String toString(){
        return String.valueOf(getValue());
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }else if(obj.getClass() == this.getClass()){
            return ((Cell) obj).getValue() == this.getValue() && ((Cell) obj).getRow() == this.getRow() && ((Cell) obj).getColumn() == this.getColumn();
        }else{
            return false;
        }
    }

    @Override
    public ListIterator<Integer> iterator() {
        return availableValues.listIterator();
    }
}
