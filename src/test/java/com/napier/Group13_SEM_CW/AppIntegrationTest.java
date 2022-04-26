package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Cities;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.sql.Connection;

public class AppIntegrationTest {
    static Cities city;
    static Connections c = new Connections();
    static Connection con = null;
    static App app = new App();

    @BeforeAll
    static void init() {
        city = new Cities();
        c.newConnect("localhost:33060", 1000);
        Connection con = null;

    }

    @AfterAll
    static void disconnect()
    {
        c.disconnect();
    }

//    @Test
//    void testGetTopCitiesInContinent() throws SQLException
//    {
//        ResultSet rset = city.getTopCitiesInContinent("Europe", 10);
//        ArrayList<City> cities = city.getCitiesArrayList(rset);
//        City city1 = cities.get(0);
//        assertEquals(city1.name, "Moscow");
//        assertEquals(city1.population, 8389200);
//        assertEquals(city1.countrycode, "Russian Federation");
//        assertEquals(city1.district, "Moscow (City)");
//    }
//
//    @Test
//    void testTopPopulatedCities(){
//        ArrayList<City> cities = city.TopPopulatedCities(con);
//        City city1 = cities.get(0);
//        assertEquals(city1.name, "Mumbai (Bombay)");
//        assertEquals(city1.population, 10500000);
//    }
//
//    @Test
//    void testgetAllCities(){
//        ArrayList<City> cities = city.getAllCities(con);
//        City city1 = cities.get(0);
//        assertEquals(city1.name, "Mumbai (Bombay)");
//        assertEquals(city1.population, 10500000);
//    }

}
