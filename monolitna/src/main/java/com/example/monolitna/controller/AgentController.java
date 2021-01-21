package com.example.monolitna.controller;

import com.example.monolitna.dto.request.UpdateAgentRequest;
import com.example.monolitna.dto.response.AgentResponse;
import com.example.monolitna.services.IAgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final IAgentService _agentService;

    public AgentController(IAgentService agentService) {
        _agentService = agentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgent(@PathVariable("id") Long id){
        AgentResponse agentResponse = _agentService.getAgentById(id);
        if(agentResponse != null){
            return new ResponseEntity<>(agentResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Agent doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updateAgent(@PathVariable("id")Long id, @RequestBody UpdateAgentRequest request){
        _agentService.updateAgentById(id, request);
    }

}