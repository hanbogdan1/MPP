package Utils;

/**
 * Created by Bogdan on 03-Apr-17.
 */

import java.sql.Connection;
import java.sql.DriverManager;


public class Conn {
//    private static String database ;
//    private static Connection connection;
//    private static volatile Conn instance2 = null;
//    public static Connection getConnection() {
//        return connection;
//    }
//
//
//    private Conn() {
//        database = "jdbc:sqlite:C:/Users/Bogdan/Documents/Visual Studio 2015/Projects/P3-Mpp-Lab1/P3-Mpp-Lab1/bin/Debug/Lab1Mpp.db";
//    }
//
//    public static void openConnection() {
//
//        // set the database connection
//        try {
//            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection(database);
//            System.out.println("Opened Connection " + connection.hashCode());
//        } catch (  Exception  e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    public static void closeConnection() {
//        try {
//            // close the connection
//            if (connection == null || connection.isClosed()) {
//                return;
//            }
//        } catch (  Exception  e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            connection.close();
//            System.out.println("Connection " + connection.hashCode()
//                    + " closed");
//        } catch (  Exception  e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    public static Conn getInstance() {
//        if (instance2 == null) {
//            synchronized (Conn.class) {
//                if (instance2 == null) {
//                    instance2 = new Conn();
//                }
//            }
//        }
//        return instance2;
//    }
//
//    public static void setInstance(Conn instance) {
//        Conn.instance2 = instance;
//    }


    private static Connection c = null;

    public static Connection getConn() throws Exception {
        if (c == null) {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:/Users/Bogdan/Documents/Visual Studio 2015/Projects/P3-Mpp-Lab1/P3-Mpp-Lab1/bin/Debug/Lab1Mpp.db");
            ;
        }
        return c;
    }
}

