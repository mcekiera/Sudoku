package GUI;

import Logic.Cell;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class Field extends JPanel{
    JFormattedTextField textField;
    Cell cell;

    public Field (Cell cell){
        textField = getFormattedTextField("*");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(new EmptyBorder(0,0,0,0));
        textField.setColumns(2);
        textField.setFont(new Font("Ariala", Font.BOLD, 30));
        this.cell = cell;
        setLayout(new BorderLayout());
        createField();
    }

    public int readValue(){
        return cell.isBlank() ? 0 : Integer.parseInt(textField.getText());
    }

    private void createField(){
        if(!cell.isBlank()){
            textField.setText(String.valueOf(cell.getValue()));
            textField.setEditable(false);
        } else {
            add(sideHelper(), BorderLayout.EAST);
        }
        add(textField, BorderLayout.CENTER);
        setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }

    private JFormattedTextField getFormattedTextField(String format){
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

    private MaskFormatter getMaskFormatter(String format){
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(format);
            mask.setValidCharacters(" 123456789");
        }catch (ParseException ex){
            ex.printStackTrace();
        }
        return mask;
    }

    private JPanel sideHelper(){
        textField.setLayout(new GridLayout(4,4));
        JPanel panel = new JPanel(new GridLayout(3,1));
        int[] x = {0,3,4,7,8,11,12,15};
        for(int i = 0; i < 16; i++){
            if(i == 0 || i == 3|| i == 4|| i == 7|| i == 8|| i == 11|| i == 12|| i == 15) {
                JFormattedTextField field = getFormattedTextField("*");

                field.setBorder(null);
                field.setHorizontalAlignment(JFormattedTextField.CENTER);
                field.setColumns(1);
                field.setFont(new Font("Arial", Font.PLAIN, 10));
                textField.add(field);
            }else {
                JPanel panel1 = new JPanel();
                panel1.setOpaque(false);
                textField.add(panel1);
            }
        }
        return panel;
    }




}
