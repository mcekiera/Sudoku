package GUI;

import Logic.Board;
import Logic.Cell;
import Logic.Creator;
import Logic.Level;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    public JPanel getOptions(){
        JPanel panel = new JPanel();
        String[] levels = {Level.VERY_EASY.name(),Level.EASY.name(),Level.MODERATE.name(),Level.HARD.name(),Level.VERY_HARD.name()};
        JComboBox<String> combo = new JComboBox<String>(levels);
        JButton generate = new JButton("GENERATE");
        JButton resutl = new JButton("RESULT");
        panel.add(combo);
        panel.add(generate);
        panel.add(resutl);
        return panel;
    }

    public static void main(String[] args){
        String[] levels = {Level.VERY_EASY.name(),Level.EASY.name(),Level.MODERATE.name(),Level.HARD.name(),Level.VERY_HARD.name()};
        String input = (String) JOptionPane.showInputDialog(null, "Choose difficulty level: ",
                "Difficulty level", JOptionPane.QUESTION_MESSAGE, null, levels, levels[1]);
        System.out.println(input);
        Creator creator = new Creator();
        Board board = creator.create(Level.valueOf(input));
        System.out.println(board);
        JFrame frame = new JFrame();
        JButton button = new JButton("CHECK SOLUTION");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Grid grid = new Grid();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(grid.getBlocks(board.getBlocks()));
        frame.add(grid.getOptions(),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,600);
    }
}
