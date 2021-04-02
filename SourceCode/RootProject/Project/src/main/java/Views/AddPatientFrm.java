package Views;

import Controllers.DBConnection;
import Controllers.LoginManager;
import Models.Patient;
import Models.Staff_Model;
import Models.Symptoms;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.jdatepicker.JDatePicker;

public class AddPatientFrm {

        //Front end variables
	private JPanel contentPane;
	private JTextField patientName;
	private JTextField patientPhone;
	private JTextField patientAge;        
	private JTextField patientProvider;
	private JTextField patientSSN;
        private JTextField symptoms;
        private JTextField patientPhysician;
        private JTextField patientPhysicianNum;
        private JTextField patientGender;
        private JTextField allergies;
        private JDatePicker dateOfBirth;
        
        Patient patient;
        DBConnection db = new DBConnection();
        LoginManager loginManager;


      	/**
	 * Create the frame.
	 */
	public AddPatientFrm(final LoginManager loginManager, Staff_Model user) {
                
                            
                //loginManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//loginManager.setBounds(100, 100, 1045, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginManager.setContentPane(contentPane);
                
                JLabel serverMessage = new JLabel();
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblPhone= new JLabel("Phone Number:");
		lblPhone.setFont(new Font("����", Font.PLAIN, 20));		
                
                //TODO: Change to calender drop down to enter Date of birth instead
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblProvider = new JLabel("Insurance Provider:");
		lblProvider.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblSSN = new JLabel("SSN:");
		lblSSN.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblSymptoms = new JLabel("Symptoms seperated by commas:");
		lblSymptoms.setFont(new Font("����", Font.PLAIN, 20));
                
                JLabel lblPhysician = new JLabel("Physician:");
		lblPhysician.setFont(new Font("����", Font.PLAIN, 20));
		
                JLabel lblPhysicianNumber = new JLabel("Physician Number:");
		lblPhysicianNumber.setFont(new Font("����", Font.PLAIN, 20));
                
                JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("����", Font.PLAIN, 20));
               
                //TODO: IMPLEMENT PLEASE
                //--------------------------------------------------
                JLabel lblAllergies = new JLabel("Allergies seperated by commas:");
		lblAllergies.setFont(new Font("����", Font.PLAIN, 20));
                
                JLabel lblMedications = new JLabel("Medications seperated by commas:");
		lblMedications.setFont(new Font("����", Font.PLAIN, 20));
                
                JLabel lblAddrStreet = new JLabel("Street Number:");
		lblAddrStreet.setFont(new Font("����", Font.PLAIN, 20));
                
                JLabel lblZipCode = new JLabel("Zipcode:");
		lblZipCode.setFont(new Font("����", Font.PLAIN, 20));
                
                JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("����", Font.PLAIN, 20));
                
                //TODO: Change to drop down
                JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("����", Font.PLAIN, 20));
                
                //TODO: Default to USA && Change to dropdown
                JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("����", Font.PLAIN, 20));
                
                //----------------------------------------------------------
		patientName = new JTextField();
		patientName.setFont(new Font("����", Font.PLAIN, 20));
		patientName.setColumns(10);
		
		patientPhone = new JTextField();
		patientPhone.setFont(new Font("����", Font.PLAIN, 20));
		patientPhone.setColumns(10);
		
		patientAge = new JTextField();
		patientAge.setFont(new Font("����", Font.PLAIN, 20));
		patientAge.setColumns(10);
		
		patientProvider = new JTextField();
		patientProvider.setFont(new Font("����", Font.PLAIN, 20));
		patientProvider.setColumns(10);
		
		patientSSN = new JTextField();
		patientSSN.setFont(new Font("����", Font.PLAIN, 20));
		patientSSN.setColumns(10);
		
		symptoms = new JTextField();
		symptoms.setFont(new Font("����", Font.PLAIN, 20));
		symptoms.setColumns(10);
                
                patientPhysician = new JTextField();
		patientPhysician.setFont(new Font("����", Font.PLAIN, 20));
		patientPhysician.setColumns(10);
                
                patientPhysicianNum = new JTextField();
		patientPhysicianNum.setFont(new Font("����", Font.PLAIN, 20));
		patientPhysicianNum.setColumns(10);
                
                patientGender = new JTextField();
		patientGender.setFont(new Font("����", Font.PLAIN, 20));
		patientGender.setColumns(10);
		
                
		JButton btnNewButton = new JButton("Add Patient");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		
                btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            if(!patientName.getText().isEmpty() && !patientAge.getText().isEmpty() && !patientPhone.getText().isEmpty() && !patientProvider.getText().isEmpty()
                            && !patientSSN.getText().isEmpty() && !patientPhysician.getText().isEmpty() && !patientPhysicianNum.getText().isEmpty())
                            {
                                ArrayList<Symptoms> symptomList = new ArrayList<Symptoms>();  
                                if(!symptoms.getText().isEmpty())
                                {
                                    String[] symptomValues = symptoms.getText().split(",");                                         

                                    for(int i = 0; i < symptomValues.length; i++)
                                    {
                                        Symptoms symptom = new Symptoms(symptomValues[i].replaceAll("\\s+", ""));
                                        symptomList.add(symptom);
                                    }
                                }  
                                ArrayList<String> allergyList = new ArrayList<String>();  
                                if(!allergies.getText().isEmpty())
                                {
                                    String[] allergieValues = allergies.getText().split(",");                                         

                                    for(int i = 0; i < allergieValues.length; i++)
                                    {
                                        String allergy = new String(allergieValues[i].replaceAll("\\s+", ""));
                                        allergyList.add(allergy);
                                    }
                                } 

                                patient = new Patient(patientName.getText(), Integer.parseInt(patientAge.getText()), patientPhone.getText(),
                                Integer.parseInt(patientSSN.getText()), patientPhysician.getText(), patientPhysicianNum.getText(), patientProvider.getText(),
                                symptomList, patientGender.getText(), allergyList);       

                                String state = db.createPatient(patient, user);

                                if(state.equals("SUCCESSFUL"))
                                {
                                  //Show new screen
                                  // showRegisterSecondary(user, loginManager.getScene());
                                }
                                else
                                {
                                    serverMessage.setText("Could not add patient. Please try again.");
                                }
                            }
                            else
                            {
                                serverMessage.setText("You left a field blank.");
                            }   
                        }
		});
                
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("����", Font.PLAIN, 20));
                btnReset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        patientName.setText("");
                        patientPhone.setText("");
                        patientAge.setText("");
                        patientProvider.setText("");
                        patientSSN.setText("");
                        symptoms.setText("");
                        patientPhysician.setText("");
                        patientPhysicianNum.setText("");
                        patientGender.setText("");
                    }
                });
		
		JButton btnClose = new JButton("Back");
                btnClose.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginManager.showMainPanel(user);
                    }
                });
                
		btnClose.setFont(new Font("����", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(122)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProvider, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSSN, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSymptoms, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblPhysician, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblPhysicianNumber, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)                                                                
                                                                .addComponent(patientGender, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(patientPhysicianNum, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(patientPhysician, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(symptoms, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(patientSSN, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(patientProvider, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(patientAge, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(patientPhone, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(patientName, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(220, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(77)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProvider, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientProvider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSSN, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientSSN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSymptoms, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(symptoms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhysician, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientPhysician, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhysicianNumber, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientPhysicianNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(patientGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
