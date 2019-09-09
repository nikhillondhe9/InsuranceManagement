/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-12
 * Time: 12:45
 */
package com.company.InsuranceManagement.Controllers;

import com.company.InsuranceManagement.Exps.PolicyNotFoundException;
import com.company.InsuranceManagement.Models.Policy;
import com.company.InsuranceManagement.Services.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Policy Rest Controller APIs
 */
@RestController
@RequestMapping("/api")
public class PolicyController {

    private PolicyService policyService;
    /**
     * Constructor
     * @param policyService
     */
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }
    /**
     * Returns policy with policyName
     * @param policyName
     * @return policy from database
     * @throws PolicyNotFoundException if policy is not found
     */
    @GetMapping(path = "/policy/{policyName}")
    public ResponseEntity<Policy> getPolicy(@PathVariable String policyName) throws PolicyNotFoundException {
        return new ResponseEntity<>(policyService.getPolicy(policyName), HttpStatus.OK);
    }
    /**
     * Returns all policies in the database
     * @return list of policies
     */
    @GetMapping(path = "/policy/all")
    public ResponseEntity<Iterable<Policy>> getAllPolicy() {
        return new ResponseEntity<>(policyService.getAllPolicies(), HttpStatus.OK);
    }
    /**
     * Adds policy to the database
     * @param policy must not be {@literal null}
     * @return newly added policy
     */
    @PostMapping(path = "/policy")
    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
        return new ResponseEntity<>(policyService.addPolicy(policy), HttpStatus.CREATED);
    }

    /**
     * deletes the policy by it's id
     * @param policyNo cannot be {@literal null}
     * @return status OK
     * @throws PolicyNotFoundException if policy is not found
     */
    @DeleteMapping(path = "/policy/{policyNo}")
    public ResponseEntity<String> deletePolicy(@PathVariable Long policyNo) throws PolicyNotFoundException {
        return new ResponseEntity<>(policyService.deletePolicy(policyNo), HttpStatus.OK);
    }

    /**
     * updates the already existing policy. The policy passed in request body should be present in database
     * @param policy cannot be {@literal null}
     * @return updated policy
     * @throws PolicyNotFoundException if policy is not found
     */
    @PutMapping(path = "/policy")
    public ResponseEntity<Policy> updatePolicy(@RequestBody Policy policy) throws PolicyNotFoundException {
        return new ResponseEntity<>(policyService.modifyPolicy(policy), HttpStatus.OK);
    }

    /**
     * Exception handler for policy bot found, which will return Http Status 404
     * @param e thrown Exception should be PolicyNotFoundException
     */
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void policyNotFoundExceptionHandler(PolicyNotFoundException e) {}
}