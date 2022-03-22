package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Cities;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CitiesTest {
    static Cities city;
    static Connections c = new Connections();
    static Connection con;

    @BeforeAll
    static void init(){
        city = new Cities();
        con = c.connect();
    }

    @AfterAll
    static void disconnect(){
        c.disconnect();
    }

    @Test
    void printCitiesTestNull(){city.printCities(null);}

    @Test
    void printCitiesTestEmpty(){
        ArrayList<City> cities = new ArrayList<>();
        city.printCities(cities);
        //c.disconnect();
    }

    @Test
    void printCitiesTestContainsNull(){
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        city.printCities(cities);
        //c.disconnect();
    }

    @Test
    void printCities(){
        ArrayList<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.name = "Edinburgh";
        city1.countrycode = "United Kingdom";
        city1.population = 450180;
        cities.add(city1);
        city.printCities(cities);
    }

    @Test
    void getCitiesArrayList() throws SQLException{
        city.getTopCitiesInContinent("Europe", 10, con);
        //city.getCitiesArrayList(rset, con);
    }

    @Test
    void getTopCitiesInContinentTestNullConnection(){
        city.getTopCitiesInContinent("Asia", 10, null);
    }

    @Test
    void getTopCitiesInContinentTestNullContinent(){
        city.getTopCitiesInContinent(null, 10, con);
    }

    @Test
    void getTopCitiesInRegionTestNullConnection(){city.getTopCitiesInRegion("Southeast Asia", 10, null);}

    @Test
    void getTopCitiesInRegionTestNullRegion(){city.getTopCitiesInRegion(null, 10, con);}

    @Test
    void getTopCitiesInCountryTestNullConnection(){city.getTopCitiesInCountry("United Kingdom", 10, null);}

    @Test
    void getTopCitiesInCountryTestNullCountry()
    {
        city.getTopCitiesInCountry(null, 10, con);
    }

    @Test
    void getTopCitiesInDistrictTestNullConnection()
    {
        city.getTopCitiesInDistrict("England", 10, null);
    }

    @Test
    void getTopCitiesInDistrictTestNullDistrict()
    {
        city.getTopCitiesInDistrict(null, 10, con);
    }

}
