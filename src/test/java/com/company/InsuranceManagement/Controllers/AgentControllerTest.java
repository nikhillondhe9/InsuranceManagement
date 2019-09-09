/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 13:16
 */
package com.company.InsuranceManagement.Controllers;

import com.company.InsuranceManagement.Exps.AgentNotFoundException;
import com.company.InsuranceManagement.Models.Agent;
import com.company.InsuranceManagement.Services.AgentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.company.InsuranceManagement.JSONStringParser.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AgentController.class)
public class AgentControllerTest {

    private static final Agent agent = new Agent(1L, "John", "John@example.com", "12QWaszx",23, 123456L,"BofA");

    @MockBean
    private AgentService agentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAgent_willReturnAgent() throws Exception {
        given(agentService.getAgent(1L)).willReturn(agent);

        mockMvc.perform(get("/api/agent/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("John"))
                .andExpect(jsonPath("email").value("John@example.com"));
    }

    @Test
    public void getAgent_willThrowNotFound() throws Exception {
        given(agentService.getAgent(1L)).willThrow(AgentNotFoundException.class);

        mockMvc.perform(get("/api/agent/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllAgents_willReturnList() throws Exception {
        given(agentService.getAllAgents()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/agent/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void addAgent_willReturnAgent() throws Exception {
        given(agentService.addAgent(agent)).willReturn(agent);

        mockMvc.perform(post("/api/agent/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(agent)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modifyAgent_willReturnModifiedAgent() throws Exception {
        given(agentService.modifyAgent(agent)).willReturn(agent);

        mockMvc.perform(put("/api/agent/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(agent)))
                .andExpect(status().isOk());
    }
    @Test
    public void modifyAgent_willThrowNotFound() throws Exception {
        given(agentService.modifyAgent(any())).willThrow(new AgentNotFoundException());

        mockMvc.perform(put("/api/agent/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(agent)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteAgent_willReturnSuccess() throws Exception {
        given(agentService.deleteAgent(1L)).willReturn("success");

        mockMvc.perform(delete("/api/agent/{id}", 1L))
                .andExpect(status().isGone());
    }

    @Test
    public void deleteAgent_willThrowNotFound() throws Exception {
        given(agentService.deleteAgent(1L)).willThrow(new AgentNotFoundException());

        mockMvc.perform(delete("/api/agent/{id}", 1L))
                .andExpect(status().isNotFound());
    }
}
