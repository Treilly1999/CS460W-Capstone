/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Views;

import UHART.Controllers.LoginManager;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.checkerframework.checker.units.qual.A;

import UHART.Models.Staff_Model;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author MSI-PC
 */
public class AboutUsFrm extends javax.swing.JPanel {

    /**
     * Creates new form AboutUsFrm
     */
    public AboutUsFrm(final LoginManager loginManager, final Staff_Model user) {
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

        //loginManager.setVisible(false);
        
        loginManager.setContentPane(this);

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			   
        			   URI uri= new URI("https://github.com/Treilly1999/CS460W-Capstone");
        			   
        			   java.awt.Desktop.getDesktop().browse(uri);
        			    System.out.println("Web page opened in browser");
        			 
        			  } catch (Exception A) {
        			   
        			   A.printStackTrace();
        			}
        	}
        });

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("About US!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 43, 0, 0);
        add(jLabel1, gridBagConstraints);

        jLabel2.setText("Jakob");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 31, 0, 0);
        add(jLabel2, gridBagConstraints);

        jLabel3.setText("Tyler");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 0);
        add(jLabel3, gridBagConstraints);

        jLabel4.setText("Yixun");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 22, 0, 94);
        add(jLabel4, gridBagConstraints);

        jLabel5.setText("Website");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(64, 47, 0, 0);
        add(jLabel5, gridBagConstraints);

        jButton1.setText("Click it!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 8, 29, 94);
        add(jButton1, gridBagConstraints);

        loginManager.validate();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
