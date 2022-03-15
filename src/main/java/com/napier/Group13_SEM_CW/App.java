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

        //ArrayList<Country> countries = a.displayCountries();

        //a.printCountries(countries);

        ArrayList<City> cities = a.getTopCitiesInContinent("Asia", 10);

        ArrayList<City> cities2 = a.getTopCitiesInRegion("Southeast Asia", 10);

        ArrayList<City> cities3 = a.getTopCitiesInCountry("United Kingdom", 10);

        a.printCities(cities3);


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
     *  Input various contents of the country table into an array that can be printed later.
     *
     * @return A list of countries with the contents specified.
     */
    public ArrayList<Country> displayCountries()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                              "SElECT * "
                            + "FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                Country country1 = new Country();
                country1.name = rset.getString("country.Name");
                country1.capital = rset.getInt("country.Capital");
                country1.code = rset.getString("country.Code");
                country1.continent = rset.getString("country.Continent");
                country1.life_expectancy = rset.getInt("country.LifeExpectancy");
                countries.add(country1);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get country details");
            return null;
        }
    }

    /** Use case 10. Gets the top N populated cities in a continent where N is provided by the user.
     *
     * @param continent
     * @param limit
     * @return An array list of the N most populated cities in a continent.
     */
    public ArrayList<City> getTopCitiesInContinent(String continent, int limit)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                "SELECT city.name, city.countrycode, district, city.population " +
                "FROM city JOIN country ON (code = city.countrycode) " +
                "WHERE continent = '" + continent + "'" +
                " ORDER BY population DESC " +
                " LIMIT " + limit + ";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("city.countrycode");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get city details");
            return null;
        }
    }

    /** Use case 11. Gets the top N populated cities in a region where N is provided by the user.
     *
     * @param region
     * @param limit
     * @return An array list of the N most populated cities in a region.
     */
    public ArrayList<City> getTopCitiesInRegion(String region, int limit)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, district, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE region = '" + region + "'" +
                            " ORDER BY population DESC " +
                            " LIMIT " + limit + ";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("city.countrycode");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get city details");
            return null;
        }
    }

    /** Use case 12. Gets the top N populated cities in a country where N is provided by the user.
     *
     * @param country
     * @param limit
     * @return An array list of the N most populated cities in a country.
     */
    public ArrayList<City> getTopCitiesInCountry(String country, int limit)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, district, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE country.name = '" + country + "'" +
                            " ORDER BY population DESC " +
                            " LIMIT " + limit + ";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("city.countrycode");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get city details");
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
        System.out.println(String.format("%-20s %-3s %-20s %-8s", "name", "country code", "district", "population"));
        // Loop over all countries in the list
        for (City city : cities)
        {
            if (city == null)
                continue;
            String emp_string =
                    String.format("%-20s %-13s %-20s %-8s",
                            city.name, city.countrycode, city.district, city.population);
            System.out.println(emp_string);
        }
    }

    /**
     * Prints the contents specified of a list of countries.
     *
     * @param countries
     */
    public void printCountries(ArrayList<Country> countries)
    {
        // Check countries is not null
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s %-20s", "capital", "code", "continent", "life expectancy", "name"));
        // Loop over all countries in the list
        for (Country country : countries)
        {
            if (country == null)
                continue;
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            country.capital, country.code, country.continent, country.life_expectancy, country.name);
            System.out.println(emp_string);
        }
    }
}
