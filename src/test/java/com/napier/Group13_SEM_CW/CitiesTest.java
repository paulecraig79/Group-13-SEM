package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Cities;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CitiesTest {
    static Cities city;
    static Connections c = new Connections();
    static Connection con;

    @BeforeAll
    static void init(){
        city = new Cities();
        con = c.oldConnect();
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
    void getCapitalsArrayList() throws SQLException  {
        try {
            ResultSet rset;
            rset = city.getTopCitiesInContinent("Europe", 10);
            ArrayList<City> cities = city.getCitiesArrayList(rset);

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Test
    void getTopCitiesInContinentTestNullConnection(){
        city.getTopCitiesInContinent("Asia", 10);
    }

    @Test
    void getTopCitiesInContinentTestNullContinent(){
        city.getTopCitiesInContinent(null, 10);
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


    @Test
    void getAllCitiesInTheWorldTest(){
        city.getAllCities(con);
    }

    @Test
    void getCitiesInContinentTest(){
      city.getCitiesInContinent("Europe", con);
    }

    @Test
    void getCitiesInRegionTest(){
        city.getCitiesInRegion(null, con);
    }

    @Test
    void getCitiesInCountryTest(){
        city.getCitiesInCountry("China", con);
    }

    @Test
    void getCitiesInCountryTestNullConnection(){
        city.getCitiesInCountry("China", null);
    }

    @Test
    void getCitiesInDistrictTest(){
        city.getCitiesInDistrict(con);
    }

    @Test
    void TopPopulatedCitiesTest(){
        city.TopPopulatedCities(con);
    }
}
