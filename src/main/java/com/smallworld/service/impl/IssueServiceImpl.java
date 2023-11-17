package com.smallworld.service.impl;

import com.smallworld.model.Issue;
import com.smallworld.service.IssueService;
import com.smallworld.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final TransactionService transactionService;

    @Override
    public List<String> getAllSolvedIssueMessages() {
       return transactionService.getTotalTransactions()
               .stream()
               .flatMap(transaction -> transaction.getIssues().stream()
               .filter(issue -> issue.getIssueId() != null && issue.getIssueSolved())
               .map(Issue::getIssueMessage)).toList();
    }
    @Override
    public Set<Integer> getUnsolvedIssueIds() {
        return transactionService.getTotalTransactions()
                .stream()
                .flatMap(transaction -> transaction.getIssues().stream()
                .filter(issue -> issue.getIssueId() != null && !issue.getIssueSolved())
                .map(Issue::getIssueId))
                .collect(Collectors.toSet());
    }
}
