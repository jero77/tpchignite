
import java.io.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;


/**
 * Test Apache Ignite & JDBC from Java
 */
public class TPCHQueries {

    // Constants for file paths
    private static final String LOGFILEPATH = "/home/dbuser/Desktop/Results/affinityCollocated.log";
    private static final String RESULTFILEPATH = "/home/dbuser/Desktop/Results/affinityCollocated.csv";
    private static final String IGNITEQUERIESPATH = "/home/dbuser/Desktop/tpchdaten/Queries/all_ignite_queries";


    private static Logger makeLogger(String logFilePath) {

        // Init Logger, FileHandler and Formatter
        Logger LOGGER = Logger.getLogger(TPCHQueries.class.getName());
        FileHandler logFile = null;
        SimpleFormatter formatter = new SimpleFormatter();

        try {
            logFile = new FileHandler();
        } catch (SecurityException e2) {
            e2.printStackTrace();
            System.exit(-1);
        } catch (IOException e2) {
            e2.printStackTrace();
            System.exit(-1);
        } finally {
            logFile.setFormatter(formatter);
            LOGGER.addHandler(logFile);
        }
        return LOGGER;
    }


    private static boolean checkQueryNumber(String queryname) {
        // The query number list to check (except 11, 15, 18-20)
        int[] queries = {1, 2, 3, 4, 5, 6, 7, 9, 10, 12, 13, 14, 16, 17, 21, 22};

        String name = new String(queryname);

        // Extract number of the String queryname of the form "queryN.sql", 1 <= N <= 22
        // \\. --> \. because java escapes '\' with '\\'
        if (name.matches("query([1-9]|(1([0-9]))|(2[0-2]))\\.sql")) {
            int number = Integer.parseInt(name.replace(".sql", "").replace("query", ""));
            for (int num : queries) {
                if (num == number)
                    return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

        // Enable Ignite Logging
        System.setProperty(org.apache.ignite.IgniteSystemProperties.IGNITE_QUIET, "false");

        // Setup DB-connection
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver & open connection
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1;collocated=true");

            // Prepare Statement with timeout for 60 minutes
            stmt = conn.createStatement();
            stmt.setQueryTimeout(3600);


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }


        // Logging
        final Logger LOGGER = makeLogger(LOGFILEPATH);


        // Setup csv-Writer
        LOGGER.log(Level.INFO, "Setting up PrintWriter ...");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(RESULTFILEPATH));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "An Exception occured!\n" + e.getMessage());
            System.exit(-1);
        }
        LOGGER.log(Level.INFO, "Set up PrintWriter complete!");


        // Write header of csv-File
        String header = "query,time1,time2,time3,time4,time5,time6,time7,time8,tim9,time10,avg";
        pw.append(header + "\n").flush();
        LOGGER.log(Level.INFO, "Wrote header to csv-File.");


        // Process the queries
        final File folder = new File(IGNITEQUERIESPATH);
        LOGGER.log(Level.INFO, "Process the queries ...");

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                LOGGER.log(Level.INFO, "Skipping directory named " + fileEntry.getPath());
            } else {

                // Check the query number if it shall be executed or not (skip)
                LOGGER.log(Level.INFO, "Check if the query number is on the List ...");
                if (checkQueryNumber(fileEntry.getName())) {
                    LOGGER.log(Level.INFO, "Process the query '" + fileEntry.getName() + "'");
                } else {
                    LOGGER.log(Level.INFO, "Skip query '" + fileEntry.getName() + "'");
                    continue;    // skip this one
                }


                // Add query name to csv
                pw.append(fileEntry.getName() + ",").flush();


                // Read the query from the file into a string
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(fileEntry.getPath()));
                } catch (FileNotFoundException e) {
                    LOGGER.log(Level.WARNING, "A file was not found (BufferedReader)" + e.getMessage());
                }

                StringBuilder query = new StringBuilder();
                String str;
                try {
                    while ((str = in.readLine()) != null) {
                        query.append(str + "\n");
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, e.getMessage());
                }


                // Execute query 10 times in a row
                long times[] = new long[10];
                boolean timeout = false;
                LOGGER.log(Level.INFO, "Executing the query '" + fileEntry.getName() + "' 10 times ...");
                for (int i = 0; i < 10; i++) {
                    try {
                        // Measure time elapsed
                        long before = System.nanoTime();
                        /*ResultSet res =*/ stmt.executeQuery(query.toString());
                        long after = System.nanoTime();
                        times[i] = after - before;

                        
//                        // result
//                        ResultSetMetaData rsmd = res.getMetaData();
//                        int columns = rsmd.getColumnCount();
//                        while (res.next()) {
//                            for (int j = 1; j <= columns; j++) {
//                                if (j > 1) System.out.print("\t|\t");
//                                System.out.print(res.getObject(j));
//                            }
//                            System.out.println();
//                        }

                        // Append time to csv
                        long millis = TimeUnit.NANOSECONDS.toMillis(times[i]);
                        String time = String.format("%dms", millis);
                        pw.append(time + ",").flush();
                    } catch (SQLTimeoutException e) {
                        // Log the timeout and continue to next query
                        LOGGER.log(Level.WARNING, "Timeout on query execution (i=" + i + ") of query '" + fileEntry.getName() + "'!");
                        LOGGER.log(Level.INFO, "Skipping further executions of this query ...");
                        pw.append("TIMEOUT").flush();
                        timeout = true;
                        break;
                    } catch (SQLException e) {
                        LOGGER.log(Level.SEVERE, "Error upon query execution of query '" + fileEntry.getName() + "'!");
                        LOGGER.log(Level.SEVERE, e.getMessage());
                        LOGGER.log(Level.SEVERE, e.getSQLState());
                    }
                }
                LOGGER.log(Level.INFO, "Executed the query '" + fileEntry.getName() + "' 10 times!");

                // Avg. time
                if (!timeout) {
                    long sum = 0;
                    for (int i = 0; i < 10; i++) {
                        sum += times[i];
                    }
                    long avg = (long) (sum / 10.0);
                    pw.append(TimeUnit.NANOSECONDS.toMillis(avg) + "ms").flush();
                }
                pw.append("\n").flush();
            }
        }


        // Close writer & connection
        pw.close();
        LOGGER.log(Level.INFO, "Closed PrintWriter");

        try {
            conn.close();
            LOGGER.log(Level.INFO, "Closed DB-Connection");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

    }
}
