package org.agm.sbtest.api.ping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.agm.sbtest.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class PingMvcTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testPing() throws Exception {

        MvcResult mvcResponse = mvc.perform(get("/")).andExpect(status().isOk()).andReturn();

        PingResult ping = JsonUtils.toObject(mvcResponse.getResponse().getContentAsString(),
                new TypeReference<PingResult>() {
                });

        assertNotNull(ping);
        assertEquals("Spring Boot test application running", ping.getMessage());
        assertTrue(ping.isRunning());
    }
}
