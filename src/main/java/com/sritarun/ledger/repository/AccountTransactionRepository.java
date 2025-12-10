package com.sritarun.ledger.repository;

import com.sritarun.ledger.domain.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    Optional<AccountTransaction> findByExternalId(String externalId);

    List<AccountTransaction> findByAccount_ExternalIdOrderByCreatedAtDesc(String accountExternalId);
}
