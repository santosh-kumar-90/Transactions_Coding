package com.smallworld.service.impl;

import com.smallworld.model.Issue;
import com.smallworld.model.Transaction;
import com.smallworld.service.ClientService;
import com.smallworld.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final TransactionService transactionService;
    @Override
    public long countUniqueClients() {
        return transactionService.getTotalTransactions()
                .stream()
                .map(Transaction::getSenderFullName)
                .distinct()
                .count();
    }

    @Override
    public Optional<String> getTopSender() {
        return transactionService.getTotalTransactions().stream()
                .collect(Collectors.groupingBy(Transaction::getSenderFullName, Collectors.summingDouble(Transaction::getAmount)))
                .entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey);
    }

    @Override
    public boolean hasOpenComplianceIssues(String clientFullName) {
       return transactionService.getTotalTransactions().stream()
                .anyMatch(transaction -> hasOpenIssue(transaction, clientFullName));
    }
    private boolean hasOpenIssue(Transaction transaction, String clientFullName) {
        if (transaction != null &&
                (clientFullName.equals(transaction.getSenderFullName()) ||
                        clientFullName.equals(transaction.getBeneficiaryFullName()))) {
            List<Issue> issues = transaction.getIssues();
            if (issues != null) {
                return issues.stream()
                        .anyMatch(issue -> issue != null &&
                                issue.getIssueId() != null &&
                                !issue.getIssueSolved());
            }
        }
        return false;
    }

}
