package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.Population;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Populations {





    /**
     * Use case 32. Gets a country population
     *
     * @return An array list of the countries in the world in order of descending population.
     **/

    public ArrayList<Population> getCountryPopulation(String Country, Connection con) {

        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.name, country.population, sum(city.population) as CountryCityPopulation, (country.population - sum(city.population))  as CountryNonCityPopulation " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE country.name = '" + Country + "'" +
                            "GROUP BY countrycode; ";

            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);






            ArrayList<Population> populations = new ArrayList<>();
            while (resultSet.next()) {
                Population populations1 = new Population();
                populations1.name = resultSet.getString("population.name");
                populations1.population = resultSet.getInt("population.population");
                populations1.cityPopulation = resultSet.getInt("population.cityPopulation");
                populations1.notCityPopulation = resultSet.getInt("population.notCityPopulation");

                populations.add(populations1);
            }
            return  populations;
        }

        catch(Exception e){
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