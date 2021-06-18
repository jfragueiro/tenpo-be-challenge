package com.tenpo.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OperationControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void should_return_200_when_all_params_ok() throws Exception {
        mockMvc.perform(get("/operations/multiply/2/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_404_when_parameter_not_present() throws Exception {
        mockMvc.perform(get("/operations/multiply/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_400_when_pbad_arameter() throws Exception {
        mockMvc.perform(get("/operations/multiply/2/a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_405_when_http_method_is_not_post() throws Exception {
        mockMvc.perform(post("/operations/multiply/2.3/0")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(put("/operations/multiply/2.3/0")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(delete("/operations/multiply/2.3/0")).andExpect(status().isMethodNotAllowed());
        mockMvc.perform(patch("/operations/multiply/2.3/0")).andExpect(status().isMethodNotAllowed());
    }

}
