package UHART;

import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import UHART.Controllers.AES256;
import UHART.Controllers.DBConnection;
import UHART.Controllers.LoginController;
import UHART.Models.Address;
import UHART.Models.Patient;
import UHART.Models.Staff_Model;
import UHART.Models.Symptoms;
import UHART.Models.Staff_Model.USER_ROLE;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DBTest extends TestCase
{

    DBConnection dbTest = new DBConnection();
    AES256 aes = new AES256();
    LoginController lcTest;

    Date date = new Date();
    ArrayList<Symptoms> symptoms = new ArrayList<>();
    Symptoms symp = new Symptoms("Fever");    

    ArrayList<String> allergies = new ArrayList<>();    

    Address address = new Address("1 Yeah rd", "12345", "AZ", "USA", "YEAH");

    Patient patient = new Patient("1", "Test", date, "111-111-1111", 921343213, "Dr. Yeah", "987-654-3210", "Anthem",
    symptoms, "Male", allergies, address);

    Staff_Model testUser = new Staff_Model(USER_ROLE.NURSE, 10, "TestUser", "testtest", "testtest");

    public DBTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( DBTest.class );
    }

    public void testParsePatients() throws Throwable 
    {        
        //When
        Document searchParam = new Document();
        searchParam.put("ssn", aes.encrypt("123456789"));

        ArrayList<Patient> result = dbTest.parsePatients(searchParam);
        
        //Then
        assertEquals("123456789", result.get(0).getSSN() + "");
        assertEquals("Tyler Reilly", result.get(0).getName());
        assertEquals("312-805-9892", result.get(0).getPhone());
        assertEquals("Dr. Craig", result.get(0).getPhysician());
        assertEquals("Anthem", result.get(0).getProvider());
        assertEquals("860-860-8600", result.get(0).getPhysicianNumber());
        assertEquals("Anthem", result.get(0).getProvider());
        assertEquals("Male", result.get(0).getGender());
        assertEquals("21 Briarwood Ln", result.get(0).getAddress().getStreet());
        assertEquals("East Hartford", result.get(0).getAddress().getCity());
        assertEquals("CT", result.get(0).getAddress().getState());
        assertEquals("06118", result.get(0).getAddress().getZipcode());
        assertEquals("USA", result.get(0).getAddress().getcountry());
        assertEquals("Peanut", result.get(0).getAllergies().get(0));
        assertEquals("Fever", result.get(0).getSymptoms().get(0).getSymptom());
        assertEquals("Choking", result.get(0).getMedicalHistory().get(0).getReason());
        assertEquals("4/12/2020", result.get(0).getMedicalHistory().get(0).getDate());
        assertEquals("abdominal pain", result.get(0).getDiagnosis().get(0));        
        assertEquals("acetaminophen", result.get(0).getMedications().get(0));    
        assertTrue(3103 == result.get(0).getBill().getPrice());
        //assertEquals("angiogram", result.get(0).getTests().get(3));
        assertEquals("Doing much better. They are starting to walk again.", result.get(0).getProgressReports().get(0).getNote());
        
    }

    public void testCreatePatient() throws Throwable 
    {
        symptoms.add(symp);
        allergies.add("peanut");
        
        String result = dbTest.createPatient(patient, testUser);

        assertEquals("SUCCESSFUL", result);

        Document searchDoc = new Document();
        searchDoc.put("name", "Test");

        dbTest.removeEntry(searchDoc, false);
    }

    public void testUserRetrieval() throws Throwable
    {
        Document loginQuery = new Document();
        loginQuery.put("userName", "nurse");

        lcTest = new LoginController(loginQuery);

        assertEquals("nurse", lcTest.getCurrentUser().getName());
        assertEquals("nurse", lcTest.getCurrentUser().getUserName());

    }

}
