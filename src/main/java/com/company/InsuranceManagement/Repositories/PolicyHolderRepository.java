/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 16:23
 */
package com.company.InsuranceManagement.Repositories;

import com.company.InsuranceManagement.Models.PolicyHolder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyHolderRepository extends CrudRepository<PolicyHolder, Long> {
}
