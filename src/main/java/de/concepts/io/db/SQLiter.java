/* Copyright (C) Marc Müller - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marc Müller <marc@mgm.technology>, 2020
 */
package de.concepts.io.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiter {


    private static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
    private static final String QUERY_CREATE_TABLE_WATCHDOG =
            "CREATE TABLE watchdog (id integer primary key AUTOINCREMENT, "
            + "timestamp varchar(255), comment varchar(255))";
    private static final String QUERY_CREATE_TABLE_FTP =
            "CREATE TABLE ftp (id integer primary key AUTOINCREMENT, "
                    + "timestamp varchar(255), comment varchar(255))";
    private static final String WATCHDOG_SQLITE_DB = "watchdog-sqlite.db";

    public static void main(String[] args) {
        Connection c = null;

        // test or create database
        c = connectOrCreate();
        boolean result = logStartup("Startup");
        if(c!=null){
            try {
                c.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }

    /**
     * get a connection
     * @return
     */
    public static Connection getDatabaseConnection() {
        Connection c = null;
        try {
            Class.forName(ORG_SQLITE_JDBC);
            c = DriverManager.getConnection("jdbc:sqlite:" + WATCHDOG_SQLITE_DB);
            return c;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    private static Connection connectOrCreate() {
        Connection c = getDatabaseConnection();
        try{
            boolean exist = true;
            if(!new File(WATCHDOG_SQLITE_DB).exists()){
                exist = false;
            }

            //create table
            if(!exist){
                String tableQuery= QUERY_CREATE_TABLE_WATCHDOG;
                Statement stmt = c.createStatement();
                stmt.executeUpdate(tableQuery);
                tableQuery= QUERY_CREATE_TABLE_FTP;
                stmt = c.createStatement();
                stmt.executeUpdate(tableQuery);
                stmt.close();
            }
            System.out.println("Connection accepted");
        }catch(Exception e){
            System.out.println("Can not connect to the db" + e);
            System.exit(0);
        }
        return c;
    }

    /**
     * log application startup
     * @param comment
     * @return
     */
    public static boolean logStartup(String comment) {
        try {
            Connection c = getDatabaseConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            Statement stmt = null;
            stmt = c.createStatement();
            String timeStamp = new SimpleDateFormat("YYYY-MM-dd-hh-mm-ss").format(new Date());

            String sql = "INSERT INTO watchdog (timestamp, comment) VALUES ('" + timeStamp + "', '" + comment + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            return true;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return false;
        }
    }
    public static boolean logFtpAccess( String comment) {
        try {
            Connection c = getDatabaseConnection();
            c.setAutoCommit(false);
            Statement stmt = null;
            stmt = c.createStatement();
            String timeStamp = new SimpleDateFormat("YYYY-MM-dd-hh-mm-ss").format(new Date());

            String sql = "INSERT INTO ftp (timestamp, comment) VALUES ('" + timeStamp + "', '" + comment + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            return true;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return false;
        }
    }
}