package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import com.napier.Group13_SEM_CW.reports.Cities;
import com.napier.Group13_SEM_CW.reports.Countries;

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

        Countries country = new Countries();
        ResultSet z = country.getCountriesInWorld(con);
        ArrayList<Country> test = country.getCountriesArrayList(z, con);
        country.printCountries(test);

        // Disconnect from database
        c.disconnect();


    }
}
