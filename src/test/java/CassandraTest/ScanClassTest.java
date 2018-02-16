package CassandraTest;

import Cassandra.ScanClass;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class ScanClassTest {
    private static ScanClass test = new ScanClass();
    private static UUID theUUID = UUID.randomUUID();
    private static String scanType = "THETYPE";
    private static String theDate = "01-05-2017T12";
    private static DateFormat format = new SimpleDateFormat("dd-MM-yyyy'T'HH");
    private static Date rowDate = null;


    @BeforeClass
    public  static void initTest() {
        test.setUUID(theUUID);
        test.setscanType(scanType);
        test.setDate(theDate);
        try{
            rowDate = format.parse(theDate);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void getRawDate() {
        Date testDate = test.getRawDate();
        assertEquals(rowDate.toString(),testDate.toString());
    }

    @Test
    public void getUID() {
        assertEquals(theUUID.toString(),test.getUID().toString());
    }



    @Test
    public void getscanType() {
        assertEquals(scanType,test.getscanType());
    }



    @Test
    public void getDate() {
        assertEquals(theDate,test.getDate());
    }
}

