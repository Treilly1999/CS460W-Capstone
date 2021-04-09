/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UHART.Controllers;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author Tyler
 */
public class RegisterController 
{
    
    public void create(JPanel panel, GroupLayout layout) {        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);        
        
       // addMedHist(layout, parallel, sequential);
        
        JLabel label = new JLabel("Medical History");
        JLabel datelbl = new JLabel("Date:");
        JLabel reasonlbl = new JLabel("Reason");
        
        JTextField date = new JTextField();
        datelbl.setLabelFor(date);
        JTextField reason = new JTextField();
        reasonlbl.setLabelFor(reason);
        
        layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGap(122)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)						
						.addGroup(layout.createSequentialGroup()
                                                        .addGap(65)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(datelbl, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(date, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(reasonlbl, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
								.addComponent(reason, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE))))						
					.addContainerGap(220, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()    
					.addGap(77)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(datelbl, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)	
                                                .addComponent(reasonlbl, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(reason, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))	
					.addContainerGap(53, Short.MAX_VALUE))
		);
        
        
        panel.setLayout(layout);
    }    
}
