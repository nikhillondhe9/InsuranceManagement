package com.company.InsuranceManagement.Services;

import com.company.InsuranceManagement.Exps.AgentNotFoundException;
import com.company.InsuranceManagement.Models.Agent;
import com.company.InsuranceManagement.Repositories.AgentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AgentServiceTest {

    private static final Agent agent = new Agent(1L, "John", "John@example.com", "12QWaszx",23, 123456L,"BofA");

    private AgentService agentService;

    @Mock
    private AgentRepository agentRepository;

    @Before
    public void setUp() {
        agentService = new AgentService(agentRepository);
    }

    @Test
    public void getAgent_returnsAgent() throws Exception {
        given(agentRepository.findById(1L)).willReturn(Optional.of(agent));

        Agent agent1 = agentService.getAgent(1L);

        assertThat(agent1.getAccountNo()).isEqualTo(agent.getAccountNo());
        assertThat(agent1.getName()).isEqualTo(agent.getName());
    }

    @Test(expected = AgentNotFoundException.class)
    public void getAgent_throwsNotFound() throws AgentNotFoundException {
        given(agentRepository.findById(any())).willReturn(Optional.empty());

        Agent agent1 = agentService.getAgent(1L);

        assertThat(agent1).isNull();
    }

    @Test
    public void getAllAgents_willReturnList() throws Exception {
        given(agentRepository.findAll()).willReturn(Collections.emptyList());

        Iterable<Agent> agents = agentService.getAllAgents();

        assertThat(agents).isEmpty();
        verify(agentRepository).findAll();
    }

    @Test
    public void addAgent_willReturnAgent() throws Exception {
        given(agentRepository.save(agent)).willReturn(agent);

        Agent agent1 = agentService.addAgent(agent);

        assertThat(agent1.getName()).isEqualTo(agent.getName());
        verify(agentRepository).save(agent);
    }

    @Test
    public void modifyAgent_willReturnAgent() throws Exception {
        given(agentRepository.findById(agent.getId())).willReturn(Optional.of(agent));
        agentService.modifyAgent(agent);

        verify(agentRepository).findById(agent.getId());
        verify(agentRepository).save(agent);
    }

    @Test(expected = AgentNotFoundException.class)
    public void modifyAgent_willThrowNotFound() throws Exception {
        given(agentRepository.findById(agent.getId())).willReturn(Optional.empty());
        agentService.modifyAgent(agent);
        verify(agentRepository).findById(agent.getId());
    }

    @Test
    public void deleteAgent_willReturnSuccess() throws Exception {
        given(agentRepository.findById(1L)).willReturn(Optional.of(agent));
        String result = agentService.deleteAgent(1L);

        assertThat(result).isEqualTo("success");

        verify(agentRepository).findById(1L);
        verify(agentRepository).deleteById(1L);
    }

    @Test(expected = AgentNotFoundException.class)
    public void deleteAgent_willThrowNotFound() throws AgentNotFoundException {
        given(agentRepository.findById(1L)).willReturn(Optional.empty());
        agentService.deleteAgent(1L);

        verify(agentRepository).findById(1L);
    }
}