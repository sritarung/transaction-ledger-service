package com.sritarun.ledger.api;

import com.sritarun.ledger.api.dto.CreateTransactionRequest;
import com.sritarun.ledger.api.dto.TransactionResponse;
import com.sritarun.ledger.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts/{accountId}/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@PathVariable("accountId") String accountId,
                                                 @Valid @RequestBody CreateTransactionRequest request) {
        return transactionService.createTransaction(accountId, request);
    }

    @GetMapping
    public List<TransactionResponse> listTransactions(@PathVariable("accountId") String accountId) {
        return transactionService.listTransactions(accountId);
    }
}
