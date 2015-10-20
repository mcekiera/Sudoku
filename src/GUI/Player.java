package GUI;

import Logic.Board;
import Logic.Creator;
import Logic.Level;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementation of graphic interface of Sudoku.
 */
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

    /**
     * Creates GUI.
     */
    private void createGUI(){
        frame = new JFrame();
        frame.setTitle("SUDOKU");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(createBackground(), BorderLayout.CENTER);
        frame.add(createOptions(),BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @return panel with graphic representation of Sudoku board.
     */
    private JPanel createBackground(){
        JPanel background = new JPanel(new BorderLayout());
        grid = new Grid(new Board());
        background.add(grid);
        return background;
    }

    /**
     * @return panel consisting components related with crating, checking and solving given game board.
     */
    public JPanel createOptions(){
        JPanel panel = new JPanel(new GridLayout(1,4,2,2));
        level = new JComboBox<Level>(Level.values());
        panel.add(level);
        panel.add(createGenerateBt());
        panel.add(createCheckBt());
        panel.add(createSolveBt());
        panel.setBorder(new EmptyBorder(3, 1, 1, 0));
        return panel;
    }

    /**
     * @return button with action responsible for generating new game board.
     */
    private JButton createGenerateBt(){
        JButton generate = new JButton("GENERATE");
        generate.addActionListener(new GenerateListener());
        return generate;
    }

    /**
     * @return button with action responsible for showing checking solution provided by User for given game board.
     */
    private JButton createCheckBt(){
        JButton check = new JButton("CHECK SOLUTION");
        check.addActionListener(new CheckListener());
        return check;
    }

    /**
     * @return button with action responsible for showing valid solution for given game board.
     */
    private JButton createSolveBt(){
        JButton solve = new JButton("SHOW SOLUTION");
        solve.addActionListener(new SolveListener());
        return solve;
    }

    public static void main(String[] args){
        Player player = new Player();
    }

    /**
     * It begin a process of generating new game board with chosen difficulty level.
     */
    private class GenerateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            creator.create((Level) level.getSelectedItem());
            grid.createGrid(creator.getBoard().getBlocks());
            frame.revalidate();
        }
    }

    /**
     * It verifies if solution provided by User is valid.
     */
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
