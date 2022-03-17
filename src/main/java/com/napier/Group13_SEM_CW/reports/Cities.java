package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.City;
import java.sql.*;
import java.util.ArrayList;

public class Cities {

    /** Use case 10. Gets the top N populated cities in a continent where N is provided by the user.
     *
     * @param continent
     * @param limit
     * @return An array list of the N most populated cities in a continent.
     */
    public ArrayList<City> getTopCitiesInContinent(String continent, int limit, Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
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
                city1.countrycode = rset.getString("country");
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
    public ArrayList<City> getTopCitiesInRegion(String region, int limit, Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
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
                city1.countrycode = rset.getString("country");
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
    public ArrayList<City> getTopCitiesInCountry(String country, int limit, Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
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
                city1.countrycode = rset.getString("country");
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
    public ArrayList<City> getTopCitiesInDistrict(String district, int limit, Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
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
                city1.countrycode = rset.getString("country");
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
        System.out.println(String.format("%-30s %-14s %-20s %-8s", "name", "country", "district", "population"));
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
