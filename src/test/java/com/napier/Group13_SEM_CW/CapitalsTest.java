package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalsTest {
    static Capitals capital;
    static Connections c = new Connections();
    static Connection con;

    @BeforeAll
    static void init(){
        capital = new Capitals();
        con = c.connect();
    }

    @AfterAll
    static void disconnect(){
        c.disconnect();
    }

    @Test
    void printCapitalsTestNull() {
        capital.printCapitals(null);
    }

    @Test
    void printCapitalsTestEmpty() {
        ArrayList<City> capitals = new ArrayList<>();
        capital.printCapitals(capitals);
    }

    @Test
    void printCapitalsTestsContainsNull() {
        ArrayList<City> capitals = new ArrayList<>();
        capitals.add(null);
        capital.printCapitals(capitals);
    }

    @Test
    void printCapitals() {
        ArrayList<City> capitals = new ArrayList<>();
        City capital1 = new City();
        capital1.name = "Cario";
        capital1.district = "Kairo";
        capital1.countrycode = "EGY";
        capital1.population = 67894979;
        capitals.add(capital1);
        capital.printCapitals(capitals);
    }

    @Test
    void getCapitalsArrayList() throws SQLException  {
        try {
            ResultSet rset;
            rset = capital.getCapitalsInWorld(con);
            ArrayList<City> capitals = capital.getCapitalsArrayList(rset);

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Test
    void getCapitalsInWorldTestNullConnection() {
        capital.getCapitalsInWorld(null);
    }

    @Test
    void getCapitalsInContinentTestNullContinent() {
        capital.getCapitalsInContinent(null, con);
    }

    @Test
    void getCapitalsInContinentTestNullConnection() {
        capital.getCapitalsInContinent("Asia", null);
    }

    @Test
    void getCapitalsInRegionTestNullRegion() {
        capital.getCapitalsInRegion(null, con);
    }

    @Test
    void getCapitalsInRegionTestNullConnection(){
        capital.getCapitalsInRegion("Southeast Asia", null);
    }

    @Test
    void topPopulatedCapitalsInWorldTestEmpty()
    {
        capital.topPopulatedCapitalsInWorld(0, con);
    }

    @Test
    void topPopulatedCapitalsInWorldTestNullConnection()
    {
        capital.topPopulatedCapitalsInWorld(0, null);
    }

    @Test
    void topPopulatedCapitalsInWorldTest()
    {
        capital.topPopulatedCapitalsInWorld(5, con);
    }
}

