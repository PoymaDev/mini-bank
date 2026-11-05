package org.example.minibank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateAccountRequestDTO {
    @NotBlank(message = "Номер счета не может быть пустым")
    @Size(min = 4, max = 20, message = "Номер счета должен быть от 4 до 20 символов")
    private String accountNumber;

    @NotBlank(message = "Имя владельца счета не может быть пустым")
    private String ownerName;

    @PositiveOrZero(message = "Начальный баланс не может быть отрицательным")
    private BigDecimal initialBalance;

    public CreateAccountRequestDTO(){}

    public CreateAccountRequestDTO(String accountNumber, String ownerName, BigDecimal initialBalance){
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.initialBalance = initialBalance;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }
}
