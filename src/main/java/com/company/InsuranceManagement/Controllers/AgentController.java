/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 2019-08-13
 * Time: 13:16
 */
package com.company.InsuranceManagement.Controllers;

import com.company.InsuranceManagement.Exps.AgentNotFoundException;
import com.company.InsuranceManagement.Models.Agent;
import com.company.InsuranceManagement.Services.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AgentController {

    private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping(path = "/agent/{id}")
    public ResponseEntity<Agent> getAgent(@PathVariable Long id) throws AgentNotFoundException {
        return new ResponseEntity<>(agentService.getAgent(id), HttpStatus.OK);
    }

    @GetMapping(path = "/agent/all")
    public ResponseEntity<Iterable<Agent>> getAllAgents() {
        return new ResponseEntity<>(agentService.getAllAgents(), HttpStatus.OK);
    }

    @PostMapping(path = "/agent")
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
        return new ResponseEntity<>(agentService.addAgent(agent), HttpStatus.CREATED);
    }

    @PutMapping(path = "/agent")
    public ResponseEntity<Agent> modifyAgent(@RequestBody Agent agent) throws AgentNotFoundException {
        return new ResponseEntity<>(agentService.modifyAgent(agent), HttpStatus.OK);
    }

    @DeleteMapping(path = "/agent/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable Long id) throws AgentNotFoundException {
        return new ResponseEntity<>(agentService.deleteAgent(id), HttpStatus.GONE);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void agentNotFoundExceptionHandler(AgentNotFoundException e) {}
}
