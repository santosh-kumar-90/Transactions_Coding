package com.smallworld.service.impl;

import com.smallworld.model.Issue;
import com.smallworld.model.Transaction;
import com.smallworld.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCountingUniqueClients_givenTransactionsWithUniqueSenders_thenCountUniqueSenders() {

        List<Transaction> transactions = Arrays.asList(
                createTransaction("Sender1", "Beneficiary1", 100.0),
                createTransaction("Sender2", "Beneficiary2", 150.0),
                createTransaction("Sender3", "Beneficiary3", 200.0),
                createTransaction("Sender4", "Beneficiary4", 50.0)
        );

        when(transactionService.getTotalTransactions()).thenReturn(transactions);

        long result = clientService.countUniqueClients();

        assertEquals(4, result);
    }

    @Test
    void whenGettingTopSender_givenTransactions_thenReturnTopSender() {
        List<Transaction> transactions = Arrays.asList(
                createTransaction("Sender1", "Beneficiary1", 100.0),
                createTransaction("Sender2", "Beneficiary2", 150.0),
                createTransaction("Sender3", "Beneficiary3", 200.0),
                createTransaction("Sender4", "Beneficiary4", 50.0)
        );
        when(transactionService.getTotalTransactions()).thenReturn(transactions);

        Optional<String> result = clientService.getTopSender();

        assertTrue(result.isPresent());
        assertEquals("Sender3", result.get());
    }

    @Test
    void whenCheckingOpenComplianceIssues_givenTransactionsAndClientWithOpenIssue_thenReturnTrue() {
        List<Transaction> transactions = new ArrayList<>(Arrays.asList(
                createTransaction("Sender1", "Beneficiary1", 100.0),
                createTransaction("Sender2", "Beneficiary2", 150.0),
                createTransaction("Sender3", "Beneficiary3", 200.0),
                createTransaction("Sender4", "Beneficiary4", 50.0)
        ));
        Transaction transactionWithOpenIssue = createTransactionWithOpenIssue("Sender1", "Sender1", 1000);
        transactions.add(transactionWithOpenIssue);

        when(transactionService.getTotalTransactions()).thenReturn(transactions);

        boolean result = clientService.hasOpenComplianceIssues("Sender1");

        assertTrue(result);
    }


    @Test
    void whenCheckingOpenComplianceIssues_givenTransactionsAndClientWithoutOpenIssue_thenReturnFalse() {
        List<Transaction> transactions = Arrays.asList(
                createTransaction("Sender1", "Beneficiary1", 100.0),
                createTransaction("Sender2", "Beneficiary2", 150.0),
                createTransaction("Sender3", "Beneficiary3", 200.0),
                createTransaction("Sender4", "Beneficiary4", 50.0)
        );
        when(transactionService.getTotalTransactions()).thenReturn(transactions);

        boolean result = clientService.hasOpenComplianceIssues("Sender5");

        assertFalse(result);
    }

    private Transaction createTransaction(String senderFullName, String beneficiaryFullName, double amount) {

      return Transaction.builder()
                .senderFullName(senderFullName)
                .beneficiaryFullName(beneficiaryFullName)
                .amount(amount)
                .build();

    }

    private Transaction createTransactionWithOpenIssue(String senderFullName, String beneficiaryFullName, double amount) {
        Transaction transaction = createTransaction(senderFullName, beneficiaryFullName, amount);
        transaction.setIssues(List.of(createOpenIssue()));
        return transaction;
    }

    private Issue createOpenIssue() {
        Issue issue = new Issue();
        issue.setIssueId(1);
        issue.setIssueSolved(false);
        return issue;
    }
}
