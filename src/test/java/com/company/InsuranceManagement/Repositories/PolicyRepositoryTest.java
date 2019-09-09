package com.company.InsuranceManagement.Repositories;

import com.company.InsuranceManagement.Models.Policy;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Policy Repository test
 * Used DataJpaTest and SpringRunner
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PolicyRepositoryTest {

    private static final Policy policy = new Policy("Motor Insurance");

    @Autowired
    PolicyRepository policyRepository;

    @After
    public void tearDown() {
        policyRepository.deleteAll();
    }

    /**
     * get policy should return policy by name
     */
    @Test
    public void getPolicy_returnsPolicy() {
        policyRepository.save(policy);

        Policy repoPolicy = policyRepository.getPolicyByPolicyName(policy.getPolicyName());
        assertThat(repoPolicy.getPolicyName()).isEqualTo(policy.getPolicyName());
    }

    /**
     * find all policies should return list of policies
     */
    @Test
    public void findAll_willReturnIterable() {
        policyRepository.save(policy);

        Iterable<Policy> policies= policyRepository.findAll();

        assertThat(policies).hasSize(1);
    }

    /**
     * add policy should return added policy
     */
    @Test
    public void addPolicy_returnsPolicy() {
        Policy policy1 = policyRepository.save(policy);

        assertThat(policy1.getPolicyName()).isEqualTo(policy.getPolicyName());
    }

    /**
     * delete policy should delete policy and return void
     */
    @Test
    public void deletePolicy_deletesPolicy() {
        Policy policy1 = policyRepository.save(new Policy("Motor Insurance"));

        policyRepository.deleteById(policy1.getPolicyNo());

        assertThat(policyRepository.existsById(policy1.getPolicyNo())).isFalse();
    }
}