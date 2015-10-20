package GUI;

import Logic.Board;
import Logic.Cell;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Graphic representation of Sudoku grid.
 */
public class Grid extends Container implements Iterable<Field>{
    List<Field> fields;

    public Grid(Board board){
        setLayout(new BorderLayout());
        fields = new ArrayList<Field>();
        add(createGrid(board.getBlocks()));
    }

    /**
     * Creates Sudoku grid of Fields.
     * @param blocks List of cells divided into groups, according to Sudoku game board devision.
     * @return grid of Fields.
     */
    public JPanel createGrid(List<List<Cell>> blocks){
        reset();
        JPanel grid = new JPanel(new GridLayout(3,3,2,2));
        grid.setBackground(Color.LIGHT_GRAY);
        for(List<Cell> block : blocks){
            grid.add(createBlock(block));
        }
        add(grid);          //TODO refactore here!!! return and add, without add doesn't work!
        return grid;
    }

    /**
     * Creates single Sudoku block with 9 fields.
     * @param block
     * @return
     */
    public JPanel createBlock(List<Cell> block){
        JPanel blockPanel = new JPanel(new GridLayout(3,4));
        for(Cell cell : block){
            Field field = new Field(cell);
            fields.add(field);
            blockPanel.add(field);
        }
        blockPanel.setBorder(new LineBorder(Color.black, 1));
        return blockPanel;
    }

    /**
     * Removes grid of Fields from background panel.
     */
    private void reset(){
        removeAll();
        fields.clear();
    }

    /**
     * Provides capability to iterate through Fields of game board.
     * @return Iterator object.
     */
    @Override
    public Iterator<Field> iterator() {
        return fields.iterator();
    }
}
