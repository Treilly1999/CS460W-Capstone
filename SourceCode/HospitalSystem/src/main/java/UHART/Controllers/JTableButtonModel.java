/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Controllers;

import UHART.Models.Patient;
import UHART.Models.Staff_Model;
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
                private ArrayList<Patient> patients = new ArrayList<>();
                private DBConnection db = new DBConnection();
                private LoginManager loginManager;
                private Staff_Model user;
                private Patient patient;
                private Boolean moreThanOne = false;
		
                public JTableButtonModel(LoginManager loginManager, Staff_Model user)
                {
                    this.loginManager = loginManager;
                    this.user = user;
                }
                
                public ArrayList<Patient> getPatients() { return patients; }
                private Patient getPatient() {return patient; }
                
                public void setRows(Document search)
                {
                    if(search == null)
                     {
                         //Finds every single patient
                         patients = db.parsePatients();
                         moreThanOne = true;
                     }
                     else
                     {            
                         //finds individual patient
                         patient = db.parsePatient(search);
                         //patients.add(patient);
                     }
                         String name;

                    if(moreThanOne)
                    {
                        rows = new Object[patients.size()];
                    
                        for(int k = 0; k < rows.length; k++)
                        {
                            name = patients.get(k).getName();                   
    
                            rows[k] = new JButton(name);       
                        }
                    }
                    else
                    {
                        rows = new Object[1];
                    
                        name = patient.getName();                   

                        rows[0] = new JButton(name);
                        
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

		@Override public Object getValueAt(final int rowIndex, final int columnIndex) {
			switch (columnIndex) {			                           
				case 0: final JButton button = new JButton();

                        if(moreThanOne)
                        {
                            button.setText(getPatients().get(rowIndex).getName());
                            button.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent arg0) {
                                    loginManager.showPatientPanel(getPatients().get(rowIndex), user);
                                }
                            });
                        }
                        else
                        {
                            button.setText(getPatient().getName());
                            button.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent arg0) {
                                    loginManager.showPatientPanel(getPatient(), user);
                                }
                            });
                        }
						
						return button;
				default: return "Error";
			}
		}	
	}

