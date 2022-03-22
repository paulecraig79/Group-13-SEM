package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import com.napier.Group13_SEM_CW.reports.Capitals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class CapitalTest19 {

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
    void topPopulatedCapitalsInRegionTestEmpty()
    {
        capitals.topPopulatedCapitalsInRegion(null, con);
    }

    @Test
    void topPopulatedCapitalsInRegionTestNull()
    {
        capitals.topPopulatedCapitalsInRegion(0, null);
    }

    @Test
    void topPopulatedCapitalsInRegionTest()
    {
        capitals.topPopulatedCapitalsInRegion(5, con);
    }
}
