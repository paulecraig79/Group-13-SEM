package com.napier.Group13_SEM_CW;


import java.sql.*;
import java.util.ArrayList;


public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        //print a list of cities in a continent
        ArrayList<City> cities = a.getCitiesInRegion("Southern Europe");

        //Print cities
        a.printCities(cities);

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

    /**
     * Get city method
     */
    public City getCities(int ID){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT name, countrycode, district, population " +
                            "FROM city " +
                            "WHERE name = " + ID;

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            //Return new city if valid
            //Check one is returned
            if(resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
                city.countrycode = resultSet.getString("countrycode");
                city.district = resultSet.getString("district");
                city.population = resultSet.getInt("population");
                return city;
            } else
                return null;
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Display city
     */
    public void displayCity(City city){
        if(city != null){
            System.out.println(
                    city.name + " "
                            + city.countrycode + " "
                            + city.district + " "
                            + city.population
            );
        }
    }

    /**
     * All the cities in a REGION ORGANISED BY LARGEST POPULATION TO SMALLEST.
     *
     *@return A list of all cities in a region, or null if
     *there is an error
     */
    public ArrayList<City> getCitiesInRegion(String region){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population "
                    + "FROM city JOIN country ON (code = city.countrycode) "
                    + "WHERE region = '" + region + "' "
                    + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
                city.countrycode = resultSet.getString("countrycode");
                city.population = resultSet.getInt("population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Prints the contents specified of a list of countries.
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
