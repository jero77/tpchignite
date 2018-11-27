import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import javax.cache.Cache.Entry;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TesterQueryCache {

    public static void main(String[] args) {

//        Connection conn = null;
//        Statement stmt = null;
//        System.out.println("Initializing connection to the cluster ...");
//        try {
//            // Initialize connection and statement
//            conn = getConnection();
//            System.out.println("Connected to the cluster!");
//            stmt = conn.createStatement();
//
//            // Query
//            ResultSet result = stmt.executeQuery("Select * from PART");
//            while (result.next()) {
//                System.out.println(result.getInt("P_PARTKEY")+"|"+result.getString("P_NAME")+"|"
//                        +result.getString("P_BRAND")+"|"+result.getBigDecimal("P_RETAILPRICE"));
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }

        IgniteCreateCaches icc = new IgniteCreateCaches();

        // Client configuration
        final String ADDRESSES = "127.0.0.1:47500..47509";
        ClientConfiguration cliConfig = new ClientConfiguration().setAddresses(ADDRESSES);

        // Discovery
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47509"));
        spi.setIpFinder(ipFinder);

        // Ignite Configuration
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setDiscoverySpi(spi);


        // Start client that connects to the cluster
        Ignite ignite = null;
        try {
            ignite = Ignition.start(cfg);
            System.out.println("Client started!");

            // Add cache configurations
            ArrayList<CacheConfiguration> configs = icc.setupCacheConfigurations(ignite);
            System.out.println("Added cache configurations!");


            CacheConfiguration<Integer, PART> partConfig = (CacheConfiguration<Integer, PART>) configs.get(4);
            IgniteCache<Integer, PART> partCache = ignite.getOrCreateCache(partConfig);
            System.out.println(partCache.getName());

//            SqlQuery sql = new SqlQuery(PART.class, "");
//            QueryCursor<Entry<Integer, PART>> cursor = partCache.query(sql);
//            for (Entry<Integer, PART> e : cursor) {
//                System.out.println(e.getValue().toString());
//            }

            partCache.put(14, new PART(14, "name", "mfgr", "brand",
                    "type", 100, "container", new BigDecimal(1234567.45),
                    "comment"));

            QueryCursor<List<?>> cursor = partCache.query(new SqlFieldsQuery("SELECT * FROM PART, CUSTOMER"));
            for (List<?> row : cursor) {
                for (int i = 0; i < row.size(); i++) {
                    System.out.print(row.get(i) + "|");
                }
                System.out.println();
            }

        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ignite.close();
        }



    }



    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Register driver
        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

        // Return connection to the cluster (Port 10800 default for JDBC client)
        return DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1:10800;distributedJoins=true;" +
                "collocated=true;streaming=false");
    }

}

