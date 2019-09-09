/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-12
 * Time: 12:38
 */
package com.company.InsuranceManagement.Services;

import com.company.InsuranceManagement.Exps.PolicyNotFoundException;
import com.company.InsuranceManagement.Models.Policy;
import com.company.InsuranceManagement.Repositories.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Policy Service to interact with the JPA repository
 */
@Service
public class PolicyService {

    private PolicyRepository policyRepository;

    /**
     * Constructor
     * @param policyRepository
     */
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    /**
     * get policy by policy name
     * @param policyName
     * @return
     * @throws PolicyNotFoundException
     */
    public Policy getPolicy(String policyName) throws PolicyNotFoundException {
       Policy policy = policyRepository.getPolicyByPolicyName(policyName);
       if (policy == null)
           throw new PolicyNotFoundException();
       return policy;
    }

    /**
     * add policy will return the policy added
     * @param policy
     * @return
     */
    public Policy addPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    /**
     * delete policy will return success if policy is found
     * @param policyNo
     * @return
     * @throws PolicyNotFoundException
     */
    public String deletePolicy(Long policyNo) throws PolicyNotFoundException {
        Optional<Policy> policy = policyRepository.findById(policyNo);
        if (!policy.isPresent())
            throw new PolicyNotFoundException();
        policyRepository.deleteById(policyNo);
        return "success";
    }

    /**
     * modify policy will return modified policy, else throw exception
     * @param policy
     * @return
     * @throws PolicyNotFoundException
     */
    public Policy modifyPolicy(Policy policy) throws PolicyNotFoundException {
        if(!policyRepository.findById(policy.getPolicyNo()).isPresent())
            throw new PolicyNotFoundException();
        return policyRepository.save(policy);
    }

    /**
     * get all policies will return policies list
     * @return
     */
    public Iterable<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}
