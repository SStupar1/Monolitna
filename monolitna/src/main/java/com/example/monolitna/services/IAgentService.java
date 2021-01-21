package com.example.monolitna.services;

import com.example.monolitna.dto.request.UpdateAgentRequest;
import com.example.monolitna.dto.response.AgentResponse;

public interface IAgentService {
    AgentResponse getAgentById(Long id);

    void updateAgentById(Long id, UpdateAgentRequest request);
}
