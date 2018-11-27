import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.affinity.Affinity;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.*;

public class IgniteCreateCaches {


    public ArrayList<CacheConfiguration> setupCacheConfigurations(Ignite ignite) {
        // Cache configurations
        ArrayList<CacheConfiguration> configs = new ArrayList<CacheConfiguration>();

        // CUSTOMER Cache
        CacheConfiguration cacheConfig = new CacheConfiguration("CUSTOMER");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                .setTypes(Integer.class, CUSTOMER.class)
                .setIndexedTypes(Integer.class, CUSTOMER.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // LINEITEM Cache
        cacheConfig = new CacheConfiguration("LINEITEM");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                .setTypes(LINEITEM_KEY.class, LINEITEM.class)
                .setIndexedTypes(LINEITEM_KEY.class, LINEITEM.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // NATION Cache (replicated to each node due to smallness)
        cacheConfig = new CacheConfiguration("NATION");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.REPLICATED)
                .setTypes(Integer.class, NATION.class)
                .setIndexedTypes(Integer.class, NATION.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // ORDERS Cache
        cacheConfig = new CacheConfiguration("ORDERS");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                .setTypes(Integer.class, ORDERS.class)
                .setIndexedTypes(Integer.class, ORDERS.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // PART Cache
        cacheConfig = new CacheConfiguration("PART");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                .setTypes(Integer.class, PART.class)
                .setIndexedTypes(Integer.class, PART.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // PARTSUPP Cache
        cacheConfig = new CacheConfiguration("PARTSUPP");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                .setTypes(PARTSUPP_KEY.class, PARTSUPP.class)
                .setIndexedTypes(PARTSUPP_KEY.class, PARTSUPP.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // REGION Cache (replicated to each node due to smallness)
        cacheConfig = new CacheConfiguration("REGION");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.REPLICATED)
                .setTypes(Integer.class, REGION.class)
                .setIndexedTypes(Integer.class, REGION.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);


        // SUPPLIER Cache
        cacheConfig = new CacheConfiguration("SUPPLIER");
        cacheConfig.setBackups(0)
                .setCacheMode(CacheMode.PARTITIONED)
                .setTypes(Integer.class, SUPPLIER.class)
                .setIndexedTypes(Integer.class, SUPPLIER.class)
                .setSqlSchema("PUBLIC");
        configs.add(cacheConfig);

        return configs;
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

        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ignite.close();
        }
    }

}
