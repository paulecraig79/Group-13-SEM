package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.Country;
import java.sql.*;
import java.util.ArrayList;

public class Countries {

    /** Use case 1. Gets all countries in order from largest to smallest population
     *
     * @return An array list of the countries in the world in order of descending population.
     **/

    public ResultSet getCountriesInWorld(Connection con) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital " +
                            "GROUP BY countrycode " +
                            "ORDER BY population DESC;";
            // Execute SQL statement
            return (stmt.executeQuery(strSelect));
            // Return city if valid.
            // Check one is returned.
        }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Didn't manage to get country details");
                return null;
            }
        }


        /** Takes the resultant SQL statements and inputs them into an Arraylist
         *
         * @param rset A set of results provided by an SQL query
         * @param con A connection to the world database
         * @return An Array list of countries containing the required info columns
         */
        public ArrayList<Country> getCountriesArrayList (ResultSet rset, Connection con) throws SQLException {
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                Country countries1 = new Country();
                countries1.code = rset.getString("country.code");
                countries1.name = rset.getString("country.name");
                countries1.continent = rset.getString("country.continent");
                countries1.region = rset.getString("country.region");
                countries1.population = rset.getInt("country.population");
                countries1.capital = rset.getInt("city.name");
                countries.add(countries1);
            }
            return countries;
        }

    /**
     * Prints the contents specified of a list of countries.
     *
     * @param countries
     */
    public void printCountries(ArrayList<Country> countries)
    {
        //Check countries is not null
        if (countries == null){
            System.out.println("No countries");
            return;
        }

        //Print header
        System.out.println(String.format("%-3s %-14s %-14s %-14s %-20s %-6s", "code", "name", "continent", "region", "population", "capital"));
        //Loop over all countries in the list
        for (Country country : countries)
        {
            if (country == null)
                continue;
            String emp_string =
                    String.format("%-3s %-14s %-14s %-14s %-20s %-6s",
                            country.code, country.name ,country.continent, country.region, country.population, country.capital);
            System.out.println(emp_string);
        }
    }


}
