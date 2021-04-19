package UHART.Views;

import UHART.Controllers.LoginController;
import UHART.Controllers.LoginManager;
import UHART.Models.Staff_Model;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import org.bson.Document;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;

public class LoginFrm {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField passwordField;
	private LoginController loginController;
	private JTextArea errorMessage = new JTextArea();
	//private String sha256hex;
	
	Document staffQuery = new Document();
	private Staff_Model currentUser;
        
//        public Staff_Model getCurrentUser()
//        {
//            return currentUser;
//        }
        
        public JPanel getLoginForm() { return contentPane; }

	/**
	 * Create the frame.
	 */
	public LoginFrm(final LoginManager loginManager) {
//		addWindowListener(new WindowAdapter() {
//		});
//		setTitle("Hospital System");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 849, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
                
		errorMessage.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
                errorMessage.setLineWrap(true);
                errorMessage.setWrapStyleWord(true);
                errorMessage.setOpaque(false);
                errorMessage.setEditable(false);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Hospital Management System");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/UHART/Icon/\u533B\u9662.png")));
		lblNewLabel_2.setBounds(178, 72, 364, 35);
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_2);
		//setContentPane(contentPane);
		
		
		JLabel lblNewLabel = new JLabel("User Name: ");
		lblNewLabel.setIcon(new ImageIcon(LoginFrm.class.getResource("/UHART/Icon/\u7528\u6237.png")));
		lblNewLabel.setBounds(68, 203, 160, 29);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		user = new JTextField();
		user.setBounds(282, 210, 325, 21);
		user.setColumns(10);
		contentPane.add(user);
		
		JLabel lblNewLabel_1 = new JLabel("User Password: ");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/UHART/Icon/\u5BC6\u7801.png")));
		lblNewLabel_1.setBounds(66, 298, 186, 29);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		 final JLabel lblNewLabel_3 = new JLabel("");
         lblNewLabel_3.setEnabled(false);
         lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 20));
         lblNewLabel_3.setBounds(462, 453, 145, 37);
         contentPane.add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(282, 295, 325, 35);
		passwordField.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		contentPane.add(passwordField);
		
                JButton btnNewButton = new JButton("Log In");
                btnNewButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/UHART/Icon/log-in.png")));
                btnNewButton.setBounds(212, 453, 168, 37);
                loginManager.getRootPane().setDefaultButton(btnNewButton);
                btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		// function to login to other interface and start the program
                                staffQuery.put("userName", user.getText());
                                if(!user.getText().isEmpty() && !passwordField.getText().isEmpty())
                						{
                							try
                							{
                								
                								loginController = new LoginController(staffQuery);
                								currentUser = loginController.getCurrentUser();
                								//System.out.println(currentUser.toString());
	
                								if(currentUser.getUSER_ROLE() == UHART.Models.Staff_Model.USER_ROLE.DEFAULT)
                								{
                									//errorMessage.setText("Unauthorized Access. Contact your system administrator.");
                									//errorMessage.setTextFill(Color.web("#0076a3"));
                								}
                								//System.out.println("BEFORE AUTHORZE");
                								Boolean accepted = loginController.authorize(currentUser, Hashing.sha256().hashString(passwordField.getText(), StandardCharsets.UTF_8).toString(), user.getText());
                								//System.out.println("AFTER AUTHORZE");
                								if (accepted) {
                								  loginManager.authenticated(currentUser);
                								}
                							}
                							catch (Exception exception)
                							{
                								System.out.println("STAFF MEMBER NOT FOUND");
                								lblNewLabel_3.setEnabled(true);
                								lblNewLabel_3.setText("STAFF MEMBER NOT FOUND");
                								exception.printStackTrace();
                								//TODO: WHAT TO DO IF LOGIN FAIL
                								//loginManager.showLoginScreen(); 
                								errorMessage.setText("Username/Password inccorect.");
                								lblNewLabel_3.setEnabled(true);
                								lblNewLabel_3.setText("Username/Password inccorect.");
                								//errorMessage.setTextFill(Color.web("#0076a3"));
                							}
                						}
                               
                               
                	}
                });
                contentPane.add(btnNewButton);
                
	}
}
