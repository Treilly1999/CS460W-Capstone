/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Tyler
 */
public class JTableMouseListener extends MouseAdapter{
    
    private final JTable table;
    
    public JTableMouseListener(JTable table)
    {
        this.table = table;
    }
    
    @Override public void mouseClicked(MouseEvent e)
    {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX());
        int row    = e.getY()/table.getRowHeight(); 

        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
          Object value = table.getValueAt(row, column);
          if (value instanceof JButton) {
            ((JButton)value).doClick();
          }
        }
    }
}
