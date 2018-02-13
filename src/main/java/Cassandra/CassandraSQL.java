package Cassandra;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CassandraSQL {
    String serverIP = "127.0.0.1";
    String keyspace = "casb_data_security_core_keyspace";
    private Cluster cluster = null;
    private Session session = null;
    private List<ScanClass> theData = new ArrayList<ScanClass>();

    public boolean Init(){

        cluster = Cluster.builder()
                .addContactPoints(serverIP)
                .build();

        session = cluster.connect(keyspace);

        return true;
    }

    public void AddRetroScan(){
        UUID uuid = UUID.randomUUID();
        String sqlUuid = uuid.toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy'T'HH");
        Calendar c = Calendar.getInstance();
        Date dt = new Date();
        /*String today = sdf.format(dt);


        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        today = sdf.format(dt);

        String cqlStatement = "";*/

        for(int i =0; i<200; i++){
          //  System.out.println("The date is " + today);
        //    Calendar c = Calendar.getInstance();
            dt = new Date();
            c.setTime(dt);
            c.add(Calendar.DATE, i);
            dt = c.getTime();
            String today = sdf.format(dt);
        //    System.out.println("The date is " + today);
            String cqlStatement = "insert into tenant_dlpscan_daily_counter " +
                     "(tenantid, trigger, date,allowed_limit,object_counter,scanned_counter ) values" +
                    "(df21cbbf-6ae4-4ed5-aa65-827f994a9a86, 'RETROSCANMETERING','" + today  +"',0,2,900)";
            session.execute(cqlStatement);

        }




        int smitty = 45;
    }

    private DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    public void SampleRead(){
        String cqlStatement = "SELECT tenantid,trigger,date FROM tenant_dlpscan_daily_counter where trigger = 'RETROSCANMETERING'ALLOW FILTERING ";
        for (Row row : session.execute(cqlStatement)) {
            UUID id = row.getUUID(0);
            String trigger = row.getString(1);
            String date = row.getString(2);
            ScanClass theScan = new ScanClass();
            theScan.setUUID(id);
            theScan.setscanType(trigger);
            theScan.setDate(date);
            theData.add(theScan);



           // System.out.println(row.toString());
        }




        try{
            Date purgeDate  = format.parse("01-01-2022");
            Collections.sort(theData, ScanClass.ScanDateComparator);
            for(ScanClass str: theData){
                Date scanDate = str.getRawDate();
                System.out.println(scanDate);

                if(scanDate.before(purgeDate)){
                    System.out.println(str);
                    cqlStatement = "delete from tenant_dlpscan_daily_counter where tenantid = " + str.getUID() + " and date = '";
                    cqlStatement = cqlStatement + str.getDate()  +"' and trigger = '" + str.getscanType() + "'";
                    System.out.println(cqlStatement);
                    session.execute(cqlStatement);
                    int k=45;
                }
            }
        }catch(Exception here){
            System.out.println(here.getMessage());
        }





        int smitty = 45;
    }

    public void Shutdown(){
        session.close();
    }

   /* public void workWithKeySpace(){
        String cqlStatement = "CREATE KEYSPACE smittykeyspace WITH " +
                "replication = {'class':'SimpleStrategy','replication_factor':1}";
        session = cluster.connect(keyspace);
        session.execute(cqlStatement);
        session.close();
        session = cluster.connect("smittykeyspace");

        cqlStatement = "CREATE TABLE users (" +
                " username varchar PRIMARY KEY," +
                " password varchar " +
                ");";

        session.execute(cqlStatement);
        session.close();

        String cqlStatementC = "INSERT INTO smittykeyspace.users (username, password) " +
                "VALUES ('Serenity', 'fa3dfQefx');";

        String cqlStatementU = "UPDATE smittykeyspace.users " +
                "SET password = 'zzaEcvAf32hla' " +
                "WHERE username = 'Serenity';";

        String cqlStatementD = "DELETE FROM smittykeyspace.users " +
                "WHERE username = 'Serenity';";
        session = cluster.connect("smittykeyspace");
        session.execute(cqlStatementC);
        session.execute(cqlStatementU);
        session.execute(cqlStatementD);
    }*/
}
