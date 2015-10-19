package GUI;

import Logic.Board;
import Logic.Cell;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid extends Container implements Iterable<Field>{
    List<Field> fields;

    public Grid(Board board){
        setLayout(new BorderLayout());
        fields = new ArrayList<Field>();
        add(getBlocks(board.getBlocks()));
    }

    public JPanel getBlocks(List<List<Cell>> blocks){
        fields.clear();
        JPanel grid = new JPanel(new GridLayout(3,3,2,2));
        grid.setBackground(Color.LIGHT_GRAY);
        for(List<Cell> block : blocks){
            JPanel blockPanel = new JPanel(new GridLayout(3,4));
            for(Cell cell : block){
                Field field = new Field(cell);
                fields.add(field);
                blockPanel.add(field);
            }
            blockPanel.setBorder(new LineBorder(Color.black,1));
            grid.add(blockPanel);
        }
        return grid;
    }

    @Override
    public Iterator<Field> iterator() {
        return fields.iterator();
    }
}
