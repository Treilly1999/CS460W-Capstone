package Views;

import Controllers.DBConnection;
import Controllers.LoginManager;
import Models.Staff_Model;
import com.google.common.hash.Hashing;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.nio.charset.StandardCharsets;
import org.bson.Document;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class LoginFrm {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField passwordField;
        
        private String sha256hex;
        
        Document staffQuery = new Document();
        private Staff_Model currentUser;      
        
        public Staff_Model getCurrentUser()
        {
            return currentUser;
        }
        
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
		
		JLabel lblNewLabel = new JLabel("Hospital Management System");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 25));
		
		JLabel lblNewLabel_1 = new JLabel("User Name :");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		user = new JTextField();
		user.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("User Password :");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("User Type");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 20));
                
                JTextArea errorMessage = new JTextArea();
		errorMessage.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
                errorMessage.setLineWrap(true);
                errorMessage.setWrapStyleWord(true);
                errorMessage.setOpaque(false);
                errorMessage.setEditable(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Register", "Doctor", "Nurse", "Biller"}));
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// function to login to other interface and start the program
                                staffQuery.put("userName", user.getText());
                        
                                try
                                {
                                    currentUser = findStaffMember(staffQuery);  
                                    System.out.println(currentUser.toString());

                                    if(currentUser.getUSER_ROLE() == Models.Staff_Model.USER_ROLE.DEFAULT)
                                    {
                                        //errorMessage.setText("Unauthorized Access. Contact your system administrator.");
                                        //errorMessage.setTextFill(Color.web("#0076a3"));
                                    }
                                    //System.out.println("BEFORE AUTHORZE");
                                    Boolean accepted = authorize(currentUser);
                                    //System.out.println("AFTER AUTHORZE");
                                    if (accepted) {
                                      loginManager.authenticated(currentUser);
                                    }
                                }
                                catch (Exception exception)
                                {
                                    System.out.println("STAFF MEMBER NOT FOUND");
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(user, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(248, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(224))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(372)
					.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(185, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(63)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

        
        /*
    Author: Tyler
    Description: Finds the staff member in the login query, builds a current user profile from
    staff_model and decides to log them in or not.
    */
    public Staff_Model findStaffMember(Document query)
    {
        //MongoClient mongoClient = new MongoClient("localhost", 27017);
        //MongoDatabase database = mongoClient.getDatabase("hospital");
        MongoCollection<Document> collection = DBConnection.getDB().getCollection("staff");
        
        Staff_Model staffMember = new Staff_Model();
            
        FindIterable<Document> findIterable = findIterable = collection.find(query);

        MongoCursor<Document> cursor = findIterable.iterator();

        Document staff = new Document();

        try
        {
            while(cursor.hasNext())
            {               
                staff = cursor.next();
            }
        } finally {
            cursor.close();
        }
              
        staffMember = buildCurrentUser(staff);
        
        //System.out.println(staffMember.getUSER_ROLE());
        
        return staffMember;
    }
  
    /*
    Author: Tyler
    Description: Builds a Staff_Model for the current user
    TODO: Consolidate with parsePatients? Less code && makes them more dynamic
    */
    public static Staff_Model buildCurrentUser(Document staffMember)
    {
                
        Staff_Model staff;
        
        String name = "", userName = "", password="";
        int id = 0;
        
        Models.Staff_Model.USER_ROLE role = Models.Staff_Model.USER_ROLE.DEFAULT;
        //= queryParameters.get("user_role");
       
        if(staffMember.getString("user_role").equals("NURSE"))            
            role = Models.Staff_Model.USER_ROLE.NURSE;                        
        if(staffMember.getString("user_role").equals("DOCTOR"))
            role = Models.Staff_Model.USER_ROLE.DOCTOR;
        if(staffMember.getString("user_role").equals("REGISTER"))
            role = Models.Staff_Model.USER_ROLE.REGISTER;
        if(staffMember.getString("user_role").equals("BILLING"))
            role = Models.Staff_Model.USER_ROLE.BILLING;        
       
        
        //System.out.println(queryParameters.get("user_role"));
        try
        {
            name = staffMember.getString("name");
            id = Integer.parseInt(staffMember.getString("id"));
            userName = staffMember.getString("userName");
            password = staffMember.getString("password");            
            
          } catch (Exception e)
          {
              System.out.println("Some query parameters were not found in " + name + " profile");
          }   
       
      //System.out.println("SUCCEDED");
      staff = new Staff_Model(role, id, name, userName, password);
      return staff;
    }

  /**
   * Check authorization credentials.
   */   
  private Boolean authorize(Staff_Model staffMember) {
       //System.out.println("INSIDE AUTHORZE");
       //System.out.println("Username: " + staffMember.getUserName() + ". Password: " + staffMember.getPassword());
       
       //TODO: ADD SALT TO PASSWORD TO MAKE EVERY PASSWORD DIFFERENT
       sha256hex = Hashing.sha256().hashString(passwordField.getText(), StandardCharsets.UTF_8).toString();
    
       //System.out.println("AFTER HASHING: " + sha256hex);
       
       return staffMember.getUserName().equals(user.getText()) && staffMember.getPassword().equals(sha256hex);
  }
}
