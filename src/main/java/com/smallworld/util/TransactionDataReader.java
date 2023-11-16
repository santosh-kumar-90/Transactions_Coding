package com.smallworld.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.exception.CustomException;
import com.smallworld.exception.TransactionErrors;
import com.smallworld.model.Transaction;
import com.smallworld.service.TransactionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static com.smallworld.exception.TransactionErrors.TRANSACTION_FILE_NOT_LOADED;

@RequiredArgsConstructor
@Slf4j
@Component
public class TransactionDataReader {

    @Value("classpath:${transaction.file.location}")
    private Resource transactionFileData;

    private final TransactionService transactionService;


    public List<Transaction> readTransactionsData() {
        try {
            InputStream inputStream = transactionFileData.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(reader, new TypeReference<>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    @PostConstruct
    public void setTransactions() throws CustomException {

       try {
           transactionService.setTransactionsList(readTransactionsData());
           log.info("Transaction file loaded successfully");
       }
       catch (Exception e){
           log.error("Transactions file could not be loaded!");
           throw TRANSACTION_FILE_NOT_LOADED.asException();
       }

    }
}
