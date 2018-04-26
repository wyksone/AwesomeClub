import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Apr 26 16:38:08 CEST 2018
 */



/**
 * @author Luka Dragicevic
 */
public class AddFood extends JFrame {

    Food bfood;
    ObjectGetter og;
    int index;
    ArrayList<Food> foods;

    public AddFood() {
        og = new ObjectGetter();
        foods = og.getAllFoods();
        initComponents();
    }

    private void comboBox1ActionPerformed(ActionEvent e) {

    }

    private void button1ActionPerformed(ActionEvent e) {

        foods.get(index).setName(textField1.getText());
        foods.get(index).setCalorie(Double.parseDouble(textField2.getText()));
        foods.get(index).setFat(Double.parseDouble(textField3.getText()));
        foods.get(index).setCarb(Double.parseDouble(textField4.getText()));
        foods.get(index).setProtein(Double.parseDouble(textField5.getText()));

        CsvWriter writer = new CsvWriter();
        System.out.println(foods);
        //writer.saveFoods(foods);

        comboBox1.removeAllItems();
        for (Food food: foods){
            comboBox1.addItem(food.getName());
        }
    }

    private void comboBox1ItemStateChanged(ItemEvent e) {

        System.out.println(foods.get(6).getCalorie());

        if (e.getStateChange() == ItemEvent.SELECTED) {
            for (Food food : foods) {
                if (food.getName().equals(comboBox1.getSelectedItem().toString())) {
                    if (food.getType() == 'b') {
                        textField1.setText(food.getName());
                        textField2.setText(Double.toString(food.getCalorie()));
                        textField3.setText(Double.toString(food.getFat()));
                        textField4.setText(Double.toString(food.getCarb()));
                        textField5.setText(Double.toString(food.getProtein()));
                        bfood = food;
                        index = foods.indexOf(food);
                    }
                }
            }

            System.out.println(foods.get(6).getCalorie());

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Luka Dragicevic
        comboBox1 = new JComboBox();
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("Add Basic Food");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- comboBox1 ----
        comboBox1.addActionListener(e -> comboBox1ActionPerformed(e));
        comboBox1.addItemListener(e -> comboBox1ItemStateChanged(e));
        contentPane.add(comboBox1, BorderLayout.NORTH);

        //======== panel1 ========
        {

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(new GridLayout(6, 2));

            //---- label1 ----
            label1.setText("Name:");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label1);
            panel1.add(textField1);

            //---- label2 ----
            label2.setText("Calories:");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2);
            panel1.add(textField2);

            //---- label3 ----
            label3.setText("Fats:");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label3);
            panel1.add(textField3);

            //---- label4 ----
            label4.setText("Carbs:");
            label4.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label4);
            panel1.add(textField4);

            //---- label5 ----
            label5.setText("Proteins:");
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label5);
            panel1.add(textField5);

            //---- button1 ----
            button1.setText("Submit Changes");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        for (Food food: foods){
            if (food.getType() == 'b') {
                comboBox1.addItem(food.getName());
            }
        }

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Luka Dragicevic
    private JComboBox comboBox1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
