package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;


import java.sql.*;



/**
 * App is used to connect to sql database and is used for sql queries
 */
public class App
{
    private static Connection con = null;

    public static void main(String[] args) throws SQLException {

        // Connect to database
        Connections c = new Connections();

        if(args.length < 1){
            c.newConnect("localhost:33060", 30000);
        }else{
            c.newConnect(args[0], Integer.parseInt(args[1]));
        }

        // Disconnect from database
        c.disconnect();

    }
}
