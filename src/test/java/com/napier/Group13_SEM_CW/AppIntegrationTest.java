package com.napier.Group13_SEM_CW;

import com.napier.Group13_SEM_CW.database.Connections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {

    static App app;
    static Connections c = new Connections();

    @BeforeAll
    static void init() {
        app = new App();
        c.newConnect("localhost:33060", 3000);

    }
}
