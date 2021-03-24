
public class Registration extends StaffMember{
	Registration(int i, int p, String a, String n)
	{
		id = i;
		phoneNumber = p;
		address = a;
		name = n;
	}
	
	public Patient createPatient(String name, Integer age, String phoneNumber, Integer ssn, String physicianName, 
            String physicianNumber)
	{
		return new Patient(name, age, phoneNumber, ssn, physicianName, physicianNumber);
	}
}
