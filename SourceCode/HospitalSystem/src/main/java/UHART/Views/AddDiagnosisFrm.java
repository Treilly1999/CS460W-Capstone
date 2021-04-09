package UHART.Views;

import UHART.Controllers.DoctorController;
import UHART.Controllers.LoginManager;
import UHART.Models.Staff_Model;
import UHART.Controllers.JTableButtonModel;
import UHART.Controllers.JTableButtonRenderer;
import UHART.Controllers.JTableMouseListener;
import UHART.Models.Diagnoses;
import UHART.Models.Medications;
import UHART.Models.Symptoms;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.table.TableCellRenderer;
import org.bson.Document;

public class AddDiagnosisFrm {   
        
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	//private JTextField textField_3;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
        private JComboBox medications;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
        private DoctorController doctorController = new DoctorController();
        
        private List<Medications> selectedMedications;
        private List<Symptoms> selectedSymptoms;
        private List<Diagnoses> selectedDiagnosis;
	/**
	 * Create the frame.
	 */
	public AddDiagnosisFrm(final LoginManager loginManager, final Staff_Model user) {
				
		loginManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//loginManager.setBounds(100, 100, 1147, 897);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginManager.setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("SSN:");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 20));
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
                
                /*
                Author: Tyler
                Description: Button creates a search document from textfield, 
                returns with a table of found entries that have a button to take
                user to profile page for patient.
                */
                btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            Document search = new Document();
                            if(textField.getText().isEmpty())
                            {
                                search = null;
                            }
                            else
                            {
                                search.put("ssn", Integer.parseInt(textField.getText()));
                            }
                            
                            TableCellRenderer tableRenderer = new JTableButtonRenderer();
                            JTableButtonModel button = new JTableButtonModel(loginManager, user);
                            button.setRows(search);
                            table.setModel(button);
                            table.getColumn("Patients").setCellRenderer(tableRenderer);
                            //tableRenderer = table.getDefaultRenderer(JButton.class);
                            //table.setDefaultRenderer(JButton.class, new JTableButtonRenderer());
                            table.addMouseListener(new JTableMouseListener(table));
                            
                            //System.out.println(table.getValueAt(0, 0));
                        }
		});	
                
                table = new JTable();
                table.setFillsViewportHeight(true);
		//table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblSymptom = new JLabel("Symptom");
		lblSymptom.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblDiagnosis = new JLabel("Diagnosis");
		lblDiagnosis.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblMedicines = new JLabel("Medicines");
		lblMedicines.setFont(new Font("����", Font.PLAIN, 20));
		
//		textField_3 = new JTextField();
//		textField_3.setColumns(10);
                
		JList list = new JList();
		
		comboBox = new JComboBox();
                comboBox.addItem("Fever");
                comboBox.addItem("Cough");
                comboBox.addItem("Congestion");
                comboBox.addItem("Aches");
                comboBox.addItem("Fatigue");
                comboBox.addItem("Nausea");
		                
		comboBox_1 = new JComboBox();
                comboBox_1.addItem(UHART.Models.Diagnoses.ABDOMINALPAIN);
                comboBox_1.addItem(UHART.Models.Diagnoses.ACUTEUPPERRESPIRATORYINFECTION);
                comboBox_1.addItem(UHART.Models.Diagnoses.CHESTPAIN);
                comboBox_1.addItem(UHART.Models.Diagnoses.DIZZINESSANDGIDDINESS);
                comboBox_1.addItem(UHART.Models.Diagnoses.HEADACHE);
                comboBox_1.addItem(UHART.Models.Diagnoses.INJURYOFHEAD);
                comboBox_1.addItem(UHART.Models.Diagnoses.LOWERBACKPAIN);
                comboBox_1.addItem(UHART.Models.Diagnoses.NONINFECTIVEGASTROENTERITISANDCOLITIS);
                comboBox_1.addItem(UHART.Models.Diagnoses.SYNCOPEANDCOLLAPSE);
                comboBox_1.addItem(UHART.Models.Diagnoses.URINARYTRACTINFECTION);
                
                medications = new JComboBox();
                medications.addItem(UHART.Models.Medications.ACETAMINOPHEN);
                medications.addItem(UHART.Models.Medications.ANTACID);
				medications.addItem(UHART.Models.Medications.ANTIBIOTIC);
                medications.addItem(UHART.Models.Medications.ANTISEIZURE);
                medications.addItem(UHART.Models.Medications.ASPIRIN);
                medications.addItem(UHART.Models.Medications.FOSCARNET);
                medications.addItem(UHART.Models.Medications.GANCICLOVIR);
                medications.addItem(UHART.Models.Medications.IBUPROFIN);
                medications.addItem(UHART.Models.Medications.SIMETHICONE);
                medications.addItem(UHART.Models.Medications.SUGAR);
                medications.addItem(UHART.Models.Medications.VALDANCICLOVIR);
                
		btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
                btnNewButton_1.addActionListener(new ActionListener() {
                    //TODO: Add diagnosis to selectedDiagnosis list
                    public void actionPerformed(ActionEvent e) {
                        //selectedDiagnosis.add(comboBox_1.getSelectedItem());
//                        if(!textField_3.getText().isEmpty())
//                            textField_3.setText(textField_3.getText() + ", " + comboBox_1.getSelectedItem().toString());
//                        else
//                        {                          
//                            textField_3.setText("Selected diagnosis: " + comboBox_1.getSelectedItem().toString());
//                        }
                            
                    }
                });
                
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setFont(new Font("����", Font.PLAIN, 20));
                
                btnNewButton_2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginManager.showMainPanel(user);
                    }
                });
                
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(176)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSymptom, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDiagnosis, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMedicines, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, Alignment.TRAILING, 0, 438, Short.MAX_VALUE)
										.addComponent(comboBox_1, 0, 438, Short.MAX_VALUE)
                                                                                .addComponent(medications, 0, 438, Short.MAX_VALUE)))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(table, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
									.addGap(136)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(71)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(182)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(315)))
					.addGap(587))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addGap(70)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSymptom, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addGap(128)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDiagnosis, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addComponent(lblMedicines, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(80)
							.addComponent(medications, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))                                                
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
