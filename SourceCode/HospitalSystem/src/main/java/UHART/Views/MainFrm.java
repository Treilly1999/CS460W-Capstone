package UHART.Views;

import UHART.Controllers.LoginManager;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import UHART.Controllers.LoginManager;
import UHART.Models.Staff_Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrm {

	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 */
	public MainFrm(final LoginManager loginManager, final Staff_Model user) {
		//setTitle("Main Interface");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 928, 848);
		
		JMenuBar menuBar = new JMenuBar();
		loginManager.setJMenuBar(menuBar);
		
		JMenu mnNewMenu_2 = new JMenu("Patient");
		mnNewMenu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Search Patient");
		mntmNewMenuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		mnNewMenu_2.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginManager.search(user);
			}
		});
		
		JMenu mnNewMenu = new JMenu("Setting");
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Change Password");
		mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Log out");
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_1);
                
                mntmNewMenuItem_1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginManager.logout();
                    }
                });
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("About us");
		mntmNewMenuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginManager.setContentPane(contentPane);
		
                
                //-----------------USER SPECIFIC BUTTONS-----------------------
		JButton btnNewButton = new JButton("Add Patient Record");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
                btnNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginManager.showRegisterPanel(user);
                    }
                });
		
		JButton btnAddDiagno = new JButton("Add Diagnosis Information");
		btnAddDiagno.setFont(new Font("����", Font.PLAIN, 20));
                btnAddDiagno.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginManager.showDiagnosisPanel(user);
                    }
                });
		
		JButton btnUpdatePatientRecord = new JButton("Update Patient Record");
		btnUpdatePatientRecord.setFont(new Font("����", Font.PLAIN, 20));
                btnUpdatePatientRecord.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginManager.showUpdatePanel(user);
                    }
                });
		
		JButton btnCheckPatientRecord = new JButton("Search");
		btnCheckPatientRecord.setFont(new Font("����", Font.PLAIN, 20));
                btnCheckPatientRecord.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //TODO: Add view for searching patient\
						loginManager.search(user);
                    }
                });
		
		JButton btnBilling = new JButton("Billing ");
		btnBilling.setFont(new Font("����", Font.PLAIN, 20));
                btnBilling.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginManager.showBillingPanel(user);
                    }
                });
                
                if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.REGISTER)
                {
                    btnAddDiagno.setVisible(false);
                    btnUpdatePatientRecord.setVisible(false);                    
                    btnBilling.setVisible(false);        
					
					btnCheckPatientRecord.setVisible(false);
                }
                else if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.NURSE)
                {
                    btnNewButton.setVisible(false);
                    btnBilling.setVisible(false);
					btnAddDiagno.setVisible(false);
                    btnUpdatePatientRecord.setVisible(false);  
                }
                else if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DOCTOR)
                {
                    btnNewButton.setVisible(false);
                    btnBilling.setVisible(false);
					btnAddDiagno.setVisible(false);
                    btnUpdatePatientRecord.setVisible(false);    
                }
                else if(user.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.BILLING)
                {
                    btnNewButton.setVisible(false);
                    btnUpdatePatientRecord.setVisible(false);
					btnAddDiagno.setVisible(false);                  
                    btnBilling.setVisible(false);        
                }
                
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddDiagno, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUpdatePatientRecord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCheckPatientRecord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnBilling, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(btnAddDiagno, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(btnUpdatePatientRecord, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(btnCheckPatientRecord, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addComponent(btnBilling, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(142, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
