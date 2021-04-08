/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Patient;
import Models.Staff_Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/**
 *
 * @author Tyler
 */


public class JTableButtonModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = new String[] {"Patients"};
		private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {JButton.class};
                private static Object[] rows;
                private ArrayList<Patient> patients;
                private DBConnection db = new DBConnection();
                private LoginManager loginManager;
                private Staff_Model user;
		
                public JTableButtonModel(LoginManager loginManager, Staff_Model user)
                {
                    this.loginManager = loginManager;
                    this.user = user;
                }
                
                public ArrayList<Patient> getPatients() { return patients; }
                
                public void setRows(Document search)
                {
                    if(search == null)
                     {
                         //Finds every single patient
                         patients = db.parsePatients(null);
                     }
                     else
                     {            
                         //finds individual patient
                         patients = db.parsePatients(search);
                     }
                         String name;

                         rows = new Object[patients.size()];

                        
                        for(int k = 0; k < rows.length; k++)
                        {
                            name = patients.get(k).getName();                   

                            rows[k] = new JButton(name);


                        }
                           
                }
                
		@Override public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override public int getRowCount() {
			return rows.length;
		}
		
		@Override public String getColumnName(int columnIndex) {
	        return COLUMN_NAMES[columnIndex];
	    }
		
		@Override public Class<?> getColumnClass(int columnIndex) {
			return COLUMN_TYPES[columnIndex];
		}

                //TODO: Change the panel to a profile page for patient
		@Override public Object getValueAt(final int rowIndex, final int columnIndex) {
			switch (columnIndex) {			
                            //TODO: pass getPatients().get(rowIndex) to a loginManager method that shows patient profile
				case 0: final JButton button = new JButton(getPatients().get(rowIndex).getName());
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								loginManager.showPatientPanel(getPatients().get(rowIndex), user);
							}
						});
						return button;
				default: return "Error";
			}
		}	
	}

