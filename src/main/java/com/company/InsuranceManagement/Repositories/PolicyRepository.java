/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-12
 * Time: 13:54
 */
package com.company.InsuranceManagement.Repositories;

import com.company.InsuranceManagement.Models.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Policy Repository to do CRUD operations on database
 */
@Repository
public interface PolicyRepository extends CrudRepository<Policy, Long> {
    Policy getPolicyByPolicyName(String policyName);
}
