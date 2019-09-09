package com.company.InsuranceManagement.Repositories;

import com.company.InsuranceManagement.Models.PolicyHolder;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PolicyHolderRepositoryTest {

    private static final PolicyHolder policyHolder = new PolicyHolder(1L, "John", "john@example.com",
            "12QWas", "male", 1234566L, "BofA", "blah blah blah");

    @Autowired
    private PolicyHolderRepository policyHolderRepository;

    @After
    public void tearDown() {
        policyHolderRepository.deleteAll();
    }

    @Test
    public void findById_willReturnPolicyHolder() throws Exception {
        PolicyHolder policyHolderr = policyHolderRepository.save(policyHolder);

        Optional<PolicyHolder> policyHolder1 = policyHolderRepository.findById(policyHolderr.getId());

        assertThat(policyHolder1).isNotEmpty();
        assertThat(policyHolder1.get().getName()).isEqualTo(policyHolder.getName());
    }

    @Test
    public void findAll_willReturnList() throws Exception {
        Iterable<PolicyHolder> policyHolders = policyHolderRepository.findAll();

        assertThat(policyHolders).isEmpty();
    }

    @Test
    public void save_willReturnPolicyHolder() throws Exception {
        PolicyHolder policyHolder1 = policyHolderRepository.save(policyHolder);

        assertThat(policyHolder1.getName()).isEqualTo(policyHolder.getName());
    }
}