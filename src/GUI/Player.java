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
        frame.setTitle("SUDOKU");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(createBackground(), BorderLayout.CENTER);
        frame.add(createOptions(),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createBackground(){
        JPanel background = new JPanel(new BorderLayout());
        grid = new Grid(new Board());
        background.add(grid);
        return background;
    }
    public JPanel createOptions(){
        JPanel panel = new JPanel(new GridLayout(1,4,2,2));
        level = new JComboBox<Level>(Level.values());
        panel.add(level);
        panel.add(createGenerateBt());
        panel.add(createCheckBt());
        panel.add(createSolveBt());
        return panel;
    }

    private JButton createGenerateBt(){
        JButton generate = new JButton("GENERATE");
        generate.addActionListener(new GenerateListener());
        return generate;
    }

    private JButton createCheckBt(){
        JButton check = new JButton("CHECK SOLUTION");
        check.addActionListener(new CheckListener());
        return check;
    }

    private JButton createSolveBt(){
        JButton solve = new JButton("SHOW SOLUTION");
        solve.addActionListener(new SolveListener());
        return solve;
    }

    public static void main(String[] args){
        Player player = new Player();
    }

    private class GenerateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            creator.create((Level) level.getSelectedItem());
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
            if(result){
                JOptionPane.showMessageDialog(frame, "CONGRATULATIONS", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * ActionListener responsible for solving puzzle not finished by User.
     */
    private class SolveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(Field field : grid){
                field.showSolution();
            }
        }
    }
}
