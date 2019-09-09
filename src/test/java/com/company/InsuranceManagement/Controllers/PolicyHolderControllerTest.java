/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 16:09
 */
package com.company.InsuranceManagement.Controllers;

import com.company.InsuranceManagement.Exps.PolicyHolderNotFoundException;
import com.company.InsuranceManagement.Models.PolicyHolder;
import com.company.InsuranceManagement.Services.PolicyHolderService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PolicyHolderController.class)
public class PolicyHolderControllerTest {

    private static final PolicyHolder policyHolder = new PolicyHolder(1L, "John", "john@example.com",
            "12QWas", "male", 1234566L, "BofA", "blah blah blah");

    @MockBean
    private PolicyHolderService policyHolderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPolicyHolder_willReturnPolicyHolder() throws Exception {
        given(policyHolderService.getPolicyHolder(1L)).willReturn(policyHolder);

        mockMvc.perform(get("/api/policyHolder/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("John"));
    }

    @Test
    public void getPolicyHolder_willThrowNotFound() throws Exception {
        given(policyHolderService.getPolicyHolder(1L)).willThrow(new PolicyHolderNotFoundException());

        mockMvc.perform(get("/api/policyHolder/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllPolicyHolders_willReturnList() throws Exception {
        given(policyHolderService.getAllPolicies()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/policyHolder/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void addPolicyHolder_willReturnAddedPolicyHolder() throws Exception {
        given(policyHolderService.addPolicyHolder(policyHolder)).willReturn(policyHolder);

        mockMvc.perform(post("/api/policyHolder")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(policyHolder)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modifyPolicyHolder_willReturnModifiedPolicy() throws Exception {

    }
}
