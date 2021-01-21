package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.UpdateAgentRequest;
import com.example.monolitna.dto.response.AgentResponse;
import com.example.monolitna.entity.Agent;
import com.example.monolitna.repository.IAgentRepository;
import com.example.monolitna.repository.ISimpleUserRepository;
import com.example.monolitna.services.IAgentService;
import org.springframework.stereotype.Service;

@Service
public class AgentService implements IAgentService {

    private final IAgentRepository _agentRepository;
    private final ISimpleUserRepository _simpleUserRepository;

    public AgentService(IAgentRepository agentRepository, ISimpleUserRepository simpleUserRepository) {
        _agentRepository = agentRepository;
        _simpleUserRepository = simpleUserRepository;
    }

    @Override
    public AgentResponse getAgentById(Long id) {
        Agent agent = _agentRepository.findOneById(id);
        if(agent != null){
            return mapAgentToResponse(agent);
        }
        else{
            return null;
        }
    }

    @Override
    public void updateAgentById(Long id, UpdateAgentRequest request) {
        Agent agent = _agentRepository.findOneById(id);
        if(request.getName() != null)
            agent.setName(request.getName());
        if(request.getPib() != null)
            agent.setPib(request.getPib());
        if(request.getBankAccountNumber() != null)
            agent.setBankAccountNumber(request.getBankAccountNumber());
        if(request.getAddress() != null)
            agent.setAddress(request.getAddress());

        _agentRepository.save(agent);
    }

    private AgentResponse mapAgentToResponse(Agent agent){
        AgentResponse agentResponse = new AgentResponse();
        agentResponse.setId(agent.getId());
        agentResponse.setUsername(agent.getUser().getUsername());
        agentResponse.setUserRole(agent.getUser().getUserRole().toString());
        agentResponse.setName(agent.getName());
        agentResponse.setPib(agent.getPib());
        agentResponse.setBankAccountNumber(agent.getBankAccountNumber());
        agentResponse.setAddress(agent.getAddress());
        agentResponse.setDateFounded(agent.getDateFounded());
        return agentResponse;
    }
}
