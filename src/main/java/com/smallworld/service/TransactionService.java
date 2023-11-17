package com.smallworld.service;

import com.smallworld.model.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    List<Transaction> getTotalTransactions();
    void setTransactionsList(List<Transaction> transactionsList);
    double getTotalTransactionAmount();
    double getTotalTransactionAmountSentBy(String senderFullName);
    double getMaxTransactionAmount();
    Map<String, List<Transaction>> getTransactionsByBeneficiaryName();
    List<Transaction> getTop3TransactionsByAmount();
}
