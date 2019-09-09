package com.company.InsuranceManagement.Repositories;

import com.company.InsuranceManagement.Models.Agent;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AgentRepositoryTest {

    private static final Agent agent = new Agent(1L, "John", "John@example.com", "12QWaszx",23, 123456L,"BofA");
    private static final Agent agent2 = new Agent(123L, "John", "John@example.com", "12QWaszx",23, 123456L,"BofA");

    @Autowired
    AgentRepository agentRepository;

    @After
    public void tearDown() {
        agentRepository.deleteAll();
    }

    @Test
    public void findById_willReturnAgent() {
        Agent agentt = agentRepository.save(agent);

        Optional<Agent> agent1 = agentRepository.findById(agentt.getId());

        assertThat(agent1).isNotEmpty() ;
        assertThat(agent1.get().getName()).isEqualTo(agent.getName());
    }

    @Test
    public void findById_willReturnEmpty() {
        Optional<Agent> agent1 = agentRepository.findById(1L);

        assertThat(agent1).isEmpty();
    }

    @Test
    public void findAll_willReturnList() throws Exception {
        Iterable<Agent> agents = agentRepository.findAll();

        assertThat(agents).isEmpty();
    }

    @Test
    public void save_willReturnAgent() throws Exception {
        Agent agent1 = agentRepository.save(agent);

        assertThat(agent1.getName()).isEqualTo(agent.getName());
    }

    @Test
    public void deleteById_willReturnNull() throws Exception {
        Agent agentt = agentRepository.save(agent2);
        agentRepository.deleteById(agentt.getId());

        Iterable<Agent> agents = agentRepository.findAll();

        assertThat(agents).doesNotContain(agentt);
    }
}