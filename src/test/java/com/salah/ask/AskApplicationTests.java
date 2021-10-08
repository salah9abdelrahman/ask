package com.salah.ask;

import com.salah.ask.controller.DashboardController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
class AskApplicationTests {
    @Autowired
    DashboardController dashboardController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(dashboardController, "not null");
    }


}
