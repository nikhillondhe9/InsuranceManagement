package com.company.InsuranceManagement.Services;

import com.company.InsuranceManagement.Exps.PolicyHolderNotFoundException;
import com.company.InsuranceManagement.Models.PolicyHolder;
import com.company.InsuranceManagement.Repositories.PolicyHolderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PolicyHolderServiceTest {

    private static final PolicyHolder policyHolder = new PolicyHolder(1L, "John", "john@example.com",
            "12QWas", "male", 1234566L, "BofA", "blah blah blah");

    private PolicyHolderService policyHolderService;

    @Mock
    PolicyHolderRepository policyHolderRepository;

    @Before
    public void setUp() {
        policyHolderService = new PolicyHolderService(policyHolderRepository);
    }

    @Test
    public void getPolicyHolder_willReturnPolicyHolder() throws Exception {
        given(policyHolderRepository.findById(1L)).willReturn(Optional.of(policyHolder));

       PolicyHolder policyHolder1 = policyHolderService.getPolicyHolder(1L);

        assertThat(policyHolder1).isNotNull();
        verify(policyHolderRepository).findById(1L);
    }

    @Test(expected = PolicyHolderNotFoundException.class)
    public void getPolicyHolder_willThrowNotFound() throws Exception {
        given(policyHolderRepository.findById(any())).willReturn(Optional.empty());

        PolicyHolder policyHolder1 = policyHolderService.getPolicyHolder(1L);

        assertThat(policyHolder1).isNull();
        verify(policyHolderRepository).findById(any());
    }

    @Test
    public void getAllPolicyHolders_willReturnList() throws Exception {
        given(policyHolderRepository.findAll()).willReturn(Collections.emptyList());

        Iterable<PolicyHolder> policyHolders= policyHolderService.getAllPolicies();

        assertThat(policyHolders).isEmpty();
        verify(policyHolderRepository).findAll();
    }

    @Test
    public void addPolicyHolder_willReturnAddedPolicy() throws Exception {
        given(policyHolderRepository.save(policyHolder)).willReturn(policyHolder);

        PolicyHolder policyHolder1 = policyHolderService.addPolicyHolder(policyHolder);

        assertThat(policyHolder1.getName()).isEqualTo(policyHolder.getName());
        assertThat(policyHolder1.getId()).isEqualTo(policyHolder.getId());
        verify(policyHolderRepository).save(any());
    }
}