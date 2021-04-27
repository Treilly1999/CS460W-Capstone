package UHART;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import UHART.Controllers.AES256;
import UHART.Controllers.DBConnection;
import UHART.Controllers.LoginController;
import UHART.Models.Address;
import UHART.Models.MedicalHistory;
import UHART.Models.Patient;
import UHART.Models.ProgressReport;
import UHART.Models.Staff_Model;
import UHART.Models.Symptoms;
import UHART.Models.Staff_Model.USER_ROLE;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

//import junit.framework.TestCase;
//import junit.framework.TestSuite;

public class DBTest
{
    static DBConnection dbTest = new DBConnection();
    AES256 aes = new AES256();
    LoginController lcTest;

    static Date date = new Date();
    static ArrayList<Symptoms> symptoms = new ArrayList<>();
    static Symptoms symp = new Symptoms("Fever");    

    static ArrayList<String> allergies = new ArrayList<>();    

    static Address address = new Address("1 Yeah rd", "12345", "AZ", "USA", "YEAH");

    static Patient patient = new Patient("1", "Test", date, "111-111-1111", 921343213, "Dr. Yeah", "987-654-3210", "Anthem",
    symptoms, "Male", allergies, address);

    static Staff_Model testUserNur = new Staff_Model(USER_ROLE.NURSE, 10, "TestUser", "testtest", "testtest");
    Staff_Model testUserDoc = new Staff_Model(USER_ROLE.DOCTOR, 10, "TestUser", "testtest", "testtest");

    MongoCollection<Document> col = dbTest.getDB().getCollection("patients");

    static Document searchDoc = new Document("name", patient.getName());

    public DBTest()
    {
        System.out.println("--------RUNNING TEST CREATE FIRST ---------");
        symptoms.add(symp);
        allergies.add("peanut");
        
        String result = dbTest.createPatient(patient, testUserNur);

        assertEquals("SUCCESSFUL", result);

        searchDoc.put("name", patient.getName());

        MedicalHistory medHist = new MedicalHistory("04/21/2021", "Choking");

        dbTest.createMedicalHistory(medHist, searchDoc);

        Patient patient = dbTest.parsePatient(searchDoc);

        assertEquals("04/21/2021", patient.getMedicalHistory().get(0).getDate());
        assertEquals("Choking", patient.getMedicalHistory().get(0).getReason());
    }

    // public static Test suite()
    // {
    //     return new TestSuite( DBTest.class );
    // }    
    
    @Test
    public void testParsePatients() throws Throwable 
    {        
        //When
        Document searchParam = new Document();
        searchParam.put("ssn", aes.encrypt("123456789"));

        Patient patient = dbTest.parsePatient(searchParam);
        
        //Then
        Assertions.assertEquals("123456789", patient.getSSN() + "");
        assertEquals("Tyler Reilly", patient.getName());
        assertEquals("312-805-9892", patient.getPhone());
        assertEquals("Dr. Craig", patient.getPhysician());
        assertEquals("Anthem", patient.getProvider());
        assertEquals("860-860-8600", patient.getPhysicianNumber());
        assertEquals("Anthem", patient.getProvider());
        assertEquals("Male", patient.getGender());
        assertEquals("21 Briarwood Ln", patient.getAddress().getStreet());
        assertEquals("East Hartford", patient.getAddress().getCity());
        assertEquals("CT", patient.getAddress().getState());
        assertEquals("06118", patient.getAddress().getZipcode());
        assertEquals("USA", patient.getAddress().getcountry());
        assertEquals("Peanut", patient.getAllergies().get(0));
        assertEquals("Fever", patient.getSymptoms().get(0).getSymptom());
        assertEquals("Choking", patient.getMedicalHistory().get(0).getReason());
        assertEquals("4/12/2020", patient.getMedicalHistory().get(0).getDate());
        assertEquals("abdominal pain", patient.getDiagnosis().get(0));        
        assertEquals("acetaminophen", patient.getMedications().get(0));    
        assertTrue(0 == patient.getBill().getPrice());
        //assertEquals("angiogram", result.get(0).getTests().get(3));
        assertEquals("Doing much better. They are starting to walk again.", patient.getProgressReports().get(0).getNote());

        
    }

    /*
    Author: Tyler Reilly
    Description: Tests patient creation and deletion.
    */
    // @Test
    // public void testCreatePatient() throws Throwable 
    // // {
    //     System.out.println("--------RUNNING TEST CREATE FIRST ---------");
    //     symptoms.add(symp);
    //     allergies.add("peanut");
        
    //     String result = dbTest.createPatient(patient, testUserNur);

    //     assertEquals("SUCCESSFUL", result);

    //     searchDoc.put("name", patient.getName());

    //     MedicalHistory medHist = new MedicalHistory("04/21/2021", "Choking");

    //     dbTest.createMedicalHistory(medHist, searchDoc);

    //     ArrayList<Patient> resultList = dbTest.parsePatients(searchDoc);

