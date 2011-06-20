/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kwetterfollowing;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DeluxZ
 */
public class FollowingTableModel extends AbstractTableModel {
    
    private String[] columnNames = { "Name", "Bio", "Web", "Following" };

    private Object[][] data = {
        { "Mary", "Campione", "Snowboarding", new Boolean(false) },
        { "Alison", "Huml", "Rowing", new Boolean(true) },
        { "Kathy", "Walrath", "Knitting", new Boolean(false) },
        { "Sharon", "Zakhour", "Speed reading", new Boolean(true) },
        { "Philip", "Milne", "Pool", new Boolean(false) } };

    public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }
    
    @Override
    public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }
    
}
