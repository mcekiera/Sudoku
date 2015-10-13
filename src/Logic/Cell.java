package Logic;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private int row;
    private int column;
    private int value;
    private int group;
    private Set<Integer> triedValues;

    public Cell(int x, int y){
        this.row = x;
        this.column = y;
        triedValues = new HashSet<Integer>(9);
        group = identifyGroup();
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void setValue(int value){
        this.value = value;
        triedValues.add(value);
    }

    public int getValue(){
        return value;
    }

    public int getGroup(){
        return group;
    }

    private int identifyGroup(){
        int x = row /3;
        int y = column /3;
        int modifier = row <3 ? 0 : row <6 ? 2 : 4;
        return x+y+modifier;
    }

    public boolean isValidValue(int i){
        return triedValues.isEmpty() || !triedValues.contains(Integer.valueOf(i));
    }

    public void reset(){
        value = 0;
        triedValues.clear();
    }

    public void clearMemory(){
        triedValues.clear();
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

}
