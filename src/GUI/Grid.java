package GUI;

import Logic.Board;
import Logic.Cell;
import Logic.Creator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import static Logic.Level.*;

public class Grid {


    public JPanel getBlocks(List<List<Cell>> blocks){
        JPanel grid = new JPanel(new GridLayout(3,3,2,2));
        grid.setBackground(Color.LIGHT_GRAY);
        for(List<Cell> block : blocks){
            JPanel blockPanel = new JPanel(new GridLayout(3,4));
            for(Cell cell : block){
                Field field = new Field(cell);
                blockPanel.add(field);
            }
            blockPanel.setBorder(new LineBorder(Color.black,1));
            grid.add(blockPanel);
        }
        return grid;
    }

    public static void main(String[] args){
        Creator creator = new Creator();
        Board board = creator.create(VERY_EASY);
        JFrame frame = new JFrame();
        Grid grid = new Grid();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(grid.getBlocks(board.getBlocks()));
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,600);
    }
}
