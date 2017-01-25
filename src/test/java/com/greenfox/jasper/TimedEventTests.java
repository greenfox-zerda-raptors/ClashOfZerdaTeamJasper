package com.greenfox.jasper;

import com.greenfox.jasper.services.EventServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zoltán on 2017.01.24..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimedEventTests {
    @Autowired
    EventServices eventServices;

    @Test
    public void testForEventServices(){

    }
    @Test
    public void testSampleService() {
        assertEquals(
                "class com.greenfox.jasper.services.EventServices",
        this.eventServices.getClass().toString());
    }

}
