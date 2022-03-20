package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalsTest
{
    static Capitals capital;

    Connections c = new Connections();

    Connection con = c.connect();

    @BeforeAll
    static void init()
    {
        capital = new Capitals();
    }

    @Test
    void printCapitalsTestNull()
    {
        capital.printCapitals(null);
    }

    @Test
    void printCapitalsTestEmpty()
    {
        ArrayList<City> capitals = new ArrayList<>();
        capital.printCapitals(capitals);
    }

    @Test
    void printCapitalsTestsContainsNull()
    {
        ArrayList<City> capitals = new ArrayList<>();
        capitals.add(null);
        capital.printCapitals(capitals);
    }

    @Test
    void printCapitals()
    {
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
    void getCapitalsArrayList() throws SQLException {
        ResultSet rset = capital.getCapitalsInWorld(con);
        capital.getCapitalsArrayList(rset, con);
    }


}

