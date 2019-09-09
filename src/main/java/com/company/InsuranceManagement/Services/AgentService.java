/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 13:21
 */
package com.company.InsuranceManagement.Services;

import com.company.InsuranceManagement.Exps.AgentNotFoundException;
import com.company.InsuranceManagement.Models.Agent;
import com.company.InsuranceManagement.Repositories.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

    private AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent getAgent(long id) throws AgentNotFoundException {
        Optional<Agent> agent = agentRepository.findById(id);
        if(!agent.isPresent()) {
            throw new AgentNotFoundException();
        }
        return agent.get();
    }

    public Iterable<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent addAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent modifyAgent(Agent agent) throws AgentNotFoundException {
        if(!agentRepository.findById(agent.getId()).isPresent())
            throw new AgentNotFoundException();
        return agentRepository.save(agent);
    }

    public String deleteAgent(long id) throws AgentNotFoundException {
        if(!agentRepository.findById(id).isPresent())
            throw new AgentNotFoundException();
        agentRepository.deleteById(id);
        return "success";
    }
}
