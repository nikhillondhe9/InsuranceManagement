/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-12
 * Time: 12:37
 */
package com.company.InsuranceManagement.Controllers;

import com.company.InsuranceManagement.Exps.PolicyNotFoundException;
import com.company.InsuranceManagement.Models.Policy;
import com.company.InsuranceManagement.Services.PolicyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.company.InsuranceManagement.JSONStringParser.asJsonString;
import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Policy Controller Tests
 * Uses Spring Runner and WebMvcTest
 * assertions using assertJ
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {

    private static final Policy policy = new Policy(1L, "Motor Insurance");

    @MockBean
    private PolicyService policyService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * get policy wil return a policy, if the policy is present in database
     * @throws Exception if unable to perform mock
     */
    @Test
    public void getPolicy_willReturnPolicy() throws Exception {
        given(policyService.getPolicy("Motor Insurance")).willReturn(new Policy(1L, "Motor Insurance"));

        mockMvc.perform(get("/api/policy/Motor Insurance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("policyName").value("Motor Insurance"));
    }

    /**
     * get policy will throw exception if the policy is not found in database
     * @throws Exception if policy not found
     */
    @Test
    public void getPolicy_willReturnNotFound() throws Exception {
        given(policyService.getPolicy("Motor Insurance")).willThrow(new PolicyNotFoundException());

        mockMvc.perform(get("/api/policy/Motor Insurance"))
                .andExpect(status().isNotFound());
    }

    /**
     * get all policies should return a list or empty if there are no policies in database
     * @throws Exception if unable to perform the mock
     */
    @Test
    public void getAllPolicies_willReturnList() throws Exception {
        given(policyService.getAllPolicies()).willReturn(emptyList());

        mockMvc.perform(get("/api/policy/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * add policy should return details of added policy
     * @throws Exception if policy is not found
     */
    @Test
    public void addPolicy_willReturnPolicy() throws Exception {
        given(policyService.addPolicy(policy)).willReturn(policy);

        mockMvc.perform(post("/api/policy")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(policy)))
                .andExpect(status().isCreated());
    }

    /**
     * delete policy will return "success"
     * @throws Exception if policy is not found
     */
    @Test
    public void deletePolicy_WillReturnSuccess() throws Exception {
        given(policyService.deletePolicy(policy.getPolicyNo())).willReturn("success");

        mockMvc.perform(delete("/api/policy/{policyNo}", 1L))
                .andExpect(status().isOk());
    }

    /**
     * delete policy should throw exception if the policy is not found
     * @throws Exception if unable to perform mock
     */
    @Test
    public void deletePolicy_WillReturnNotFound() throws Exception {
        given(policyService.deletePolicy(policy.getPolicyNo())).willThrow(new PolicyNotFoundException());

        mockMvc.perform(delete("/api/policy/{policyNo}", 1L))
                .andExpect(status().isNotFound());
    }

    /**
     * modify policy will modify and return modified policy
     * @throws Exception if unable to perform mock
     */
    @Test
    public void modifyPolicy_willReturnUpdatedPolicy() throws Exception {
        given(policyService.modifyPolicy(policy)).willReturn(new Policy(1L, "Motor Insurance"));

        mockMvc.perform(put("/api/policy/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(policy)))
                .andExpect(status().isOk());
    }

    /**
     * modify policy will throw exception if policy is not found in the database
     * @throws Exception if policy is not found
     */
    @Test
    public void modifyPolicy_willThrowPolicyNotFound() throws Exception {
        given(policyService.modifyPolicy(any())).willThrow(new PolicyNotFoundException());

        mockMvc.perform(put("/api/policy/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(policy)))
                .andExpect(status().isNotFound());
    }
}
