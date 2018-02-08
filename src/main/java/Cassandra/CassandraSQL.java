package Cassandra;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.UUID;

public class CassandraSQL {
    String serverIP = "127.0.0.1";
    String keyspace = "casb_data_security_core_keyspace";
    private Cluster cluster = null;
    private Session session = null;


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
        String cqlStatement = "insert into tenant_dlpscan_daily_counter (tenantid, trigger, date,allowed_limit,object_counter,scanned_counter ) values (df21cbbf-6ae4-4ed5-aa65-827f994a9a86, 'carol','02-05-2017',0,2,900)";

        session.execute(cqlStatement);
        int smitty = 45;
    }
    public void SampleRead(){
        String cqlStatement = "SELECT * FROM tenant_dlpscan_daily_counter where trigger = 'RETROSCANMETERING'ALLOW FILTERING ";
        for (Row row : session.execute(cqlStatement)) {
            System.out.println(row.toString());
        }
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
