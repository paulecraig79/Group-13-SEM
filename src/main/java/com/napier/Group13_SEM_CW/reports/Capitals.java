package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.City;

import java.sql.*;
import java.util.ArrayList;

public class Capitals {


    /** Use case 14. Gets all capital cities in the world by organised from largest to smallest
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> getCapitalsInWorld(Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.population " +
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
                capital1.countrycode = rset.getString("country");
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
    public ArrayList<City> getCapitalsInContinent(String continent, Connection con)
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
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> capitals = new ArrayList<>();
            while (rset.next()) {
                City capital1 = new City();
                capital1.name = rset.getString("city.name");
                capital1.countrycode = rset.getString("country");
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
    public ArrayList<City> getCapitalsInRegion(String region, Connection con) {
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
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> capitals = new ArrayList<>();
            while (rset.next()) {
                City capital1 = new City();
                capital1.name = rset.getString("city.name");
                capital1.countrycode = rset.getString("country");
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