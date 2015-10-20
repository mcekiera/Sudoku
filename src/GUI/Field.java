package GUI;

import Logic.Cell;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class Field extends JPanel {
    JFormattedTextField textField;
    Cell cell;

    public Field (Cell cell) {
        this.cell = cell;
        setLayout(new BorderLayout());
        createField();
    }

    public boolean checkSolution(){
        textField.setForeground(new JTextField().getForeground());
        if(!textField.getText().equals(" ")) {
            if (inputMatchesSolution()) {
                return true;
            } else {
                textField.setForeground(Color.RED);
                return false;
            }
        }
        return false;
    }

    public boolean inputMatchesSolution(){
        return Integer.parseInt(textField.getText()) == cell.getSolution();
    }

    public void showSolution(){
        textField.setForeground(new JTextField().getForeground());
        textField.setText(String.valueOf(cell.getSolution()));
    }

    private void createField() {
        textField = setFormatter();
        textField.setFont(new Font("Ariala", Font.BOLD, 30));
        if(!cell.isBlank()) {
            textField.setText(String.valueOf(cell.getValue()));
            textField.setEditable(false);
        } else {
            getHelpers();
        }
        add(textField, BorderLayout.CENTER);
        setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }

    private JFormattedTextField getFormattedTextField(String format) {
        final JFormattedTextField field = new JFormattedTextField(getMaskFormatter(format));
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                field.selectAll();
            }
        });
        return field;
    }

    /**
     * @param format pattern of allowed format.
     * @return MaskFormatter object which allows to insert only digits or space character.
     */
    private MaskFormatter getMaskFormatter(String format) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(format);
            mask.setValidCharacters(" 123456789");
        }catch (ParseException ex) {
            ex.printStackTrace();
        }
        return mask;
    }

    /**
     * Creates Sudoku helper fields, where User can write number which he suspect could be in blank cell, without
     * setting answer.
     */
    private void getHelpers() {
        textField.setLayout(new GridLayout(4,4));
        for(int i = 0; i < 16; i++) {
            if(i == 0 || i == 3 || i == 4|| i == 7|| i == 8|| i == 11|| i == 12|| i == 15) {
                JFormattedTextField field = setFormatter();
                field.setBackground(new Color(250, 250, 250));
                field.setFont(new Font("Arial", Font.PLAIN, 10));
                textField.add(field);
            }else {
                textField.add(getOpaqueFiller());
            }
        }
    }

    /**
     * @return transparent JPanel.
     */
    private JPanel getOpaqueFiller(){
        JPanel opaqueFiller = new JPanel();
        opaqueFiller.setOpaque(false);
        return opaqueFiller;
    }

    /**
     * @return JFormattedTextField, without borders, with centered text, and which allows only single digits (without 0)
     */
    private JFormattedTextField setFormatter(){
        JFormattedTextField field = getFormattedTextField("*");
        field.setBorder(null);
        field.setHorizontalAlignment(JFormattedTextField.CENTER);
        field.setColumns(1);
        return field;
    }
}
