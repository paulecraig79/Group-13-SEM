package com.napier.Group13_SEM_CW.reports;

import com.napier.Group13_SEM_CW.Country;
import java.sql.*;
import java.util.ArrayList;

public class Countries {

    /** Use case 1. Gets all countries in order from largest to smallest population
     *
     * @return An array list of the countries in the world in order of desceding population.
     **/

    public ArrayList<Country> getCountriesInWorld(Connection con)
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, country.capital " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            " ORDER BY population DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                Country countries1 = new Country();
                countries1.code = rset.getString("country.code");
                countries1.name = rset.getString("country.name");
                countries1.continent= rset.getString("country.continent");
                countries1.region= rset.getString("country.region");
                countries1.population= rset.getInt("country.population");
                countries1.capital= rset.getInt("country.capital");
                countries.add(countries1);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get country details");
            return null;
        }
    }




}
