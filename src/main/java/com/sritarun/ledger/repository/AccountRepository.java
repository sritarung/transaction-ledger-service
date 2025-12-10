package com.sritarun.ledger.repository;

import com.sritarun.ledger.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByExternalId(String externalId);
}
