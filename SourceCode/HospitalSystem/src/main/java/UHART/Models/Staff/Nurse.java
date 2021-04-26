package UHART.Models.Staff;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import UHART.Models.Patient;
import UHART.Models.Address;

/*
Author: Jakob
*/
public class Nurse extends UHART.Models.Staff_Model{
	public Nurse(int i, String p, Address a, String n)
	{
		id = i;
		phoneNumber = p;
		address = a;
		name = n;
	}
	
	public void admitPatient(Patient p)
	{
		//todo. assigns patient to doctor. pick doctor with fewest patients. can't implement this until i see how we're storing doctors
	}
	
    //TODO: Connect to backend
	public void recordProgress(Patient p)
	{
		// Scanner scan = new Scanner(System.in);
		// String report = "";
		// while(scan.nextLine().length() != 0)
		// {
		// 	report += scan.nextLine();
		// }
		// p.setProgressReports(report);
		// scan.close();
	}
	
	public static void printDischargeInstructions(Patient p)
	{
		String filename = p.getName() + ".txt";
		try
		{
			File f = new File(filename);
			f.createNewFile();
			FileWriter w =  new FileWriter(filename);
			w.write(p.getDischargeInstructions());
			w.close();
			System.out.println("Discharge instructions saved to computer.");
		}
		catch (IOException e)
		{
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
}