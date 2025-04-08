package com.bivgroup.repository;

import com.bivgroup.entity.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {

    @Override
    public Optional<Account> findByIdOptional(Long aLong) {
        return PanacheRepository.super.findByIdOptional(aLong);
    }

    public Optional<Account> findByLogin(String login) {
        return find("login=?1", login).firstResultOptional();
    }
}
