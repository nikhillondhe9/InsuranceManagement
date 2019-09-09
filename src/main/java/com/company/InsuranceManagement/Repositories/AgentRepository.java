/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 13:35
 */
package com.company.InsuranceManagement.Repositories;

import com.company.InsuranceManagement.Models.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {
}
