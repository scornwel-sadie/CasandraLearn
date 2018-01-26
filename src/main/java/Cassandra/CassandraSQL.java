package Cassandra;


import com.datastax.driver.core.Cluster;
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
}
