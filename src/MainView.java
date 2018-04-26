import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javafx.scene.control.Alert;
import org.jdatepicker.UtilDateModel;
import javax.swing.*;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.*;
/*
 * Created by JFormDesigner on Thu Apr 26 14:58:14 CEST 2018
 */



/**
 * @author Luka Dragicevic
 */
public class MainView extends JFrame {

    ObjectGetter og;
    ArrayList<LogDay> days;
    ArrayList<Food> foods;
    ArrayList<Exercise> exercises;
    LogDay day;
    CsvWriter cw;
    DefaultTableModel foodModel;
    int index;

    public MainView() {
        og = new ObjectGetter();
        days = og.getDays();
        foods = og.getAllFoods();
        exercises = og.getExercises();
        initComponents();
        populateFood();
        populateExercise();
    }

    private void populateFood(){
        for (Food food: foods){
            if (food.getType() == 'b') {
                comboBox1.addItem(food.getName());
            }
        }
    }

    private void populateExercise(){
        for (Exercise exercise: exercises){
                comboBox2.addItem(exercise.getName());
        }
    }

    private void menuItem1ActionPerformed(ActionEvent e) {
        AddFood af = new AddFood();
        af.setVisible(true);
    }

    private void datePicker1ActionPerformed(ActionEvent e) {
        Map map = new HashMap();
        map.put("YEAR", datePicker1.getModel().getValue());

        days = og.getDays();

        Calendar cal = ( Calendar ) map.get("YEAR");
        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format( date );
        Date actualDate = parseDate(dateStr);

        String col[] = {"Food Name", "Amount"};
        String col2[] = {"Exercise Name", "Minutes"};
        foodModel = new DefaultTableModel(col, 0);
        DefaultTableModel exerciseModel = new DefaultTableModel(col2, 0);
        table2.setModel(foodModel);
        table3.setModel(exerciseModel);

        for (LogDay day: days){

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(actualDate);
            cal2.setTime(day.getDate());
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

            if(sameDay){
                this.day = day;
                index = days.indexOf(day);

                double caloriesIn = day.getTotalCalories();
                double caloriesOut = day.getSpentCalories();
                double balance = caloriesIn - caloriesOut;

                String weight = Double.toString(day.getWeight());
                String calLimit = Double.toString(day.getCalorieLimit());
                String totalCalories = Double.toString(caloriesIn);
                String totalFat = Double.toString(day.getTotalFat());
                String totalCarb = Double.toString(day.getTotalCarb());
                String totalProtein = Double.toString(day.getTotalProtein());
                String spentCalories = Double.toString(caloriesOut);


                label2.setText("Weight: " + weight);
                label3.setText("Calorie Limit: " + calLimit);
                label5.setText("Calories In: " + totalCalories);
                label8.setText("Calories Out: " + spentCalories);
                label6.setText("Fats Consumed: " + totalFat);
                label4.setText("Carbs Consumed: " + totalCarb);
                label7.setText("Protein Consumed: " + totalProtein);
                label9.setText("Net Calories: " + Double.toString(balance));

                ArrayList<FoodCount> foodList = day.getFoods();
                ArrayList<ExerciseCount> exerciseList = day.getExercises();

                for (FoodCount food: foodList){
                    String name = food.getFood().getName();
                    double amount = food.getCount();

                    Object[] data = {name, amount};

                    foodModel.addRow(data);
                }

                for (ExerciseCount exercise: exerciseList){
                    String name = exercise.getExercise().getName();
                    double amount = exercise.getCount();

                    Object[] data = {name, amount};

                    exerciseModel.addRow(data);
                }
            }
        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        String foodName = comboBox1.getSelectedItem().toString();
        double foodAmount = Double.parseDouble(textField1.getText());

        if (day == null) {
            JOptionPane.showMessageDialog(null, "Please select a date first.");
            return;
        }

        Food newFoodFood  = null;

        for (Food food : foods) {
            if (food.getName().equals(foodName)) {
                newFoodFood = food;
            }
        }

        FoodCount newFood = new FoodCount(newFoodFood, foodAmount);

        ArrayList<FoodCount> foodList = day.getFoods();
        foodList.add(newFood);
        day.setFoods(foodList);

        cw = new CsvWriter();
        ArrayList<LogDay> allDays = og.getDays();
        allDays.remove(index);
        allDays.add(day);
        cw.writeToLog(allDays);
        foodModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Food Added!");

    }

    private void button2ActionPerformed(ActionEvent e) {
        String exerciseName = comboBox2.getSelectedItem().toString();
        double exerciseAmount = Double.parseDouble(textField2.getText());

        if (day == null) {
            JOptionPane.showMessageDialog(null, "Please select a date first.");
            return;
        }

        Exercise newExerciseExercise  = null;

        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(exerciseName)) {
                newExerciseExercise = exercise;
            }
        }

