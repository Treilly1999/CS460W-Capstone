import java.util.Scanner;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;

public class Doctor extends StaffMember {
	ArrayList<Patient> patients;
	Doctor(int i, int p, String a, String n)
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
		p.setDischargeInstructions(new SimpleStringProperty(s));
		scan.close();
	}
	
	public void addPatient(Patient p)
	{
		patients.add(p);
		p.setAssignedDoctor(new SimpleStringProperty(this.name));
	}
	
	public void removePatient(Patient p)
	{
		patients.remove(p);
		p.setAssignedDoctor(new SimpleStringProperty(null));
	}
}
