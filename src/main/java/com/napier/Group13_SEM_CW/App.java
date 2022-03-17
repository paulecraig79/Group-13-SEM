package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import java.sql.*;
import java.util.ArrayList;

public class App
{
    private static Connection con = null;

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        Connections c = new Connections();

        con = c.connect();

        //ArrayList<Country> countries = a.displayCountries();

        //a.printCountries(countries);

        ArrayList<City> cities = a.getTopCitiesInContinent("Asia", 10);

        ArrayList<City> cities2 = a.getTopCitiesInRegion("Southeast Asia", 10);

        ArrayList<City> cities3 = a.getTopCitiesInCountry("United Kingdom", 10);

        ArrayList<City> cities4 = a.getTopCitiesInDistrict("England", 12);

        ArrayList<City> capitals14 = a.getCapitalsInWorld();

        ArrayList<City> capitals15 = a.getCapitalsInContinent("Asia");

        ArrayList<City> capitals16 = a.getCapitalsInRegion("Southeast Asia");

        a.printCapitals(capitals16);

        //a.printCities(cities4);

        // Disconnect from database
        c.disconnect();


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
                city1.population = rset.getInt( "city.population");
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

    /** Use case 13. Gets the top N populated cities in a district where N is provided by the user.
     *
     * @param district
     * @param limit
     * @return An array list of the N most populated cities in a district.
     */
    public ArrayList<City> getTopCitiesInDistrict(String district, int limit)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, district, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE district = '" + district + "'" +
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

    /** Use case 14. Gets all capital cities in the world by organised from largest to smallest
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> getCapitalsInWorld()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            " ORDER BY population DESC;";
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

    /** Use case 15. Gets all capital cities in a continent define by user organised from largest to smallest
     *
     * @return An array list of the capital cities in the world in order of descending population.
     **/
    public ArrayList<City> getCapitalsInContinent(String continent)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE continent = '" + continent + "'" +
                            " ORDER BY population DESC;";
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

    /** Use case 16. Gets all capital cities in a region defined by user organised from largest to smallest
     *
     * @return An array list of the capital cities in the region in order of descending population.
     **/
    public ArrayList<City> getCapitalsInRegion(String region)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE region = '" + region + "'" +
                            " ORDER BY population DESC;";
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
}
