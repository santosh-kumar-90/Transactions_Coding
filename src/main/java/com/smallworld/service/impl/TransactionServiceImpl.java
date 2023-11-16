package com.smallworld.service.impl;

import com.smallworld.model.Transaction;
import com.smallworld.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public void setTransactionsList(List<Transaction> transactionsList) {

    }

    @Override
    public double getTotalTransactionAmount() {
        return 0;
    }

    @Override
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        return 0;
    }

    @Override
    public double getMaxTransactionAmount() {
        return 0;
    }

    @Override
    public Map<String, Object> getTransactionsByBeneficiaryName() {
        return null;
    }

    @Override
    public List<Object> getTop3TransactionsByAmount() {
        return null;
    }
}
