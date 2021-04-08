
public class Billing extends StaffMember{
	Billing(int i, int p, String a, String n)
	{
		id = i;
		phoneNumber = p;
		address = a;
		name = n;
	}
	
	public void addPayment(Patient p, Medications m)
	{
		p.addPayment(m.getPrice());
	}
	
	public void addPayment(Patient p, Tests_procedures t)
	{
		p.addPayment(t.getPrice());
	}
	
	public void markPaid(Patient p)
	{
		p.markPaid();
	}
}
