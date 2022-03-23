package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Cities;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
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
    void testGetCountry()
    {
        city.getTopCitiesInContinent("Europe", 10, con);
    }
}
