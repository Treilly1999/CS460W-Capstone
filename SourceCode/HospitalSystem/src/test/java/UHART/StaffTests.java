package UHART;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import UHART.*;
import UHART.Models.Address;
import UHART.Models.Bill;
import UHART.Models.Medications;
import UHART.Models.Patient;
import UHART.Models.Staff_Model;
import UHART.Models.Symptoms;
import UHART.Models.Tests_procedures;
import UHART.Models.Staff.Doctor;
import UHART.Models.Staff.Nurse;
import UHART.Models.Staff_Model.USER_ROLE;

public class StaffTests
{
	static Date date = new Date();
	static ArrayList<Symptoms> symptoms = new ArrayList<>();
	static ArrayList<String> allergies = new ArrayList<>();
	static Address address = new Address("1234 road road", "12345" , "CT", "USA", "EH");
	static Patient patient = new Patient("1", "Name", date, "111-111-1111", 111111111, "Dr. Doctor", "111-111-1112","Anthem Blue Cross", symptoms, "Female", allergies, address);
	static Nurse nurse = new Nurse(1, "p", address, "n");
	static Doctor doctor = new Doctor(1, "p", address, "n");
	static Bill bill = new Bill(false);
	
	
	public StaffTests()
	{
		
	}
	
	@Test
	public void testPrintBill() throws Throwable
	{
		bill.addItem(Medications.IBUPROFIN);
		bill.addItem(Tests_procedures.CTSCAN);
		String s = bill.toString();
		String t = String.format("15%s", "ibuprofin") + String.format("%d", 15) + "\n" + String.format("15%s", "ct scan") + String.format("%d", 3275) + "\n" + "Total cost: 3290";
		//assertEquals(t, s);
	}
	
	@Test
	public void testEnterDischargeInstructions() throws Throwable
	{
		InputStream sysInBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("Lorem ipsum dolor sit amet".getBytes());
		System.setIn(in);
		
		doctor.enterDischargeInstructions(patient);
		//assertEquals("Lorem ipsum dolor sit amet", patient.getDischargeInstructions());
		
		System.setIn(sysInBackup);
	}
	
	@Test
	public void testPrintDischargeInstructions() throws Throwable
	{
		patient.setDischargeInstructions("Lorem ipsum dolor sit amet");
		nurse.printDischargeInstructions(patient);
		File actual = new File("Name.txt");
		File expected = new File("expected.txt");
		//assertEquals(expected, actual);
	}
}