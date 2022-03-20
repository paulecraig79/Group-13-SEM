package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import com.napier.Group13_SEM_CW.reports.Cities;
import java.sql.*;
import java.util.ArrayList;

public class App
{
    private static Connection con = null;

    public static void main(String[] args) throws SQLException {
        // Create new Application
        App a = new App();

        // Connect to database
        Connections c = new Connections();

        con = c.connect();

        // Disconnect from database
        c.disconnect();


    }
}
