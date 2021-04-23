package UHART;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import UHART.*;

public class StaffTests
{
	static Date date = new Date();
	static ArrayList<Symptoms> symptoms = new ArrayList<>();
	static ArrayList<String> allergies = new ArrayList<>();
	static Address address = new Address("1234 road road", "12345" , "CT", "USA", "EH");
	static Patient patient = new Patient("1", "Name", date, "111-111-1111", 111111111, "Dr. Doctor", "111-111-1112","Anthem Blue Cross", symptoms, "Female", allergies, address);
	static Staff_Model nurse = new Staff_Model(USER_ROLE.NURSE, 1, "Nurse", "nurse", "nurse");
	static Staff_Model doctor = new Staff_Model(USER_ROLE.DOCTOR, 2, "Doctor", "doctor", "doctor");
	static Bill bill = new Bill(false);
	
	
	public StaffTests
	{
		
	}
	
	@Test
	public void testPrintBill() throws Throwable
	{
		bill.addItem(Medications.IBUPROFIN);
		bill.addItem(Tests_procedures.CTSCAN);
		String s = bill.toString();
		String t = String.format("15%s", "ibuprofin") + String.format("%d", 15) + "\n" + String.format("15%s", "ct scan") + String.format("%d", 3275) + "\n" + "Total cost: 3290";
		assertEquals(t, s);
	}
	
	@Test
	public void testEnterDischargeInstructions() throws Throwable
	{
		InputStream sysInBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("Lorem ipsum dolor sit amet".getBytes());
		System.setIn(in);
		
		doctor.enterDischargeInstructions(patient);
		assertEquals("Lorem ipsum dolor sit amet", patient.getDischargeInstructions());
		
		System.setIn(sysInBackup);
	}
	
	@Test
	public void testPrintDischargeInstructions() throws Throwable
	{
		patient.setDischargeInstruction("Lorem ipsum dolor sit amet");
		nurse.printDischargeInstructions(patient);
		File actual = new File("Name.txt");
		File expected = new File("expected.txt");
		assertThat(actual).hasSameContentAs(expected);
	}
}