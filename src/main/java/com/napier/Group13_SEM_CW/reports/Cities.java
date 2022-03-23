package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.City;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class Cities {

    /**
     * This method is used to get
     * All the cities in the WORLD ORGANISED BY LARGEST POPULATION TO SMALLEST
     *
     * @return a list of cities, or null if there is an error
     */
    public ArrayList<City> getAllCities(Connection con){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT name, population "
                            + "FROM city "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
                city.population = resultSet.getInt("population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * All the cities in a CONTINENT ORGANISED BY LARGEST POPULATION TO SMALLEST.
     *
     * @return A list of all cities in a continent, or null if
     * there is an error
     */
    public ArrayList<City> getCitiesInContinent(String continent, Connection con){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, city.countrycode, city.population "
                            + "FROM city JOIN country ON (code = city.countrycode) "
                            + "WHERE continent = '" + continent + "' "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
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
     * All the cities in a REGION ORGANISED BY LARGEST POPULATION TO SMALLEST.
     *
     *@return A list of all cities in a region, or null if
     *there is an error
     */
    public ArrayList<City> getCitiesInRegion(String region, Connection con){
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
     * All the cities in a COUNTRY ORGANISED BY LARGEST POPULATION TO SMALLEST.
     *
     * @return A list of all cities in a country, or null if
     * there is an error
     */
    public ArrayList<City> getCitiesInCountry(String country, Connection con){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS countrycode, city.district, city.population "
                            + "FROM city JOIN country ON (code = city.countrycode) "
                            + "WHERE country.name = '" + country + "' "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
                city.countrycode = resultSet.getString("countrycode");
                city.district = resultSet.getString("district");
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
     * All the cities in a DISTRICT ORGANISED BY LARGEST POPULATION TO SMALLEST.
     *
     * @return A list of all cities in a district, or null if
     * there is an error
     */
    public ArrayList<City> getCitiesInDistrict(Connection con) {
        try {
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT name, district, population "
                            + "FROM city "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()) {
                City city = new City();
                city.name = resultSet.getString("name");
                city.district = resultSet.getString("district");
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
     * The top N populated cities in the world where N is provided by the user.
     *
     * In this case N is 10.
     */
    public ArrayList<City> TopPopulatedCities(Connection con){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT name, countrycode, district, population "
                            + "FROM city "
                            + "ORDER BY population DESC "
                            + "LIMIT 10 ";
            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();

            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
                city.district = resultSet.getString("district");
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


    /** Use case 10. Gets the top N populated cities in a continent where N is provided by the user.
     *
     * @param continent
     * @param limit
     * @return A result set of the N most populated cities in a continent.
     */
    public ResultSet getTopCitiesInContinent(String continent, int limit, Connection con)
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
            //return result set if valid
            return stmt.executeQuery(strSelect);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get city details");
            return null;
        }
    }

    /** Use case 11. Gets the top N populated cities in a region where N is provided by the user.
     *
     * @param region
     * @param limit
     * @return A result set of the N most populated cities in a region.
     */
    public ResultSet getTopCitiesInRegion(String region, int limit, Connection con)
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
            return(stmt.executeQuery(strSelect));
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
     * @return A result set of the N most populated cities in a country.
     */
    public ResultSet getTopCitiesInCountry(String country, int limit, Connection con)
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
            //return result set if valid.
            return(stmt.executeQuery(strSelect));
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
     * @return A result set of the N most populated cities in a district.
     */
    public ResultSet getTopCitiesInDistrict(String district, int limit, Connection con)
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
            // Return result set if valid.
            return(stmt.executeQuery(strSelect));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get city details");
            return null;
        }
    }

    /** Takes the resultant SQL statements and inputs them into an ArrayList.
     *
     * @param rset A set of results provided by an SQL query.
     * @return A result set of cities containing the required information columns.
     */
    public ArrayList<City> getCitiesArrayList(ResultSet rset) throws SQLException {
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
