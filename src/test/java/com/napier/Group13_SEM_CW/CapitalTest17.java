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


public class CapitalTest17 {

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
    void topPopulatedCapitalsInWorldTestEmpty()
    {
        capitals.topPopulatedCapitalsInWorld(null, con);
    }

    @Test
    void topPopulatedCapitalsInWorldTestNull()
    {
        capitals.topPopulatedCapitalsInWorld(0, null);
    }

    @Test
    void topPopulatedCapitalsInWorldTest()
    {
        capitals.topPopulatedCapitalsInWorld(5, con);
    }
}
