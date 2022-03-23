package com.napier.Group13_SEM_CW;



import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import com.napier.Group13_SEM_CW.reports.Countries;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CountriesTest {

    static Countries country;

//    Connections c = new Connections();
//
//    Connection con = c.connect();

    @BeforeAll
    static void init()
    {
        country = new Countries();
    }

    @Test
    void printCountriesTestNull()
    {
        country.printCountries(null);
    }


    @Test
    void printCapitalsTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<>();
        country.printCountries(countries);
    }

    @Test
    void printCapitalsTestsContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        country.printCountries(countries);
    }

}
