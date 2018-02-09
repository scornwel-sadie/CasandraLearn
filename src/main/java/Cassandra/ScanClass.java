package Cassandra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

public class ScanClass {

    private UUID theId = null;
    private String scanType = "";
    private Date rowDate = null;
    private DateFormat format = new SimpleDateFormat("dd-MM-yyyy'T'HH");
    public void setUUID(UUID theId){
        this.theId = theId;
    }

    public Date getRawDate(){
        return this.rowDate;
    }

    public UUID getUID(){
        return this.theId;
    }

    public void setscanType(String scanType){
        this.scanType = scanType;
    }

    public String getscanType(){
        return this.scanType;
    }

    public void setDate(String theDate){

        try{
            rowDate = format.parse(theDate);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

    public String getDate(){
        return format.format(rowDate);
    }

    @Override
    public String toString() {
        return "[ rollno=" + scanType + ", name=" + getDate() + "]";
    }

    public static Comparator<ScanClass> ScanDateComparator = new Comparator<ScanClass>() {
        public int compare(ScanClass s1, ScanClass s2){
            Date date1 = s1.getRawDate();
            Date date2 = s2.getRawDate();
            //ascending order
           return date1.compareTo(date2);

            //descending order
        //    return date2.compareTo(date1);

        }
    };

}
