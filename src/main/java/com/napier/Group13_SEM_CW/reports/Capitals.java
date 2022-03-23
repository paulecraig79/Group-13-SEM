package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.City;

import java.sql.*;
import java.util.ArrayList;

public class Capitals {


    /**
     * Use case 14. Gets all capital cities in the world by organised from largest to smallest
     *
     * @return A result set containing the required information from the world database as prompted by the
     * SQL statements included.
     **/
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

    /** Use case 15. Gets all capital cities in a continent define by user organised from largest to smallest.
     *
     * @return A result set containing the required information from the world database as prompted by the
     *      * SQL statements included (all capital cities in a specified continent by descending population order).
     **/
    public ResultSet getCapitalsInContinent(String continent, Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE continent = '" + continent + "'" +
                            " ORDER BY population DESC;";
            // Execute SQL statement
            //return result set if valid
            return stmt.executeQuery(strSelect);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }

    /** Use case 16. Gets all capital cities in a region defined by user organised from largest to smallest
     *
     * @return A result set containing the required information from the world database as prompted by the
     * SQL statements included (all capital cities in a specified region by descending population order).
     **/
    public ResultSet getCapitalsInRegion(String region, Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE region = '" + region + "'" +
                            " ORDER BY population DESC;";
            // Execute SQL statement
            //return result set if valid
            return(stmt.executeQuery(strSelect));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }

    /** Use case 17. The top N populated capital cities in the world where N is provided by the user.
     *
     * @return An array list of the top capital cities in the world.
     **/
    public ArrayList<City> topPopulatedCapitalsInWorld(Integer number, Connection con)
    {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            " ORDER BY population DESC LIMIT '" + number + "'" + ";";
            // Execute SQL statement
            // Return city if valid.
            return (ArrayList<City>) stmt.executeQuery(strSelect);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }

    /** Use case 18. The top N populated capital cities in a continent where N is provided by the user.
     *
     * @return An array list of the top capital cities in the continent.
     **/
    public ArrayList<City> topPopulatedCapitalsInContinent(Integer number, Connection con)
    {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE continent = 'Africa' " +
                            " ORDER BY population DESC LIMIT '" + number + "'" + ";";
            // Execute SQL statement
            // Return city if valid.
            return (ArrayList<City>) stmt.executeQuery(strSelect);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }

    /** Use case 19. The top N populated capital cities in a region where N is provided by the user.
     *
     * @return An array list of the top capital cities in the region.
     **/
    public ArrayList<City> topPopulatedCapitalsInRegion(Integer number, Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE region = 'British Islands'" +
                            " ORDER BY population DESC LIMIT '" + number + "'" + ";";
            // Execute SQL statement
            // Return city if valid.
            return (ArrayList<City>) stmt.executeQuery(strSelect);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }



    /** Takes the resultant SQL statements and inputs them into an ArrayList.
     *
     * @param rset A set of results provided by an SQL query.
     * @return An Array List of capitals containing the required information columns.
     */
    public ArrayList<City> getCapitalsArrayList(ResultSet rset) throws SQLException {
        ArrayList<City> capitals = new ArrayList<>();
        while (rset.next()) {
            City capital1 = new City();
            capital1.name = rset.getString("city.name");
            capital1.countrycode = rset.getString("country");
            capital1.population = rset.getInt("city.population");
            capitals.add(capital1);
        }
        return capitals;
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
        System.out.println(String.format("%-30s %-14s %-20s", "name", "country", "population"));
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
