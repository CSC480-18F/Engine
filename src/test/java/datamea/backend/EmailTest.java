package datamea.backend;


import junit.framework.TestCase;

import javax.jws.soap.SOAPBinding;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EmailTest extends TestCase {
    private final File TEXT_FILE1 = new File("TestEmails/1239784613000.txt");
    private final Email EMAIL = new Email(TEXT_FILE1);
    private final File TEXT_FILE2 = new File("TestEmails/1284278477000.txt");
    private final Email EMAIL2 = new Email(TEXT_FILE2);
    private final File TEXT_FILE3 = new File("TestEmails/1322486502000.txt");
    private final Email EMAIL3 = new Email(TEXT_FILE3);
    private final File TEXT_FILE4 = new File("TestEmails/1428001431000.txt");
    private final Email EMAIL4 = new Email(TEXT_FILE4);

    public void setUp() throws Exception {
        super.setUp();
        ArrayList<Email> testEmails = new ArrayList<>();
        testEmails.add(EMAIL);
        testEmails.add(EMAIL2);
        testEmails.add(EMAIL3);
        testEmails.add(EMAIL4);

        //manually create info for testUser
    }

    //still needs work
    public void testRecoverEmail() {
        assertEquals(EMAIL.getSentimentScores()[0],2 );
        assertEquals(EMAIL.getSentimentScores()[4], 4);

        //check for changed values in txt file

    }

    //Done
    public void testGetOverallSentimentDbl() {
        int[] testArray = new int[]{1,1,1,1,1};
        int [] testArray2 = new int[]{2,1,3,4,2};
        assertEquals(Email.getOverallSentimentDbl(testArray), 50.0);
        assertEquals(Email.getOverallSentimentDbl(testArray2), 62.5);
    }

    //ask Andy how to test
    public void testAnalyzeSentiment() {


    }

    //ask Andy what to send filter
    public void testFilter() {
    }

    //Done
    public void testGetDayOfWeek() {
        assertEquals(EMAIL.getDayOfWeek(), 4);
        assertEquals(EMAIL2.getDayOfWeek(), 1);
        assertEquals(EMAIL3.getDayOfWeek(), 2);
        assertEquals(EMAIL4.getDayOfWeek(), 5);
    }

    //need help from Andy
    public void testExtractAttachments() throws MessagingException, IOException {
        ArrayList<String> testAttachments = new ArrayList<>();
        ArrayList<String> expected = new ArrayList<>();
        try{
            testAttachments = EMAIL.extractAttachments();
        }catch (StringIndexOutOfBoundsException s){
            System.out.println(s.getMessage());
        }

        assertEquals(testAttachments, expected);

    }

    //test fails when it shouldn't
    public void testIsAnswered() {
        assertFalse(EMAIL.isAnswered());
        assertTrue(EMAIL4.isAnswered());
    }

    //Done
    public void testGetSentimentScores() {
        int[] testScores = new int[]{2,3,4,5,4};
        for(int i = 0; i < 5; i++){
            assertEquals(EMAIL.getSentimentScores()[i], testScores[i]);
        }
    }

    //need help from Andy
    public void testGetSentimentPctStr() {
    }

    //Done
    public void testGetDate() {
        Date date = new Date(1239784613000L);
        Date date2 = new Date(1284278477000L);
        Date date3 = new Date(1322486502000L);
        Date date4 = new Date(1428001431000L);

        assertEquals(EMAIL.getDate(), date);
        assertEquals(EMAIL2.getDate(), date2);
        assertEquals(EMAIL3.getDate(), date3);
        assertEquals(EMAIL4.getDate(), date4);
    }

    //ask for help
    public void testGetSender() {
        Sender testSender1 = new Sender(User.decrypt("&Mgqigel/$Hjwpyvssl'\"AJntmvxtrm5NgpeehjEphgw1ysk2k{g2giC"));
        boolean test_1 = EMAIL.getSender().filterName().equals(testSender1);
        assertTrue(test_1);
    }

    // needs to be done better
    public void testDetectLanguage(){
        String testLanguage = "Hello, World. This is a test";
        assertEquals(EMAIL.detectLanguage(testLanguage), "en");
    }

    // flags are broken?
    public void testGetFlags() {
        assertEquals(EMAIL.getFlags(), ("\\" + "Seen"));
    }

    //Done
    public void testGetFolder() {
        assertEquals(EMAIL.getFolder(), "Archiv UniDuE Adresse");
        assertEquals(EMAIL4.getFolder(),"Betreute Arbeiten");
    }

    //Done
    public void testGetSubFolder() {
        assertEquals(EMAIL.getSubFolder(), "Archiv UniDuE Adresse");
        assertEquals(EMAIL4.getSubFolder(), "Betreute Arbeiten");
    }

    //Done
    public void testGetAttachments() {
        ArrayList<String> testAttachments = new ArrayList<>();
        testAttachments.add(".pdf");
        testAttachments.add(" .pdf");
        testAttachments.add(" .txt");

        for(int i = 0; i< testAttachments.size(); i++){
            assertEquals(testAttachments.get(i), EMAIL3.getAttachments().get(i));
        }

    }

    //Done
    public void testGetLanguage() {
        boolean test_1 = EMAIL.getLanguage().equals("unk");
        boolean test_2 = EMAIL2.getLanguage().equals("en");
        assertTrue(test_1);
        assertTrue(test_2);
    }
    //TODO test on rest of emails
    public void testGetDomain() {
        boolean same = EMAIL.getDomain(false).get(0).trim().equals("@ibes.uni-due.de");
        assertTrue(same);
    }


}