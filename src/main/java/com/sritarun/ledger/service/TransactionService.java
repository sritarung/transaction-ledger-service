package com.sritarun.ledger.service;

import com.sritarun.ledger.api.dto.CreateTransactionRequest;
import com.sritarun.ledger.api.dto.TransactionResponse;
import com.sritarun.ledger.domain.Account;
import com.sritarun.ledger.domain.AccountTransaction;
import com.sritarun.ledger.domain.TransactionType;
import com.sritarun.ledger.repository.AccountRepository;
import com.sritarun.ledger.repository.AccountTransactionRepository;
import com.sritarun.ledger.service.exception.InsufficientFundsException;
import com.sritarun.ledger.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final AccountTransactionRepository transactionRepository;

    @Transactional
    public TransactionResponse createTransaction(String accountExternalId, CreateTransactionRequest request) {
        Account account = accountRepository.findByExternalId(accountExternalId)
                .orElseThrow(() -> new NotFoundException("Account not found: " + accountExternalId));

        BigDecimal amount = request.getAmount();

        if (request.getType() == TransactionType.DEBIT) {
            if (account.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient balance");
            }
            account.setBalance(account.getBalance().subtract(amount));
        } else {
            account.setBalance(account.getBalance().add(amount));
        }

        AccountTransaction tx = AccountTransaction.builder()
                .account(account)
                .type(request.getType())
                .amount(amount)
                .description(request.getDescription())
                .build();

        AccountTransaction savedTx = transactionRepository.save(tx);
        accountRepository.save(account);

        return toResponse(savedTx);
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> listTransactions(String accountExternalId) {
        return transactionRepository.findByAccount_ExternalIdOrderByCreatedAtDesc(accountExternalId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse toResponse(AccountTransaction tx) {
        return TransactionResponse.builder()
                .id(tx.getExternalId())
                .accountId(tx.getAccount().getExternalId())
                .type(tx.getType())
                .amount(tx.getAmount())
                .description(tx.getDescription())
                .createdAt(tx.getCreatedAt())
                .build();
    }
}