        ExerciseCount newExercise = new ExerciseCount(newExerciseExercise, exerciseAmount);

        ArrayList<ExerciseCount> exerciseList = day.getExercises();
        exerciseList.add(newExercise);
        day.setExercises(exerciseList);

        cw = new CsvWriter();
        ArrayList<LogDay> allDays = og.getDays();
        allDays.remove(index);
        allDays.add(day);
        cw.writeToLog(allDays);
        foodModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Exercise Added!");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Luka Dragicevic
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        panel1 = new JPanel();
        panel4 = new JPanel();
        comboBox1 = new JComboBox();
        textField1 = new JTextField();
        button1 = new JButton();
        panel5 = new JPanel();
        comboBox2 = new JComboBox();
        textField2 = new JTextField();
        button2 = new JButton();
        label1 = new JLabel();
        datePicker1 = new JDatePicker();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        scrollPane3 = new JScrollPane();
        table3 = new JTable();
        panel3 = new JPanel();
        label2 = new JLabel();
        label6 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Diet Manager");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(5, 5));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Options");

                //---- menuItem1 ----
                menuItem1.setText("Add Food");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Add Exercise");
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(new GridLayout(1, 2));

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout());
                panel4.add(comboBox1);

                //---- textField1 ----
                textField1.setMinimumSize(new Dimension(35, 24));
                textField1.setPreferredSize(new Dimension(50, 24));
                panel4.add(textField1);

                //---- button1 ----
                button1.setText("Add");
                button1.addActionListener(e -> button1ActionPerformed(e));
                panel4.add(button1);
            }
            panel1.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new FlowLayout());

                //---- comboBox2 ----
                comboBox2.setPreferredSize(new Dimension(80, 26));
                panel5.add(comboBox2);

                //---- textField2 ----
                textField2.setMinimumSize(new Dimension(35, 24));
                textField2.setPreferredSize(new Dimension(35, 24));
                textField2.setMaximumSize(new Dimension(35, 2147483647));
                panel5.add(textField2);

                //---- button2 ----
                button2.setText("Add");
                button2.addActionListener(e -> button2ActionPerformed(e));
                panel5.add(button2);
            }
            panel1.add(panel5);

            //---- label1 ----
            label1.setText("Date:");
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label1);

            //---- datePicker1 ----
            datePicker1.addActionListener(e -> datePicker1ActionPerformed(e));
            panel1.add(datePicker1);
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(1, 2));

            //======== scrollPane2 ========
            {

                //---- table2 ----
                table2.setAutoCreateRowSorter(true);
                table2.setEnabled(false);
                scrollPane2.setViewportView(table2);
            }
            panel2.add(scrollPane2);

            //======== scrollPane3 ========
            {

                //---- table3 ----
                table3.setEnabled(false);
                scrollPane3.setViewportView(table3);
            }
            panel2.add(scrollPane3);
        }
        contentPane.add(panel2, BorderLayout.CENTER);

        //======== panel3 ========
        {
            panel3.setLayout(new GridLayout(4, 2));

            //---- label2 ----
            label2.setText("text");
            panel3.add(label2);

            //---- label6 ----
            label6.setText("text");
            panel3.add(label6);

            //---- label3 ----
            label3.setText("text");
            panel3.add(label3);

            //---- label4 ----
            label4.setText("text");
            panel3.add(label4);

            //---- label5 ----
            label5.setText("text");
            panel3.add(label5);

            //---- label7 ----
            label7.setText("text");
            panel3.add(label7);

            //---- label8 ----
            label8.setText("text");
            panel3.add(label8);

            //---- label9 ----
            label9.setText("text");
            panel3.add(label9);
        }
        contentPane.add(panel3, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        ArrayList<LogDay> days = new ArrayList<>();
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Exercise> exercises = new ArrayList<>();

        ObjectGetter getter = new ObjectGetter();
        exercises = getter.getExercises();
        /*days = getter.getDays();
        for (LogDay day: days) {
            System.out.println(day.toString());
        }*/



    }
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Luka Dragicevic
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JPanel panel1;
    private JPanel panel4;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton button1;
    private JPanel panel5;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JButton button2;
    private JLabel label1;
    private JDatePicker datePicker1;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JScrollPane scrollPane3;
    private JTable table3;
    private JPanel panel3;
    private JLabel label2;
    private JLabel label6;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
