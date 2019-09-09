/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-12
 * Time: 12:41
 */
package com.company.InsuranceManagement.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Policy model for database
 */
@Entity
public class Policy {

    @Id
    @GeneratedValue
    private Long policyNo;
    private String policyName;

    public Policy() {
    }

    public Policy(Long policyNo, String policyName) {
        this.policyNo = policyNo;
        this.policyName = policyName;
    }

    public Policy(String policyName) {
        this.policyName = policyName;
    }

    public Long getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(Long policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
}
