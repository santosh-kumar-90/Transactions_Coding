package com.smallworld.service.impl;

import com.smallworld.model.Transaction;
import com.smallworld.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    public static List<Transaction> TRANSACTIONS_LIST;
    @Override
    public void setTransactionsList(List<Transaction> transactionsList) {
        TRANSACTIONS_LIST = transactionsList;
    }

    @Override
    public double getTotalTransactionAmount() {
        return TRANSACTIONS_LIST.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        return TRANSACTIONS_LIST.stream()
                .filter(transaction -> senderFullName.equals(transaction.getSenderFullName()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getMaxTransactionAmount() {
        return TRANSACTIONS_LIST.stream()
                .mapToDouble(Transaction::getAmount)
                .max()
                .orElse(0);
    }

    @Override
    public Map<String, List<Transaction>> getTransactionsByBeneficiaryName() {
        return TRANSACTIONS_LIST
                .stream()
                .collect(Collectors.groupingBy(Transaction::getBeneficiaryFullName));
    }

    @Override
    public List<Transaction> getTop3TransactionsByAmount() {
        return TRANSACTIONS_LIST
                .stream()
                .sorted((transaction1, transaction2) -> Double.compare(transaction2.getAmount(), transaction1.getAmount()))
                .limit(3).toList();
    }
}
