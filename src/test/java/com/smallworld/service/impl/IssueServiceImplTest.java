package com.smallworld.service.impl;

import com.smallworld.model.Issue;
import com.smallworld.model.Transaction;
import com.smallworld.service.IssueService;
import com.smallworld.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IssueServiceImplTest {
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private IssueServiceImpl issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSolvedIssueMessages_givenTransactionsWithSolvedIssues_thenReturnSolvedMessages() {

        List<Transaction> transactions = Arrays.asList(
                createTransaction("Sender1", "Beneficiary1", 100.0, createIssue(1, true, "Issue 1")),
                createTransaction("Sender2", "Beneficiary2", 150.0, createIssue(2, false, "Issue 2")),
                createTransaction("Sender3", "Beneficiary3", 200.0, createIssue(3, true, "Issue 3"))
        );

        when(transactionService.getTotalTransactions()).thenReturn(transactions);

        List<String> result = issueService.getAllSolvedIssueMessages();

        assertEquals(Arrays.asList("Issue 1", "Issue 3"), result);
    }

    @Test
    void getUnsolvedIssueIds_givenTransactionsWithUnsolvedIssues_thenReturnUnsolvedIds() {
        // Given
        List<Transaction> transactions = Arrays.asList(
                createTransaction("Sender1", "Beneficiary1", 100.0, createIssue(1, true, "Issue 1")),
                createTransaction("Sender2", "Beneficiary2", 150.0, createIssue(2, false, "Issue 2")),
                createTransaction("Sender3", "Beneficiary3", 200.0, createIssue(3, true, "Issue 3"))
        );

        when(transactionService.getTotalTransactions()).thenReturn(transactions);

        Set<Integer> result = issueService.getUnsolvedIssueIds();

        assertEquals(Set.of(2), result);
    }

    private Transaction createTransaction(String senderFullName, String beneficiaryFullName, double amount, Issue... issues) {
        return Transaction.builder()
                .senderFullName(senderFullName)
                .beneficiaryFullName(beneficiaryFullName)
                .amount(amount)
                .issues(List.of(issues))
                .build();
    }

    private Issue createIssue(Integer issueId, boolean issueSolved, String issueMessage) {
        return Issue
                .builder()
                .issueId(issueId)
                .issueMessage(issueMessage)
                .issueSolved(issueSolved)
                .build();
    }
}
