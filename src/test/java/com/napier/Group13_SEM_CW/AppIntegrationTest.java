package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Cities;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static Cities city;
    static Connections c = new Connections();
    static Connection con;

    @BeforeAll
    static void init() {
        city = new Cities();
        con = c.oldConnect();
    }

    @AfterAll
    static void disconnect()
    {
        c.disconnect();
    }

    @Test
    void testGetTopCitiesInContinent() throws SQLException
    {
        ResultSet rset = city.getTopCitiesInContinent("Europe", 10, con);
        ArrayList<City> cities = city.getCitiesArrayList(rset);
        City city1 = cities.get(0);
        assertEquals(city1.name, "Moscow");
        assertEquals(city1.population, 8389200);
        assertEquals(city1.countrycode, "Russian Federation");
        assertEquals(city1.district, "Moscow (City)");
    }

}
