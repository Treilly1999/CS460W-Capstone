import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Nurse extends StaffMember{
	Nurse(int i, int p, String a, String n)
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
	
	public void recordProgress(Patient p)
	{
		Scanner scan = new Scanner(System.in);
		String report = "";
		while(scan.nextLine().length() != 0)
		{
			report += scan.nextLine();
		}
		p.setProgressReports(report);
		scan.close();
	}
	
	public void printDischargeInstructions(Patient p)
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
