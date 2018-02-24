package Cassandra;

public class CassandraLearn {
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.
        CassandraSQL c = new CassandraSQL();
        c.Init();
    //
        //
         c.AddRetroScan();
    //    c.SampleRead();
    //    c.Shutdown();
    //    c.workWithKeySpace();
        System.out.println(" All Done");
        return;
    }
}
