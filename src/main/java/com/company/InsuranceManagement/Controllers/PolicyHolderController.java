/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 16:10
 */
package com.company.InsuranceManagement.Controllers;

import com.company.InsuranceManagement.Exps.PolicyHolderNotFoundException;
import com.company.InsuranceManagement.Models.Policy;
import com.company.InsuranceManagement.Models.PolicyHolder;
import com.company.InsuranceManagement.Services.PolicyHolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class PolicyHolderController {
    private PolicyHolderService policyHolderService;

    public PolicyHolderController(PolicyHolderService policyHolderService) {
        this.policyHolderService = policyHolderService;
    }

    @GetMapping(path = "/policyHolder/{id}")
    public ResponseEntity<PolicyHolder> getPolicyHolder(@PathVariable Long id) throws PolicyHolderNotFoundException {
        return new ResponseEntity<>(policyHolderService.getPolicyHolder(id), HttpStatus.OK);
    }

    @GetMapping(path = "/policyHolder/all")
    public ResponseEntity<Iterable<PolicyHolder>> getAllPolicies() {
        return new ResponseEntity<>(policyHolderService.getAllPolicies(), HttpStatus.OK);
    }

    @PostMapping(path = "/policyHolder")
    public ResponseEntity<PolicyHolder> addPolicy(@RequestBody PolicyHolder policyHolder) {
        return new ResponseEntity<>(policyHolderService.addPolicyHolder(policyHolder), HttpStatus.CREATED);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void PolicyHolderNotFoundExceptionHandler(PolicyHolderNotFoundException e) {}
}
