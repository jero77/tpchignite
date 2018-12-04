import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class IgniteCreateCaches {


    public ArrayList<CacheConfiguration> setupCacheConfigurations(Ignite ignite) {
        // Cache configurations
        ArrayList<CacheConfiguration> configs = new ArrayList<CacheConfiguration>();

        // CUSTOMER Cache
        CacheConfiguration<?, ?> cacheConfig = new CacheConfiguration("CUSTOMER");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                //.setTypes(Integer.class, CUSTOMER.class)
                .setIndexedTypes(Integer.class, CUSTOMER.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // LINEITEM Cache
        cacheConfig = new CacheConfiguration("LINEITEM");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                //.setTypes(LINEITEM_KEY.class, LINEITEM.class)
                .setIndexedTypes(LINEITEM_KEY.class, LINEITEM.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // NATION Cache (replicated to each node due to smallness)
        cacheConfig = new CacheConfiguration("NATION");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.REPLICATED)
                //.setTypes(Integer.class, NATION.class)
                .setIndexedTypes(Integer.class, NATION.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // ORDERS Cache
        cacheConfig = new CacheConfiguration("ORDERS");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                //.setTypes(Integer.class, ORDERS.class)
                .setIndexedTypes(Integer.class, ORDERS.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // PART Cache
        cacheConfig = new CacheConfiguration("PART");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                //.setTypes(Integer.class, PART.class)
                .setIndexedTypes(Integer.class, PART.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // PARTSUPP Cache
        cacheConfig = new CacheConfiguration("PARTSUPP");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                //.setTypes(PARTSUPP_KEY.class, PARTSUPP.class)
                .setIndexedTypes(PARTSUPP_KEY.class, PARTSUPP.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // REGION Cache (replicated to each node due to smallness)
        cacheConfig = new CacheConfiguration("REGION");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.REPLICATED)
                //.setTypes(Integer.class, REGION.class)
                .setIndexedTypes(Integer.class, REGION.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // SUPPLIER Cache
        cacheConfig = new CacheConfiguration("SUPPLIER");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                //.setTypes(Integer.class, SUPPLIER.class)
                .setIndexedTypes(Integer.class, SUPPLIER.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);

        return configs;
    }


    private void populateTables(HashMap<String, IgniteCache> cacheMap) {

        BufferedReader reader = null;
        String pathPrefix = "/home/dbuser/Desktop/tpchdaten/TblFiles/";
        try {

            int counter = 0;
            String line;

            // LINEITEM
            reader = new BufferedReader(new FileReader(pathPrefix + "lineitem.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("LINEITEM");
                String[] values = line.split("\\|");
                LINEITEM_KEY key = new LINEITEM_KEY(Integer.parseInt(values[0]), Integer.parseInt(values[3]));
                cache.put(key, new LINEITEM(key, Integer.parseInt(values[1]), Integer.parseInt(values[2]),
                        Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6]),
                        Double.parseDouble(values[7]), values[8], values[9], Timestamp.valueOf(values[10]+ " 00:00:00"),
                        Timestamp.valueOf(values[11] + " 00:00:00"), Timestamp.valueOf(values[12] + " 00:00:00"),
                        values[13], values[14], values[15]));
                counter++;
            }
            System.out.println("Populated LINEITEM-Cache! Put " + counter + " entries!");
            counter = 0;


            // ORDERS (collocated to LINEITEM)
            reader = new BufferedReader(new FileReader(pathPrefix + "orders.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("ORDERS");
                String[] values = line.split("\\|");
                Integer key = Integer.parseInt(values[0]);
                cache.put(key ,new ORDERS(key, Integer.parseInt(values[1]), values[2], Double.parseDouble(values[3]),
                        Timestamp.valueOf(values[4] + " 00:00:00"), values[5], values[6],
                        Integer.parseInt(values[7]), values[8]));
                counter++;
            }
            System.out.println("Populated ORDERS-Cache! Put " + counter + " entries!");
            counter = 0;


            // CUSTOMER (collocated to ORDERS)
            reader = new BufferedReader(new FileReader(pathPrefix + "customer.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("CUSTOMER");
                String[] values = line.split("\\|");
                Integer key = Integer.parseInt(values[0]);
                cache.put(key, new CUSTOMER(key, values[1], values[2], Integer.parseInt(values[3]),
                        values[4], Double.parseDouble(values[5]), values[6], values[7]));
                counter++;
            }
            System.out.println("Populated CUSTOMER-Cache! Put " + counter + " entries!");
            counter = 0;


            // PARTSUPP (collocated to LINEITEM)
            reader = new BufferedReader(new FileReader(pathPrefix + "partsupp.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("PARTSUPP");
                String[] values = line.split("\\|");
                PARTSUPP_KEY key = new PARTSUPP_KEY(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                cache.put(key, new PARTSUPP(key, Integer.parseInt(values[2]), Double.parseDouble(values[3]),
                        values[4]));
                counter++;
            }
            System.out.println("Populated PARTSUPP-Cache! Put " + counter + " entries!");
            counter = 0;


            // PART (collocated to PARTSUPP)
            reader = new BufferedReader(new FileReader(pathPrefix + "part.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("PART");
                String[] values = line.split("\\|");
                Integer key = Integer.parseInt(values[0]);
                cache.put(key, new PART(key, values[1], values[2], values[3], values[4],
                        Integer.parseInt(values[5]), values[6], Double.parseDouble(values[7]), values[8]));
                counter++;
            }
            System.out.println("Populated PART-Cache! Put " + counter + " entries!");
            counter = 0;


            // SUPPLIER (collocated to PARTSUPP)
            reader = new BufferedReader(new FileReader(pathPrefix + "supplier.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("SUPPLIER");
                String[] values = line.split("\\|");
                Integer key = Integer.parseInt(values[0]);
                cache.put(key, new SUPPLIER(key, values[1], values[2], Integer.parseInt(values[3]),
                        values[4], Double.parseDouble(values[5]), values[6]));
                counter++;
            }
            System.out.println("Populated SUPPLIER-Cache! Put " + counter + " entries!");
            counter = 0;



            // REGION
            reader = new BufferedReader(new FileReader(pathPrefix + "region.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("REGION");
                String[] values = line.split("\\|");
                Integer key = Integer.parseInt(values[0]);
                cache.put(key, new REGION(key, values[1], values[2]));
                counter++;
            }
            System.out.println("Populated REGION-Cache! Put " + counter + " entries!");
            counter = 0;


            // NATION (collocated to REGION)
            reader = new BufferedReader(new FileReader(pathPrefix + "nation.tbl"));
            while ((line = reader.readLine()) != null) {
                IgniteCache cache = cacheMap.get("NATION");
                String[] values = line.split("\\|");
                Integer key = Integer.parseInt(values[0]);
                cache.put(key, new NATION(key, values[1], Integer.parseInt(values[2]),
                        values[3]));
                counter++;
            }
            System.out.println("Populated NATION-Cache! Put " + counter + " entries!");
            System.out.println("Finished cache population!");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) {

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
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setDiscoverySpi(spi);


        // Start client that connects to the cluster
        Ignite ignite = null;
        try {
            ignite = Ignition.start(cfg);
            System.out.println("Client started!");

            // Add cache configurations
            ArrayList<CacheConfiguration> configs = icc.setupCacheConfigurations(ignite);
            System.out.println("Added cache configurations!");

            // Store all caches into a HashMap indexed by the cache names
            HashMap<String, IgniteCache> cacheMap = new HashMap<String, IgniteCache>(configs.size());
            for (CacheConfiguration<?, ?> cacheConfiguration : configs) {
                IgniteCache<?, ?> cache = ignite.getOrCreateCache(cacheConfiguration);
                cacheMap.put(cache.getName(), cache);
                System.out.format("Created/Got cache [%s]!\n", cache.getName());
                System.out.println(cache.metrics());
            }


            // Populate tables
            icc.populateTables(cacheMap);

        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ignite.close();
        }
    }

}
