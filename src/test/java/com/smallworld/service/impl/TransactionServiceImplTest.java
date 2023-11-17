package com.smallworld.service.impl;

import com.smallworld.model.Transaction;
import com.smallworld.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionService transactionService;

    private TransactionServiceImpl transactionServiceImpl;

    @BeforeEach
    void setUp() {

        transactionService = mock(TransactionService.class);

        transactionServiceImpl = new TransactionServiceImpl();
    }

    @Test
    void whenGettingTotalTransactions_thenReturnList() {
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary1").amount(100.0).build(),
                Transaction.builder().senderFullName("Sender2").beneficiaryFullName("Beneficiary2").amount(150.0).build()
        );
        transactionServiceImpl.setTransactionsList(transactions);

        List<Transaction> result = transactionServiceImpl.getTotalTransactions();

        assertEquals(transactions, result);
    }

    @Test
    void whenSettingTransactionsList_thenListIsSet() {
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary1").amount(100.0).build(),
                Transaction.builder().senderFullName("Sender2").beneficiaryFullName("Beneficiary2").amount(150.0).build()
        );

        transactionServiceImpl.setTransactionsList(transactions);

        assertEquals(transactions, transactionServiceImpl.getTotalTransactions());
    }

    @Test
    void whenGettingTotalTransactionAmount_thenReturnTotalAmount() {
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary1").amount(100.0).build(),
                Transaction.builder().senderFullName("Sender2").beneficiaryFullName("Beneficiary2").amount(150.0).build()
        );
        transactionServiceImpl.setTransactionsList(transactions);

        double result = transactionServiceImpl.getTotalTransactionAmount();

        assertEquals(250.0, result, 0.01);
    }

    @Test
    void whenGettingTotalTransactionAmountSentBy_thenReturnTotalAmountSentBySender() {

        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary1").amount(100.0).build(),
                Transaction.builder().senderFullName("Sender2").beneficiaryFullName("Beneficiary2").amount(150.0).build(),
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary3").amount(50.0).build()
        );
        transactionServiceImpl.setTransactionsList(transactions);

        double result = transactionServiceImpl.getTotalTransactionAmountSentBy("Sender1");

        assertEquals(150.0, result, 0.01);
    }

    @Test
    void whenGettingMaxTransactionAmount_thenReturnMaxAmount() {
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary1").amount(100.0).build(),
                Transaction.builder().senderFullName("Sender2").beneficiaryFullName("Beneficiary2").amount(150.0).build(),
                Transaction.builder().senderFullName("Sender3").beneficiaryFullName("Beneficiary3").amount(200.0).build()
        );
        transactionServiceImpl.setTransactionsList(transactions);

        double result = transactionServiceImpl.getMaxTransactionAmount();

        assertEquals(200.0, result, 0.01);
    }

    @Test
    void whenGettingTransactionsByBeneficiaryName_thenReturnMapOfLists() {
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary1").amount(100.0).build(),
                Transaction.builder().senderFullName("Sender2").beneficiaryFullName("Beneficiary2").amount(150.0).build(),
                Transaction.builder().senderFullName("Sender3").beneficiaryFullName("Beneficiary1").amount(200.0).build()
        );
        transactionServiceImpl.setTransactionsList(transactions);

        Map<String, List<Transaction>> result = transactionServiceImpl.getTransactionsByBeneficiaryName();

        assertEquals(2, result.get("Beneficiary1").size());
        assertEquals(1, result.get("Beneficiary2").size());
    }

    @Test
    void whenGettingTop3TransactionsByAmount_thenReturnList() {
        List<Transaction> transactions = Arrays.asList(
                Transaction.builder().senderFullName("Sender1").beneficiaryFullName("Beneficiary1").amount(100.0).build(),
                Transaction.builder().senderFullName("Sender2").beneficiaryFullName("Beneficiary2").amount(150.0).build(),
                Transaction.builder().senderFullName("Sender3").beneficiaryFullName("Beneficiary3").amount(200.0).build(),
                Transaction.builder().senderFullName("Sender4").beneficiaryFullName("Beneficiary4").amount(50.0).build()
        );
        transactionServiceImpl.setTransactionsList(transactions);

        List<Transaction> result = transactionServiceImpl.getTop3TransactionsByAmount();

        assertEquals(3, result.size());
        assertEquals(200.0, result.get(0).getAmount(), 0.01);
        assertEquals(150.0, result.get(1).getAmount(), 0.01);
        assertEquals(100.0, result.get(2).getAmount(), 0.01);
    }
}
