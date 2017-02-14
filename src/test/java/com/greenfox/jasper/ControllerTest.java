//package com.greenfox.jasper;
//
//import com.greenfox.jasper.controllers.BuildingController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
///**
// * Created by Zoltán on 2017.02.14..
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
//@Configuration
//@EnableWebMvc
//public class ControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//
//    @Test
//    public void buildingControllerTest(){
////        mockMvc.perform(get(
////                "/kingdom/buildings")
////                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isForbidden());
//
//    }
//
//
//    @Configuration
//    @EnableWebMvc
//    public static class TestConfiguration {
//        @Bean
//        public BuildingController buildingController(){
//            return new BuildingController();
//        }
//    }
//
//
//}