
package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.reports.Capitals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CapitalsTest {
    static Capitals capital;

    /*
        static Connections c = new Connections();
        static Connection con;

        @BeforeAll
        static void init(){
            capital = new Capitals();
            con = c.oldConnect();
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
    */
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
}
/*
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
    void getCapitalsInContinentTestNullContinent()
    {
        capital.getCapitalsInContinent(null, con);
    }

    @Test
    void getCapitalsInContinentTestNullConnection()
    {
        capital.getCapitalsInContinent("Asia", null);
    }

    @Test
    void getCapitalsInRegionTestNullRegion()
    {
        capital.getCapitalsInRegion(null, con);
    }

    @Test
    void getCapitalsInRegionTestNullConnection()
    {
        capital.getCapitalsInRegion("Southeast Asia", null);
    }

    @Test
    void getCapitalsInWorldTestNull() {
        capital.getCapitalsInWorld(null);
    }



    //unit tests for use case 17
    @Test
    void topPopulatedCapitalsInWorldTestEmpty()
    {
        capital.topPopulatedCapitalsInWorld(0, con);
    }

    @Test
    void topPopulatedCapitalsInWorldTestNullConnection()
    {
        capital.topPopulatedCapitalsInWorld(6, null);
    }

    @Test
    void topPopulatedCapitalsInWorldTest()
    {
        capital.topPopulatedCapitalsInWorld(5, con);
    }

    @Test
    void getWorldPopulationArrayList() throws SQLException{
        try{
            ResultSet rset;
            rset = (ResultSet) capital.topPopulatedCapitalsInWorld(10, con);
            ArrayList<City> capitals = capital.getCapitalsArrayList(rset);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //unit tests for use case 18
    @Test
    void topPopulatedCapitalsInContinentTestEmpty()
    {
        capital.topPopulatedCapitalsInContinent(0, con);
    }

    @Test
    void topPopulatedCapitalsInContinentTestNullConnection()
    {
        capital.topPopulatedCapitalsInContinent(6, null);
    }

    @Test
    void topPopulatedCapitalsInContinentTest()
    {
        capital.topPopulatedCapitalsInContinent(5, con);
    }

    @Test
    void getContinentPopulationArrayList() throws SQLException{
        try{
            ResultSet rset;
            rset = (ResultSet) capital.topPopulatedCapitalsInContinent(10, con);
            ArrayList<City> capitals = capital.getCapitalsArrayList(rset);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //unit tests for use case 19
    @Test
    void topPopulatedCapitalsInRegionTestEmpty()
    {
        capital.topPopulatedCapitalsInRegion(0, con);
    }

    @Test
    void topPopulatedCapitalsInRegionTestNullConnection()
    {
        capital.topPopulatedCapitalsInRegion(6, null);
    }

    @Test
    void topPopulatedCapitalsInRegionTest()
    {
        capital.topPopulatedCapitalsInRegion(5, con);
    }

    @Test
    void getRegionPopulationArrayList() throws SQLException{
        try{
            ResultSet rset;
            rset = (ResultSet) capital.topPopulatedCapitalsInRegion(10, con);
            ArrayList<City> capitals = capital.getCapitalsArrayList(rset);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}

*/
