package com.napier.Group13_SEM_CW;


import java.sql.*;
import java.util.ArrayList;


public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        ArrayList<City> world = a.topPopulatedCapitalsInWorld();

        ArrayList<City> continent = a.topPopulatedCapitalsInContinent();

        ArrayList<City> region = a.topPopulatedCapitalsInRegion("Eastern Asia");

        ArrayList<City> wor = a.getAllCities();

        ArrayList<City> cont = a.getCitiesInContinent("Asia");

        ArrayList<City> reg = a.getCitiesInRegion("Eastern Asia");

        ArrayList<City> coun = a.getCitiesInCountry("Japan");

        ArrayList<City> dist = a.getCitiesInDistrict("Osaka");

        ArrayList<City> top = a.TopPopulatedCities(10);

        ArrayList<City> topCont = a.getTopCitiesInContinent("Asia", 10);

        ArrayList<City> topDist = a.getTopCitiesInDistrict("Osaka", 10);

        ArrayList<City> topReg = a.getTopCitiesInRegion("Eastern Europe", 10);

        ArrayList<City> topCoun = a.getTopCitiesInCountry("Spain", 10);

        ArrayList<City> capWor = a.getCapitalsInWorld();

        ArrayList<City> capReg = a.getCapitalsInRegion("Central America");

        ArrayList<City> capCont = a.getCapitalsInContinent("Asia");

        ArrayList<Country> counWol = a.getCountriesInWorld();

        ArrayList<Country> counCont = a.getCountriesInContinent("Europe");

        ArrayList<Country> counReg = a.getCountriesInRegion("Central America");

        ArrayList<Country> topCounWol = a.getTopCountriesInWorld(7);

        ArrayList<Country> topCounCont = a.getTopCountriesInContinent("North America", 3);

        ArrayList<Country> topCounReg = a.getTopCountriesInRegion("Central America" , 5);

        ArrayList<CountryLanguage> languages = a.getTopLanguagesInWorld();

        //a.printCountries(topCounReg);

        a.printCapitals(region);

      //  a.printCities(capWor);

        //a.printCountryLanguage(languages);
        // Disconnect from database
        a.disconnect();


    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("disconnected");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


    //Countries
    /** Use case 35: Get top 5 languages spoken in the world, the number of people who speak it and the percentage of speakers in the world
     *
     */

     public ArrayList<CountryLanguage> getTopLanguagesInWorld(){
         try {
             //Create an SQL statement
             Statement statement = con.createStatement();
             //Create string for SQL statement
             String strSelect = "SELECT language, ROUND(SUM(population * (percentage / 100))) AS speakers, " +
             "ROUND(( (SUM(population * (percentage / 100))) / (SELECT SUM(DISTINCT population) FROM countrylanguage JOIN country ON (countrycode = country.code)) * 100), 2)  AS percentageOfWorldPopulation " +
             "FROM countrylanguage JOIN country ON (countrycode = country.code) " +
             "WHERE language = 'Chinese' OR language = 'English' OR language = 'Hindi' OR language = 'Spanish' OR language = 'Arabic' " +
             "GROUP BY language " +
             "ORDER BY speakers DESC;";

             ResultSet resultSet = statement.executeQuery(strSelect);

             ArrayList<CountryLanguage> languages = new ArrayList<>();

             while (resultSet.next()){
                 CountryLanguage language = new CountryLanguage();
                 language.language = resultSet.getString("language");
                 language.noOfSpeakers = resultSet.getString("speakers");
                 language.percentage = resultSet.getFloat("percentageOfWorldPopulation");
                 languages.add(language);
             }
             return languages;
         }  catch (Exception e) {
             System.out.println(e.getMessage());
             System.out.println("Failed to get list of most spoken languages");
             return null;
         }
     }

    /**
     * Prints the contents of a countryLanguage  array to the desired specifications.
     */
    public void printCountryLanguage(ArrayList<CountryLanguage> languages) {
        //Check countries is not null
        if (languages == null) {
            System.out.println("No languages");
            return;
        }

        //Print header
        System.out.println(String.format("%-15s %-20s %-10s", "language", "speakers", "percentageOfWorldPopulation"));
        //Loop over all countries in the list
        for (CountryLanguage language : languages) {
            if (language == null)
                continue;
            String emp_string =
                    String.format("%-15s %-20s %-10s",
                            language.language, language.noOfSpeakers, language.percentage);
            System.out.println(emp_string);
        }
    }



    /**
     * Use case 8: Get top countries in a region in order of largest to smallest population
     * N is user input
     **/
    public ArrayList<Country> getTopCountriesInRegion(String region, Integer N) {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name AS capital " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital && region =  '" + region + "'" +
                            "GROUP BY countrycode " +
                            "ORDER BY population DESC " +
                            "LIMIT " + N +";";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();

            while (resultSet.next()){
                Country country = new Country();
                country.code = resultSet.getString("code");
                country.name = resultSet.getString("name");
                country.continent = resultSet.getString("continent");
                country.region = resultSet.getString("region");
                country.population = resultSet.getInt("population");
                country.code2 = resultSet.getString("capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 8: Get top countries in a continent in order of largest to smallest population
     * N is user input
     **/
    public ArrayList<Country> getTopCountriesInContinent(String continent, Integer N) {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name AS capital " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital && continent =  '" + continent + "'" +
                            "GROUP BY countrycode " +
                            "ORDER BY population DESC " +
                            "LIMIT " + N + ";";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();

            while (resultSet.next()){
                Country country = new Country();
                country.code = resultSet.getString("code");
                country.name = resultSet.getString("name");
                country.continent = resultSet.getString("continent");
                country.region = resultSet.getString("region");
                country.population = resultSet.getInt("population");
                country.code2 = resultSet.getString("capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 8: Get top countries in the world in order of largest to smallest population
     **/
    public ArrayList<Country> getTopCountriesInWorld(Integer N) {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name AS capital " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital "+
                            "GROUP BY countrycode " +
                            "ORDER BY population DESC " +
                            "LIMIT " + N + ";";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();

            while (resultSet.next()){
                Country country = new Country();
                country.code = resultSet.getString("code");
                country.name = resultSet.getString("name");
                country.continent = resultSet.getString("continent");
                country.region = resultSet.getString("region");
                country.population = resultSet.getInt("population");
                country.code2 = resultSet.getString("capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 5: Get countries in a region in order of largest to smallest population
     **/
    public ArrayList<Country> getCountriesInRegion(String region) {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name AS capital " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital && region =  '" + region + "'"+
                            "GROUP BY countrycode " +
                            "ORDER BY population DESC;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<Country>();

            while (resultSet.next()){
                Country country = new Country();
                country.code = resultSet.getString("code");
                country.name = resultSet.getString("name");
                country.continent = resultSet.getString("continent");
                country.region = resultSet.getString("region");
                country.population = resultSet.getInt("population");
                country.code2 = resultSet.getString("capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 4: Get countries in a continent in order of largest to smallest population
     **/
    public ArrayList<Country> getCountriesInContinent(String continent) {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.name, country.code, country.continent, country.region, country.population, city.name as capital " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital && continent =  '" + continent + "'" +
                            "GROUP BY countrycode " +
                            "ORDER BY population DESC;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();

            while (resultSet.next()){
                Country country = new Country();
                country.code = resultSet.getString("code");
                country.name = resultSet.getString("name");
                country.continent = resultSet.getString("continent");
                country.region = resultSet.getString("region");
                country.population = resultSet.getInt("population");
                country.code2 = resultSet.getString("capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /** Use case 3. Gets all countries in order from largest to smallest population
     *
     * @return An array list of the countries in the world in order of descending population.
     **/
    public ArrayList<Country> getCountriesInWorld() {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.name, country.code, country.continent, country.region, country.population, city.name AS capital " +
                            "FROM country JOIN city ON countrycode = country.code " +
                            "WHERE city.ID = capital " +
                            "GROUP BY countrycode " +
                            "ORDER BY population DESC;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();

            while (resultSet.next()){
                Country country = new Country();
                country.code = resultSet.getString("code");
                country.name = resultSet.getString("name");
                country.continent = resultSet.getString("continent");
                country.region = resultSet.getString("region");
                country.population = resultSet.getInt("population");
                country.code2 = resultSet.getString("capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }



    //Capitals cities

    /**
     * Use case 14. Gets all capital cities in the world by organised from largest to smallest
     **/
    public ArrayList<City> getCapitalsInWorld() {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE city.ID = country.capital " +
                            "ORDER BY population DESC;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> capitals = new ArrayList<City>();

            while (resultSet.next()){
                City capital = new City();
                capital.name = resultSet.getString("name");
                capital.countrycode = resultSet.getString("country");
                capital.population = resultSet.getInt("population");
                capitals.add(capital);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }

    /**
     * Use case 15. Gets all capital cities in a continent define by user organised from largest to smallest.
     **/
    public ArrayList<City> getCapitalsInContinent(String continent)
    {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE city.ID = country.capital AND continent = '" + continent + "'" +
                            " ORDER BY population DESC;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> capitals = new ArrayList<City>();

            while (resultSet.next()){
                City capital = new City();
                capital.name = resultSet.getString("name");
                capital.countrycode = resultSet.getString("country");
                capital.population = resultSet.getInt("population");
                capitals.add(capital);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }

    /**
     * Use case 16. Gets all capital cities in a region defined by user organised from largest to smallest
     **/
    public ArrayList<City> getCapitalsInRegion(String region) {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE city.ID = country.capital AND region = '" + region + "'" +
                            " ORDER BY population DESC;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> capitals = new ArrayList<City>();

            while (resultSet.next()){
                City capital = new City();
                capital.name = resultSet.getString("name");
                capital.countrycode = resultSet.getString("country");
                capital.population = resultSet.getInt("population");
                capitals.add(capital);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    //Cities

    /**
     * The top N populated cities in the world where N is provided by the user.
     *
     * In this case N is 10.
     */
    public ArrayList<City> TopPopulatedCities(Integer N){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name as country, city.district, city.population "
                            + "FROM city JOIN country ON (code = city.countrycode)"
                            + "ORDER BY population DESC "
                            + "LIMIT " + N + ";";
            //Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("country");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 10. Gets the top N populated cities in a continent where N is provided by the user.
     **/
    public ArrayList<City> getTopCitiesInContinent(String continent, Integer N) {
        try {
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE continent = '" + continent + "'" +
                            " ORDER BY population DESC " +
                            " LIMIT " + N + ";";

            //Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("country");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 27. Gets the cities in a district from highest pop to lowest
     **/
    public ArrayList<City> getCitiesInDistrict(String district) {
        try {
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name as country, city.district, city.population "
                            + "FROM city JOIN country ON (code = city.countrycode)"
                            + "WHERE district = '" + district +"'"
                            + "ORDER BY population DESC ;";


            //Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("country");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 12. Gets the top N populated cities in a country where N is provided by the user.
     **/
    public ArrayList<City> getTopCitiesInCountry(String country, Integer N)
    {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE country.name = '" + country +"'" +
                            " ORDER BY population DESC " +
                            " LIMIT " + N +";";
            //Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("country");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 11. Gets the top N populated cities in a region where N is provided by the user.
     **/
    public ArrayList<City> getTopCitiesInRegion(String region, Integer N)
    {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE region = '" + region + "'" +
                            " ORDER BY population DESC " +
                            " LIMIT " + N +";";
            //Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("country");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * Use case 13. Gets the top N populated cities in a district where N is provided by the user.
     **/
    public ArrayList<City> getTopCitiesInDistrict(String district, Integer N)
    {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, district, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE district = '" + district + "'" +
                            " ORDER BY population DESC " +
                            " LIMIT " + N + ";";
            //Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("country");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * This method is used to get
     * All the cities in the a COUNTRY ORGANISED BY LARGEST POPULATION TO SMALLEST
     *
     * @return a list of cities, or null if there is an error
     */
    public ArrayList<City> getCitiesInCountry(String country){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name AS country, city.district, city.population "
                            + "FROM city JOIN country ON (code = city.countrycode) "
                            + "WHERE country.name = '" + country + "' "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
                city.countrycode = resultSet.getString("country");
                city.district = resultSet.getString("district");
                city.population = resultSet.getInt("population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }



    /**
     * This method is used to get
     * All the cities in the a REGION ORGANISED BY LARGEST POPULATION TO SMALLEST
     *
     * @return a list of cities, or null if there is an error
     */
    public ArrayList<City> getCitiesInRegion(String region){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name as country, city.district, city.population "
                            + "FROM city JOIN country ON (code = city.countrycode) "
                            + "WHERE region = '" + region + "' "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("name");
                city.countrycode = resultSet.getString("country");
                city.district = resultSet.getString("district");
                city.population = resultSet.getInt("population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * This method is used to get
     * All the cities in the a CONTINENT ORGANISED BY LARGEST POPULATION TO SMALLEST
     *
     * @return a list of cities, or null if there is an error
     */
    public ArrayList<City> getCitiesInContinent(String continent){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name as country, city.district,  city.population "
                            + "FROM city JOIN country ON (code = city.countrycode) "
                            + "WHERE continent = '" + continent + "' "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()){
                City city = new City();
                city.name = resultSet.getString("city.name");
                city.countrycode = resultSet.getString("country");
                city.district = resultSet.getString("district");
                city.population = resultSet.getInt("population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }


    /**
     * This method is used to get
     * All the cities in the WORLD ORGANISED BY LARGEST POPULATION TO SMALLEST
     *
     * @return a list of cities, or null if there is an error
     */
    public ArrayList<City> getAllCities(){
        try{
            //Create an SQL statement
            Statement statement = con.createStatement();

            //Create string for SQL statement
            String strSelect =
                    "SELECT city.name,country.name as country, city.district, city.population "
                            + "FROM city JOIN country ON (code = city.countrycode) "
                            + "ORDER BY population DESC ";

            //Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city1 = new City();
                city1.name = rset.getString("city.name");
                city1.countrycode = rset.getString("country");
                city1.district = rset.getString("district");
                city1.population = rset.getInt("city.population");
                cities.add(city1);
            }
            return cities;
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get list of all cities");
            return null;
        }
    }

    /** Use case 17. The top N populated capital cities in the world where N is provided by the user.
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> topPopulatedCapitalsInWorld()
    {

        try {


            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name as countrycode, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE city.id = country.capital" +
                            " ORDER BY population DESC LIMIT 5;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> capitals = new ArrayList<>();
            while (rset.next()) {
                City capital1 = new City();
                capital1.name = rset.getString("city.name");
                capital1.countrycode = rset.getString("countrycode");
                capital1.population = rset.getInt("city.population");
                capitals.add(capital1);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }

    /** Use case 18. The top N populated capital cities in a continent where N is provided by the user.
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> topPopulatedCapitalsInContinent()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name as country, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE city.ID = country.capital AND continent = 'Africa'" +
                            " ORDER BY population DESC LIMIT 10;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return city if valid.
            // Check one is returned.
            ArrayList<City> capitals = new ArrayList<>();
            while (rset.next()) {
                City capital1 = new City();
                capital1.name = rset.getString("city.name");
                capital1.countrycode = rset.getString("country");
                capital1.population = rset.getInt("city.population");
                capitals.add(capital1);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }


    /** Use case 19. The top N populated capital cities in a region where N is provided by the user.
     *
     * @return An array list of the capital cities in the world in order of desceding population.
     **/
    public ArrayList<City> topPopulatedCapitalsInRegion(String region)
    {
        try {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name as country, city.population " +
                            "FROM city JOIN country ON (code = city.countrycode) " +
                            "WHERE city.id = country.capital AND region = '" + region + "'" +
                            "ORDER BY population " +
                            "DESC LIMIT 10;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);

            //Extract city information
            ArrayList<City> capitals = new ArrayList<City>();

            while (resultSet.next()){
                City capital = new City();
                capital.name = resultSet.getString("name");
                capital.countrycode = resultSet.getString("country");
                capital.population = resultSet.getInt("population");
                capitals.add(capital);
            }
            return capitals;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Didn't manage to get capital city details");
            return null;
        }
    }



    /**
     * Prints the contents specified of a list of capital cities.
     *
     * @param capitals
     */
    public void printCapitals(ArrayList<City> capitals)
    {
        // Check cities is not null
        if (capitals == null)
        {
            System.out.println("No cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-14s %-20s", "name", "country code", "population"));
        // Loop over all countries in the list
        for (City capital : capitals)
        {
            if (capital == null)
                continue;
            String emp_string =
                    String.format("%-30s %-14s %-20s",
                            capital.name, capital.countrycode, capital.population);
            System.out.println(emp_string);
        }
    }


    /**
     * Prints the contents specified of a list of countries.
     *
     * test
     *
     * @param cities
     */
    public void printCities(ArrayList<City> cities)
    {
        // Check cities is not null
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-14s %-20s %-8s", "name", "country code", "district", "population"));
        // Loop over all countries in the list
        for (City city : cities)
        {
            if (city == null)
                continue;
            String emp_string =
                    String.format("%-30s %-14s %-20s %-8s",
                            city.name, city.countrycode, city.district, city.population);
            System.out.println(emp_string);
        }
    }


    /**
     * Prints the contents specified of a list of countries.
     *
     * @param countries
     */
    public void printCountries(ArrayList<Country> countries) {
        //Check countries is not null
        if (countries == null) {
            System.out.println("No countries");
            return;
        }

        //Print header
        System.out.println(String.format("%-3s %-14s %-14s %-14s %-20s %-6s", "code", "name", "continent", "region", "population", "capital"));
        //Loop over all countries in the list
        for (Country country : countries) {
            if (country == null)
                continue;
            String emp_string =
                    String.format("%-3s %-14s %-14s %-14s %-20s %-6s",
                            country.code, country.name, country.continent, country.region, country.population, country.code2);
            System.out.println(emp_string);
        }
    }
}