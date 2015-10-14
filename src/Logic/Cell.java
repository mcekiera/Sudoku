package Logic;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private int row;
    private int column;
    private int value;
    private int block;
    private boolean pre = false;

    private Set<Integer> testedValues;
    private Set<Integer> valueSet;

    public Cell(int x, int y){
        this.row = x;
        this.column = y;
        testedValues = new HashSet<Integer>(9);
        valueSet = createValueSet();
        block = specifyBlock(x,y);
    }

    public int getRow() {
        return row;
    }

    public int getColumn(){
        return column;
    }

    public void setValue(int value){
        this.value = value;
        testedValues.add(value);
        valueSet.remove(value);
    }

    public int getValue(){
        return value;
    }

    public int getBlock(){
        return block;
    }

    public boolean passValue(int i){
        return testedValues.isEmpty() || !testedValues.contains(i);
    }

    public void reset(){
        value = 0;
        testedValues.clear();
    }

    public void clearMemory(){
        testedValues.clear();
    }

    private static int specifyBlock(int row, int column){
        int x = row /3;
        int y = column /3;
        int modifier = row <3 ? 0 : row <6 ? 2 : 4;
        return x+y+modifier;
    }

    public static Set<Integer> createValueSet(){
        Set<Integer> values = new HashSet<Integer>();
        for(int i = 1; i <= 9; i++){
            values.add(i);
        }
        return values;
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




    public void setPre(boolean pre) {
        this.pre = pre;
    }


    public boolean isPre(){
        return pre;
    }

}
