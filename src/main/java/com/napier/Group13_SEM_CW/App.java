package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import com.napier.Group13_SEM_CW.reports.Cities;
import com.napier.Group13_SEM_CW.reports.Countries;

import java.sql.*;
import java.util.ArrayList;


/**
 * App is used to connect to sql database and is used for sql queries
 */
public class App
{
    private static Connection con = null;

    public static void main(String[] args) throws SQLException {

        // Connect to database
        Connections c = new Connections();

        con = c.connect();

        // Disconnect from database
        c.disconnect();

    }
}
