package Cassandra;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraSQL {
    String serverIP = "127.0.0.1";
    String keyspace = "system";
    private Cluster cluster = null;
    private Session session = null;


    public boolean Init(){

        cluster = Cluster.builder()
                .addContactPoints(serverIP)
                .build();

        session = cluster.connect(keyspace);

        return true;
    }

    public void SampleRead(){
        String cqlStatement = "SELECT * FROM local";
        for (Row row : session.execute(cqlStatement)) {
            System.out.println(row.toString());
        }
        session.close();
    }

    public void workWithKeySpace(){
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
    }
}
