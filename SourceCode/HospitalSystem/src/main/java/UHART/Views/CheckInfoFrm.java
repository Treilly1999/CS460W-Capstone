package UHART.Views;

import UHART.Controllers.LoginManager;
import UHART.Models.Staff_Model;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CheckInfoFrm {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public CheckInfoFrm(LoginManager loginManager, Staff_Model user) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 800, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		loginManager.setContentPane(contentPane);
	}

}
