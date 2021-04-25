/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Views;

import UHART.Controllers.DBConnection;
import UHART.Controllers.LoginManager;
import UHART.Models.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author MSI-PC
 */
public class AddPatientFrm extends javax.swing.JPanel {

    private Patient patient;
    private DBConnection db = new DBConnection();
    private Address address;
    /**
     * Creates new form AddPatientFrm
     */
    public AddPatientFrm(final LoginManager loginManager, final Staff_Model user) {
        initComponents(loginManager, user);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(final LoginManager loginManager, final Staff_Model user) {
        java.awt.GridBagConstraints gridBagConstraints;
        loginManager.setContentPane(this);
        //java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel1.setText("Gender:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel2.setText("Provider: ");

        jLabel3.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel3.setText("Phone Number: ");

        jLabel4.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel4.setText("SSN:");

        jLabel5.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel5.setText("Physician:");

        jLabel6.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel6.setText("Physician Number:");

        jLabel7.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel7.setText("Allergies seperated by commns:");

        jLabel8.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel8.setText("Symptons seperated by commas:");

        jLabel9.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel9.setText("Medications seperated by commas:");

        jLabel10.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel10.setText("Steret Number:");

        jLabel11.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel11.setText("City:");

        jLabel12.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel12.setText("State:");

        jTextField1.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField2.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField3.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField4.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField5.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField6.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField7.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField8.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField9.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField10.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField11.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField12.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jLabel13.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel13.setText("Zip:");

        jLabel14.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel14.setText("Country:");

        jTextField13.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jButton1.setText("Back");
        jButton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                loginManager.showMainPanel(user);
            }
        });

        jButton3.setText("Add patient");
        jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            if(!jTextField1.getText().isEmpty() && !jTextField2.getText().isEmpty() && !jTextField3.getText().isEmpty() && !jTextField4.getText().isEmpty()
                            && !jTextField5.getText().isEmpty() && !jTextField6.getText().isEmpty() && !jTextField7.getText().isEmpty() && !jTextField8.getText().isEmpty()
                            && !jTextField9.getText().isEmpty() && !jTextField10.getText().isEmpty() && !jTextField11.getText().isEmpty() && !jTextField12.getText().isEmpty()
                            && !jTextField13.getText().isEmpty() && !jTextField14.getText().isEmpty() && !jTextField15.getText().isEmpty() && !jTextField16.getText().isEmpty())
                            {
                                
                                try
                                {
                                    int ssnLength = (int)(Math.log10(Integer.parseInt(jTextField4.getText())) + 1);
                                    System.out.println(ssnLength);
                                    if(ssnLength == 9)
                                    {                                   

                                        ArrayList<Symptoms> symptomList = new ArrayList<Symptoms>();  
                                        if(!jTextField8.getText().isEmpty())
                                        {
                                            String[] symptomValues = jTextField8.getText().split(",");                                         

                                            for(int i = 0; i < symptomValues.length; i++)
                                            {
                                                Symptoms symptom = new Symptoms(symptomValues[i].replaceAll("\\s+", ""));
                                                symptomList.add(symptom);
                                            }
                                        }                                         
                                        ArrayList<String> allergyList = new ArrayList<String>();
                                        if(!jTextField7.getText().isEmpty())
                                        {
                                            String[] allergieValues = jTextField7.getText().split(",");                                         
        
                                            for(int i = 0; i < allergieValues.length; i++)
                                            {
                                                String allergy = new String(allergieValues[i].replaceAll("\\s+", ""));
                                                allergyList.add(allergy);
                                            }
                                        }
                                        // ArrayList<String> medList = new ArrayList<String>();
                                        // if(!jTextField11.getText().isEmpty())
                                        // {
                                        //     String[] medValues = jTextField11.getText().split(",");                                         
        
                                        //     for(int i = 0; i < medValues.length; i++)
                                        //     {
                                        //         String med = new String(medValues[i].replaceAll("\\s+", ""));
                                        //         medList.add(med);
                                        //     }
                                        // }

                                        UUID id = UUID.randomUUID();
                                        address = new Address(jTextField10.getText(),jTextField13.getText(), jTextField12.getText(), jTextField14.getText(), jTextField11.getText());
                                        Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(jTextField16.getText());

                                        //TODO: Remove null allergylist
                                        patient = new Patient(id.toString(), jTextField15.getText(), dob, jTextField3.getText(),
                                        Integer.parseInt(jTextField4.getText()), jTextField5.getText(), jTextField6.getText(),jTextField2.getText(), 
                                        symptomList, jTextField1.getText(), allergyList, address);       
                                        
                                        //System.out.println(patient.toString());

                                        String state = db.createPatient(patient, user);

                                        //TODO: Add condition for failure to add patient
                                        //loginManager.showMainPanel(user);
                                        loginManager.showPatientPanel(patient, user);

                                    }
                                    else
                                    {
                                        //serverMessage.setText("SSN is not valid.");
                                    }                                    
                                }
                                catch(Exception except)
                                {
                                    System.out.println(except);
                                    //serverMessage.setText("Please check the fields");
                                    
                                }
                                                                
                            }
                            else
                            {
                                //serverMessage.setText("You left a field blank.");
                            }                               
                            
                        }
		});

        jTextField14.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jTextField15.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        jLabel20.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel20.setText("Date of Birth:");
        jLabel20.setToolTipText("");

        jLabel21.setFont(new java.awt.Font("����", 0, 20)); // NOI18N
        jLabel21.setText("Name: ");
        jLabel21.setToolTipText("");

        jTextField16.setFont(new java.awt.Font("����", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton1))
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(jTextField11)
                            .addComponent(jTextField10)
                            .addComponent(jTextField9)
                            .addComponent(jTextField8)
                            .addComponent(jTextField6)
                            .addComponent(jTextField5)
                            .addComponent(jTextField7)
                            .addComponent(jTextField13)
                            .addComponent(jTextField14)
                            .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2)
                            .addComponent(jTextField15)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4))
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(182, 182, 182))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(46, 46, 46))
        );

        jScrollPane1.setViewportView(jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 695;
        gridBagConstraints.ipady = 629;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);
        loginManager.validate();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
