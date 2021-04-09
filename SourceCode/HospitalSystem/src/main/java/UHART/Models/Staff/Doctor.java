package UHART.Models.Staff;

import java.util.Scanner;
import java.util.ArrayList;
import UHART.Models.Patient;
import UHART.Models.Address;

public class Doctor extends UHART.Models.Staff_Model {
	ArrayList<Patient> patients;
	Doctor(int i, String p, Address a, String n)
	{
		id = i;
		phoneNumber = p;
		address = a;
		name = n;
	}
	
	/*public void enterDiagnosis(Patient p, Diagnoses d)
	{
		
	}
	
	public void orderTests(Patient p, Tests_procedures t)
	{
		
	}
	
	public void orderMedications(Patient p, Medications m)
	{
		
	}*/
	
	public void enterDischargeInstructions(Patient p)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter discharge instructions");
		String s = scan.next();
		p.setDischargeInstructions(s);
		scan.close();
	}
	
	public void addPatient(Patient p)
	{
		patients.add(p);
		p.setAssignedDoctor(this.name);
	}
	
	public void removePatient(Patient p)
	{
		patients.remove(p);
		p.setAssignedDoctor(null);
	}
}