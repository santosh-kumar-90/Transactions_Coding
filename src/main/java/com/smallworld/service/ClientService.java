package com.smallworld.service;

import java.util.Optional;

public interface ClientService {
    long countUniqueClients();
    Optional<String> getTopSender();
    boolean hasOpenComplianceIssues(String clientFullName);
}
