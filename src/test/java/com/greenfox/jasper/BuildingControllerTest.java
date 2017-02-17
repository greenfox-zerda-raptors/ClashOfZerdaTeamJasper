package com.greenfox.jasper;

import com.greenfox.jasper.repos.BuildingRepo;
import com.greenfox.jasper.repos.KingdomRepo;
import com.greenfox.jasper.services.BuildingServices;
import com.greenfox.jasper.services.KingdomServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Zoltán on 2017.02.16..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestExecutionListeners(value = TransactionalTestExecutionListener.class)
@ContextConfiguration(classes = ProofOfConceptApplication.class, loader = SpringBootContextLoader.class)
@Transactional
public class BuildingControllerTest extends AbstractJUnit4SpringContextTests{

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private KingdomServices kingdomServices;

    @Autowired
    private KingdomRepo kingdomRepo;

    @Autowired
    private BuildingServices buildingServices;

    @Autowired
    private BuildingRepo buildingRepo;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void buildingControllerTest() throws Exception {
        mockMvc.perform(get("/2/buildings")).andExpect(status().isNotFound());
    }
    @Test
    public void realmTest() throws Exception {
        mockMvc.perform(get("/realm"))
                .andExpect(status().is2xxSuccessful());
    }

//    @Test
//    public void testTest() throws Exception {
//        mockMvc.perform(post("/api/users").header("Authorization", base64ForTestUser).contentType(MediaType.APPLICATION_JSON)
//                .content("{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andReturn();
//    }



}