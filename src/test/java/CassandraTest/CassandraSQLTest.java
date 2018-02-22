package CassandraTest;

import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.*;

import static org.junit.Assert.*;

public class CassandraSQLTest {
    @ClassRule
    public static CassandraCQLUnit cassandraCQLUnit = null;
    @BeforeClass
    public static void onlyOnce() {
        cassandraCQLUnit = new CassandraCQLUnit(new ClassPathCQLDataSet("setup.cql", true));

    }

    @Before
    public void setUpCassandra(){

    }
    @Test
    public void init() {
    }

    @Test
    public void addRetroScan() {
    }

    @Test
    public void sampleRead() {
    }

    @Test
    public void shutdown() {
    }
}