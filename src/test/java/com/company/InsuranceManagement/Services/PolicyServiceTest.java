package com.company.InsuranceManagement.Services;

import com.company.InsuranceManagement.Exps.PolicyNotFoundException;
import com.company.InsuranceManagement.Models.Policy;
import com.company.InsuranceManagement.Repositories.PolicyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Policy Service Test
 * Light weight unit testing using Mockito
 */
@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceTest {

    private static final Policy policy = new Policy(1L, "Motor Insurance");

    private PolicyService policyService;

    @Mock
    private PolicyRepository policyRepository;

    /**
     * Setup before starting the tests
     */
    @Before
    public void setUp() {
        policyService = new PolicyService(policyRepository);
    }

    /**
     * get policy should return a policy
     */
    @Test
    public void getPolicy_willReturnPolicy(){
        given(policyRepository.getPolicyByPolicyName(anyString())).willReturn(policy);

        Policy pol = policyService.getPolicy("Motor Insurance");

        assertThat(pol.getPolicyName()).isEqualTo(policy.getPolicyName());
        assertThat(pol.getPolicyNo()).isEqualTo(policy.getPolicyNo());

        verify(policyRepository).getPolicyByPolicyName(anyString());
    }

    /**
     * get policy will throw exception if policy is not found
     * @throws PolicyNotFoundException
     */
    @Test(expected = PolicyNotFoundException.class)
    public void getPolicy_willReturnNotFound() throws PolicyNotFoundException {
        given(policyRepository.getPolicyByPolicyName(anyString())).willReturn(null);

        Policy policy1 =policyService.getPolicy(policy.getPolicyName());

        assertThat(policy1).isNull();
    }

    /**
     * get all policies should return a list of policies or empty list
     */
    @Test
    public void getAllPolicies_willReturnList() {
        given(policyRepository.findAll()).willReturn(emptyList());

        Iterable<Policy> policies = policyService.getAllPolicies();

        assertThat(policies).isEmpty();
    }

    /**
     * add policy should return newly added policy
     */
    @Test
    public void addPolicy_willReturnPolicy() {
        given(policyRepository.save(policy)).willReturn(policy);

        Policy policy1 = policyService.addPolicy(policy);

        assertThat(policy1.getPolicyName()).isEqualTo(policy.getPolicyName());

        verify(policyRepository).save(policy);
    }

    /**
     * delete policy will delete if it is found in database
     */
    @Test
    public void deletePolicy_willReturnSuccess() {
        given(policyRepository.findById(1L)).willReturn(Optional.of(policy));
        policyService.deletePolicy(1L);

        verify(policyRepository).findById(1L);
        verify(policyRepository).deleteById(1L);
    }

    /**
     * delete policy will throw exception if not found in database
     * @throws Exception PolicyNotFoundException
     */
    @Test(expected = PolicyNotFoundException.class)
    public void deletePolicy_willThrowNotFound() throws Exception {
        given(policyRepository.findById(1L)).willReturn(Optional.empty());
        policyService.deletePolicy(policy.getPolicyNo());

        verify(policyRepository).findById(1L);
    }

    /**
     * modify policy will modify and return the modified policy
     */
    @Test
    public void modifyPolicy_willReturnPolicy() {
        given(policyRepository.findById(1L)).willReturn(Optional.of(policy));
        policyService.modifyPolicy(policy);

        verify(policyRepository).findById(1L);
        verify(policyRepository).save(policy);

    }

    /**
     * modify policy will throw exception if the policy is not found
     * @throws Exception
     */
    @Test(expected = PolicyNotFoundException.class)
    public void modifyPolicy_willThrowException() throws Exception {
        given(policyRepository.findById(1L)).willReturn(Optional.empty());
        policyService.modifyPolicy(policy);
        verify(policyRepository).findById(1L);

    }
}