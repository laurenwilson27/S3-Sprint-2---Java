// package com.keyin.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper class containing a function to get a postgres Connection based on authentication credentials in this class.
 */

public class DatabaseConnection {
    // Note that this connection does not use the default postgres user, the appropriate database and user must exist in the target Postgres server
    private static final String url = "jdbc:postgresql://localhost:5432/java-sprint";
    private static final String user = "java-user";
    private static final String password = "ZQDShWM3";

    /**
     *
     * @return A postgres Connection instance
     */
    public static Connection getCon(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");     // For Postgres
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException  e) {
            e.printStackTrace();
        }
        return connection;
    }


}
