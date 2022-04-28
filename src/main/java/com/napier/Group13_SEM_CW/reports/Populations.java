package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.Country;
import com.napier.Group13_SEM_CW.Population;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Populations {





    /** Use case 32. Gets a country population
     *
     * @return An array list of the countries in the world in order of descending population.
     **/

    public ResultSet getCountryPopulation(String Country, Connection con) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.name " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital && name = " +
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
    public ArrayList<Population> getPopulationsArrayList (ResultSet rset, Connection con) throws SQLException {
        ArrayList<Population> populations = new ArrayList<>();
        while (rset.next()) {
            Population populations1 = new Population();
            populations1.name = rset.getString("population.name");
            populations1.population = rset.getInt("population.population");
            populations1.cityPopulation = rset.getInt("population.cityPopulation");
            populations1.notCityPopulation = rset.getInt("population.notCityPopulation");

            populations.add(populations1);
        }
        return populations;
    }


    /**
     * Prints the contents specified of a list of populations.
     *
     * @param populations
     */
    public void printPopulation(ArrayList<Population> populations)
    {
        //Check countries is not null
        if (populations == null){
            System.out.println("No Populations");
            return;
        }

        //Print header
        System.out.println(String.format("%-14s %-14s %-5s %-5s", "name", "population", "cityPopulation", "notCityPopulation"));
        //Loop over all populations in the list
        for (Population population : populations)
        {
            if (population == null)
                continue;
            String emp_string =
                    String.format("%-14s %-14s %-5s %-5s",
                            population.name, population.population, population.cityPopulation, population.notCityPopulation);
            System.out.println(emp_string);
        }
    }

}