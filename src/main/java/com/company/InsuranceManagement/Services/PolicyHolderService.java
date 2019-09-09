/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 16:11
 */
package com.company.InsuranceManagement.Services;

import com.company.InsuranceManagement.Exps.PolicyHolderNotFoundException;
import com.company.InsuranceManagement.Models.Agent;
import com.company.InsuranceManagement.Models.PolicyHolder;
import com.company.InsuranceManagement.Repositories.PolicyHolderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHolderService {

    private PolicyHolderRepository policyHolderRepository;

    public PolicyHolderService(PolicyHolderRepository policyHolderRepository) {
        this.policyHolderRepository = policyHolderRepository;
    }

    public PolicyHolder getPolicyHolder(long id) throws PolicyHolderNotFoundException {
        Optional<PolicyHolder> policyHolder = policyHolderRepository.findById(id);
        if(!policyHolder.isPresent())
            throw new PolicyHolderNotFoundException();
        return policyHolder.get();
    }

    public Iterable<PolicyHolder> getAllPolicies() {
        return policyHolderRepository.findAll();
    }

    public PolicyHolder addPolicyHolder(PolicyHolder policyHolder) {
        return policyHolderRepository.save(policyHolder);
    }
}
