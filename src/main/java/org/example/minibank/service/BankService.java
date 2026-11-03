package org.example.minibank.service;

import org.example.minibank.entity.Account;
import org.example.minibank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankService {

    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String accountNumber, String ownerName, BigDecimal initialBalance){
        if(initialBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }

        Account account = new Account(accountNumber,ownerName,  initialBalance);
        return accountRepository.save(account);
    }

    public Account getAccount(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Счет " + accountNumber +" не найден"));

    }

    @Transactional
    public void transfer(String fromNumber, String toNumber, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Сумма перевода не может быть меньше нуля");
        }

        Account fromAcc = accountRepository.findByAccountNumber(fromNumber)
                .orElseThrow(() -> new IllegalArgumentException("Счет отправителя не найден"));

        Account toAcc = accountRepository.findByAccountNumber(toNumber)
                .orElseThrow(() -> new IllegalArgumentException("Счет получателя не найден"));

        if(fromAcc.getBalance().compareTo(amount) < 0){
            throw new IllegalArgumentException("недостаточно средств для перевода");
        }

        fromAcc.setBalance(fromAcc.getBalance().subtract(amount));
        toAcc.setBalance(toAcc.getBalance().add(amount));

        accountRepository.save(fromAcc);
        accountRepository.save(toAcc);
    }
}
