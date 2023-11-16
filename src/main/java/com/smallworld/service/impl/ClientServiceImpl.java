package com.smallworld.service.impl;

import com.smallworld.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public long countUniqueClients() {
        return 0;
    }

    @Override
    public Optional<String> getTopSender() {
        return Optional.empty();
    }

    @Override
    public boolean hasOpenComplianceIssues(String clientFullName) {
        return false;
    }
}
