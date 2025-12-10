package com.sritarun.ledger.api;

import com.sritarun.ledger.api.dto.AccountResponse;
import com.sritarun.ledger.api.dto.CreateAccountRequest;
import com.sritarun.ledger.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable("accountId") String accountId) {
        return accountService.getAccount(accountId);
    }
}