    //     assertEquals("04/21/2021", resultList.get(0).getMedicalHistory().get(0).getDate());
    //     assertEquals("Choking", resultList.get(0).getMedicalHistory().get(0).getReason());
    // }

    /*
    Author: Tyler Reilly
    Description: Tests user retrieval for login.
    */
    @Test
    public void testUserRetrieval() throws Throwable
    {
        System.out.println("--------RUNNING TEST USER RETRIEVAL ---------");
        Document loginQuery = new Document();
        loginQuery.put("userName", "nurse");

        lcTest = new LoginController(loginQuery);

        assertEquals("nurse", lcTest.getCurrentUser().getName());
        assertEquals("nurse", lcTest.getCurrentUser().getUserName());

    }

    /*
    Author: Tyler Reilly
    Description: Tests that the same document can be retrieved with different search parameters
    */
    @Test
    public void testPatientRetrieval() throws Throwable
    {
        System.out.println("--------RUNNING TEST PATIENT RETRIEVAL---------");
        Document searchParam = new Document();
        searchParam.put("ssn", aes.encrypt("123456789"));

        Patient patient = dbTest.parsePatient(searchParam);

        Document searchParam2 = new Document();
        searchParam2.put("name", "Tyler Reilly");

        Patient patient2 = dbTest.parsePatient(searchParam2);

        Document loginQuery = new Document();
        loginQuery.put("userName", "nurse");

        lcTest = new LoginController(loginQuery);

        assertEquals(patient.getAddress().getCity(), patient2.getAddress().getCity());
        assertNotSame(patient2, lcTest);
    }

    /*
    Author: Tyler Reilly
    Description: Test that the method markBillPaid sets the bill in the patients record to 0 and paid.
    */
    @Test
    public void testMarkBillPaid()
    {     
        System.out.println("--------RUNNING TEST MARK BILL PAID---------");
        dbTest.markBillPaid(searchDoc);

        Patient patient = dbTest.parsePatient(searchDoc);

        assertTrue(patient.getBill().getPrice() == 0);

    }

    /*
    Author: Tyler Reilly
    Description: Test if patient is admitted when admit() is called and then checkedout when checkOut is called
    */
    @Test
    public void testAdmitAndCheckOut()
    {
        System.out.println("--------RUNNING TEST ADMIT ---------");
        dbTest.admit(searchDoc);

        Patient admitted = dbTest.parsePatient(searchDoc);

        assertTrue(admitted.getAdmittedBool());

        dbTest.checkOut(searchDoc);

        Patient checkedOut = dbTest.parsePatient(searchDoc);

        assertTrue(!checkedOut.getAdmittedBool());
        assertTrue(checkedOut.getCheckOut());

    }

    @Test
    public void testCreateTestProcedures()
    {
        System.out.println("--------RUNNING TEST TEST PROCEDURES---------");
        dbTest.createTestsProcedures(UHART.Models.Tests_procedures.ANGIOGRAM, testUserDoc, searchDoc);
 
        Patient testTests = dbTest.parsePatient(searchDoc);
 
        assertEquals(UHART.Models.Tests_procedures.ANGIOGRAM.toString(), testTests.getTests().get(0));
    }

    @Test
    public void testCreateDiagnosis()
    {
        System.out.println("--------RUNNING TEST DIAGNOSIS ---------");
        dbTest.createDiagnosis(UHART.Models.Diagnoses.ABDOMINALPAIN.toString(), testUserDoc, searchDoc);
 
        Patient testTests = dbTest.parsePatient(searchDoc);
 
        assertEquals(UHART.Models.Diagnoses.ABDOMINALPAIN.toString(), testTests.getDiagnosis().get(0));
    }

    @Test
    public void testCreateProgressReports()
    {
        System.out.println("--------RUNNING TEST PROGRESS REPORTS---------");
        ProgressReport prog = new ProgressReport("Sydnie", "04/21/2021", "TEST");

        dbTest.createProgressReports(prog, testUserNur, searchDoc);
 
        Patient testTests = dbTest.parsePatient(searchDoc);
 
        assertEquals("TEST", testTests.getProgressReports().get(0).getNote());
        assertEquals("Sydnie", testTests.getProgressReports().get(0).getNurseName());
        assertEquals("04/21/2021", testTests.getProgressReports().get(0).getDate());
    }

    @Test
    public void testCreateMedications()
    {
        System.out.println("--------RUNNING TEST MEDICATIONS ---------");
        dbTest.createMedications(UHART.Models.Medications.ACETAMINOPHEN.toString(), testUserDoc, searchDoc);
 
        Patient testTests = dbTest.parsePatient(searchDoc);
 
        assertEquals(UHART.Models.Medications.ACETAMINOPHEN.toString(), testTests.getMedications().get(0));
    }

    //TODO: Add cleanup
    // @Override
    // public void testRunFinished(Result result) throws Exception {
    //     // TODO Auto-generated method stub
    //     super.testRunFinished(result);
    //     System.out.println("--------RUNNING TEST DELETE ---------");
    //     dbTest.removeEntry(searchDoc, false);
    // }

    
    
}
