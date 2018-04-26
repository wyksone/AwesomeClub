import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FoodTableModel extends AbstractTableModel {
    private ArrayList<FoodCount> foods ;
    private String[] columns ;

    public FoodTableModel(ArrayList<FoodCount> foodList){
        super();
        foods = foodList ;
        columns = new String[]{"Food Name","Amount"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length ;
    }

    // Number of row of your table
    public int getRowCount() {
        return foods.size();
    }
    // The object to render in a cell
    public Object getValueAt(int row, int col) {
        /*FoodCount food = foods.get(row);
        switch(col) {
            case 0: return food.getPosition();
            // to complete here...
            default: return null;
        }*/
        return null;
    }

    // Optional, the name of your column
    public String getColumnName(int col) {
        return columns[col] ;
    }

}