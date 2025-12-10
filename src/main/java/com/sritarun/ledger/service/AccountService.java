package com.sritarun.ledger.service;

import com.sritarun.ledger.api.dto.AccountResponse;
import com.sritarun.ledger.api.dto.CreateAccountRequest;
import com.sritarun.ledger.domain.Account;
import com.sritarun.ledger.repository.AccountRepository;
import com.sritarun.ledger.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = Account.builder()
                .ownerName(request.getOwnerName())
                .currency(request.getCurrency())
                .balance(BigDecimal.ZERO)
                .build();

        Account saved = accountRepository.save(account);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public AccountResponse getAccount(String externalId) {
        Account account = accountRepository.findByExternalId(externalId)
                .orElseThrow(() -> new NotFoundException("Account not found: " + externalId));
        return toResponse(account);
    }

    private AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getExternalId())
                .ownerName(account.getOwnerName())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
