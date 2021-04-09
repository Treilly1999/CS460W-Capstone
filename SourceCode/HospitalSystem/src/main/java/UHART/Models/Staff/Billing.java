package UHART.Models.Staff;

import UHART.Models.Patient;
import UHART.Models.Medications;
import UHART.Models.Tests_procedures;
import UHART.Models.Address;

public class Billing extends UHART.Models.Staff_Model{

	Billing(int i, String p, Address a, String n)
	{
		id = i;
		phoneNumber = p;
		address = a;
		name = n;
	}
	
	//TODO: Implemenet add payment in Patient
	public void addPayment(Patient p, Medications m)
	{
		//p.addPayment(m.getPrice());
	}
	
	public void addPayment(Patient p, Tests_procedures t)
	{
		//p.addPayment(t.getPrice());
	}
	
	public void markPaid(Patient p)
	{
		p.markPaid();
	}
}
