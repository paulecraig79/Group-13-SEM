/*
package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import com.napier.Group13_SEM_CW.reports.Cities;
import com.napier.Group13_SEM_CW.reports.Countries;


import java.sql.*;
import java.util.ArrayList;


*/
/**
 * App is used to connect to sql database and is used for sql queries
 *//*

public class App
{
    private static Connection con = null;

    public static void main(String[] args) */
/*throws SQLException *//*
{


        // Create new Application
        App a = new App();

        // Connect to database
        Connections c = new Connections();

        con = c.oldConnect();

        */
/*if(args.length < 1){
            c.newConnect("localhost:33060", 30000);
        }else{
            c.newConnect(args[0], Integer.parseInt(args[1]));
        }*//*


        Cities cities = new Cities();
        Capitals capitals = new Capitals();
        Countries countries = new Countries();

        capitals.getCapitalsInWorld(con);
        */
/*ArrayList<City> world = b.topPopulatedCapitalsInWorld(Integer.valueOf("5"), con);

        ArrayList<City> continent = b.topPopulatedCapitalsInContinent(Integer.valueOf("5"), con);

        ArrayList<City> region = b.topPopulatedCapitalsInRegion(Integer.valueOf("5"), con);

        a.printCities(world);
*//*



        // Disconnect from database
        c.disconnect();

    }

    public ResultSet getCapitalsInWorld(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            " ORDER BY population DESC;";
            // Execute SQL statement
            //return result set if valid
            return stmt.executeQuery(strSelect);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }
}
*/
package com.napier.Group13_SEM_CW;


import java.sql.*;
import java.util.ArrayList;
//import java.util.Scanner;


public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        ArrayList<City> world = a.topPopulatedCapitalsInWorld();

        ArrayList<City> continent = a.topPopulatedCapitalsInContinent();

        ArrayList<City> region = a.topPopulatedCapitalsInRegion();

        a.printCities(world);


        // Disconnect from database
        a.disconnect();


    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
                System.out.println("disconnected");
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }


    /** Use case 17. The top N populated capital cities in the world where N is provided by the user.
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> topPopulatedCapitalsInWorld()
    {

        try {
            /*Scanner input = new Scanner(System.in);
            System.out.print("Enter an integer: ");
            int number = input.nextInt();*/

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            " ORDER BY population DESC LIMIT 5;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> capitals = new ArrayList<>();
            while (rset.next()) {
                City capital1 = new City();
                capital1.name = rset.getString("city.name");
                capital1.countrycode = rset.getString("city.countrycode");
                capital1.population = rset.getInt("city.population");
                capitals.add(capital1);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }

    /** Use case 18. The top N populated capital cities in a continent where N is provided by the user.
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> topPopulatedCapitalsInContinent()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE continent = 'Africa'" +
                            " ORDER BY population DESC LIMIT 10;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> capitals = new ArrayList<>();
            while (rset.next()) {
                City capital1 = new City();
                capital1.name = rset.getString("city.name");
                capital1.countrycode = rset.getString("city.countrycode");
                capital1.population = rset.getInt("city.population");
                capitals.add(capital1);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }


    /** Use case 19. The top N populated capital cities in a region where N is provided by the user.
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> topPopulatedCapitalsInRegion()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE region = 'British Islands'" +
                            " ORDER BY population DESC LIMIT 10;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> capitals = new ArrayList<>();
            while (rset.next()) {
                City capital1 = new City();
                capital1.name = rset.getString("city.name");
                capital1.countrycode = rset.getString("city.countrycode");
                capital1.population = rset.getInt("city.population");
                capitals.add(capital1);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }

    /**
     * Prints the contents specified of a list of capital cities.
     *
     * @param capitals
     */
    public void printCapitals(ArrayList<City> capitals)
    {
        // Check cities is not null
        if (capitals == null)
        {
            System.out.println("No cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-14s %-20s", "name", "country code", "population"));
        // Loop over all countries in the list
        for (City capital : capitals)
        {
            if (capital == null)
                continue;
            String emp_string =
                    String.format("%-30s %-14s %-20s",
                            capital.name, capital.countrycode, capital.population);
            System.out.println(emp_string);
        }
    }

    /**
     * Prints the contents specified of a list of countries.
     *
     * test
     *
     * @param cities
     */
    public void printCities(ArrayList<City> cities)
    {
        // Check cities is not null
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-14s %-20s %-8s", "name", "country code", "district", "population"));
        // Loop over all countries in the list
        for (City city : cities)
        {
            if (city == null)
                continue;
            String emp_string =
                    String.format("%-30s %-14s %-20s %-8s",
                            city.name, city.countrycode, city.district, city.population);
            System.out.println(emp_string);
        }
    }

}