package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class CapitalTest18 {

    static Capitals capitals;
    static Connections c = new Connections();
    static Connection con;

    @BeforeAll
    static void init()
    {
        capitals = new Capitals();
        con = c.connect();
    }


    @AfterAll
    static void disconnect()
    {
        c.disconnect();
    }

    @Test
    void topPopulatedCapitalsInContinentTestEmpty()
    {
        capitals.topPopulatedCapitalsInContinent(null, con);
    }

    @Test
    void topPopulatedCapitalsInContinentTestNull()
    {
        capitals.topPopulatedCapitalsInContinent(0, null);
    }

    @Test
    void topPopulatedCapitalsInContinentTest()
    {
        capitals.topPopulatedCapitalsInContinent(5, con);
    }
}
