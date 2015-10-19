package GUI;

import Logic.Board;
import Logic.Creator;
import Logic.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {
    private JFrame frame;
    private JComboBox<Level> level;
    private Grid grid;
    private Creator creator;

    public Player(){
        creator = new Creator();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }

    private void createGUI(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(getBackground(), BorderLayout.CENTER);
        frame.add(getOptions(),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel getBackground(){
        JPanel background = new JPanel(new BorderLayout());
        grid = new Grid(new Board());
        background.add(grid);
        return background;
    }
    public JPanel getOptions(){
        level = new JComboBox<Level>(Level.values());
        JButton generate = new JButton("GENERATE");
        generate.addActionListener(new GenerateListener());
        JButton check = new JButton("CHECK SOLUTION");
        check.addActionListener(new CheckListener());
        JPanel panel = new JPanel(new GridLayout(1,3,2,2));
        panel.add(level);
        panel.add(generate);
        panel.add(check);
        return panel;
    }

    public static void main(String[] args){
        Player player = new Player();
    }

    private class GenerateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            creator.create((Level)level.getSelectedItem());
            grid.getBlocks(creator.getBoard().getBlocks());
            frame.revalidate();
        }
    }

    private class CheckListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean result = true;
            for(Field field : grid){
                if(!field.checkSolution()){
                    result = false;
                }

            }
        }
    }
}
