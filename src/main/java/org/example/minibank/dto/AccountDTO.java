package org.example.minibank.dto;


import java.math.BigDecimal;

public class AccountDTO {
    private String accountNumber;
    private String name;
    private BigDecimal balance;

    public AccountDTO(String accountNumber, String name, BigDecimal balance){
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
