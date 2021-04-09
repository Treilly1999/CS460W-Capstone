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
		//setContentPane(contentPane);
		
		
		JLabel lblNewLabel = new JLabel("User Name: ");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("Hospital Management System");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 25));
		
		user = new JTextField();
		user.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User Password: ");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		
                
		errorMessage.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
                errorMessage.setLineWrap(true);
                errorMessage.setWrapStyleWord(true);
                errorMessage.setOpaque(false);
                errorMessage.setEditable(false);
		
                JButton btnNewButton = new JButton("Log In");
        		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// function to login to other interface and start the program
                                staffQuery.put("userName", user.getText());
                                
                                try
                                {
                                    
                                    loginController = new LoginController(staffQuery);
                                    currentUser = loginController.getCurrentUser();
                                    System.out.println(currentUser.toString());

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
                                    exception.printStackTrace();
                                    //TODO: WHAT TO DO IF LOGIN FAIL
                                    //loginManager.showLoginScreen(); 
                                    errorMessage.setText("Username/Password inccorect.");
                                    //errorMessage.setTextFill(Color.web("#0076a3"));
                                }
                               
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);       
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(133)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(185, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(113)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addGap(35))
									.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGap(107)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(passwordField)
									.addComponent(user, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))))
						.addGap(134))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(61)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addGap(81)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(54)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
						.addComponent(btnNewButton)
						.addGap(82))
			);
		contentPane.setLayout(gl_contentPane);
	}
}
