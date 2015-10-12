package Logic;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private int x;
    private int y;
    private int value;
    private int group;
    private Set<Integer> triedValues;
    private boolean filled = false;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        triedValues = new HashSet<Integer>(9);
    }

    public void setValue(int value){
        this.value = value;
        triedValues.add(value);
    }

    public int getValue(){
        return value;
    }

    public int getGroup(){
        return identifyGroup();
    }

    public int identifyGroup(){
        int xx = x/3;
        int yy = y/3;
        int r = x<3 ? 0 : x<6 ? 2 :4;
        return xx+yy+r;
    }

    public boolean isValid(int i){
        return triedValues.isEmpty() || !triedValues.contains(Integer.valueOf(i));
    }

    public void reset(){
        value = 0;
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
            if(((Cell)obj).getValue() == this.getValue()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
