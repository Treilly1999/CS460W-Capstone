/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Views;

import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.awt.event.ActionEvent;

import UHART.Controllers.DBConnection;
import UHART.Controllers.LoginManager;
import UHART.Models.Staff_Model;
import com.google.common.hash.Hashing;
/**
 *
 * @author MSI-PC
 */
public class ChangePwFrm extends javax.swing.JPanel {

    private LoginManager loginManager;
    private Staff_Model user;
    private DBConnection db = new DBConnection();
    /**
     * Creates new form ChangePwFrm
     */
    public ChangePwFrm(final LoginManager loginManager, final Staff_Model user) {
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
        this.loginManager = loginManager;
        this.user = user;

        //loginManager.setVisible(false);
        loginManager.setContentPane(this);


        //TODO: Implement backend
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("����", 0, 18)); // NOI18N
        jLabel1.setText("Old Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(46, 98, 0, 0);
        add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("����", 0, 18)); // NOI18N
        jLabel2.setText("Re-enter Old Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 98, 0, 0);
        add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 165;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 160, 0, 86);
        add(jPasswordField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 165;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 160, 0, 86);
        add(jPasswordField2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("����", 0, 18)); // NOI18N
        jLabel3.setText("New Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 98, 0, 0);
        add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 165;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 160, 0, 86);
        add(jPasswordField3, gridBagConstraints);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 149, 89, 0);
        add(jButton1, gridBagConstraints);

        jButton2.setText("Confirm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 134, 89, 0);
        add(jButton2, gridBagConstraints);
        /*
        Author: Tyler
        Description: Change password
        */
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(!jPasswordField1.getText().isEmpty() && !jPasswordField2.getText().isEmpty() && !jPasswordField3.getText().isEmpty())
                {
                    if(jPasswordField1.getText().equals(jPasswordField2.getText()))
                    {
                        String sha256oldPassword = Hashing.sha256().hashString(jPasswordField1.getText(), StandardCharsets.UTF_8).toString();
                        if(sha256oldPassword.equals(user.getPassword()))
                        {
                            String sha256newPassword = Hashing.sha256().hashString(jPasswordField3.getText(), StandardCharsets.UTF_8).toString();
                            db.changePassword(user, sha256newPassword);
                            loginManager.showLoginScreen();
                        }
                       
                    }
                }
            }
        });

        jLabel4.setFont(new java.awt.Font("����", 0, 36)); // NOI18N
        jLabel4.setText("Change Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 130, 0, 0);
        add(jLabel4, gridBagConstraints);

        //Error message
        jLabel5.setFont(new java.awt.Font("����", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 66;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 160, 0, 86);
        add(jLabel5, gridBagConstraints);

        loginManager.validate();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        loginManager.showMainPanel(user);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    // End of variables declaration//GEN-END:variables
}
