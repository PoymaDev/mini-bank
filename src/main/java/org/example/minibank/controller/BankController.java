package org.example.minibank.controller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.example.minibank.dto.AccountDTO;
import org.example.minibank.entity.Account;
import org.example.minibank.service.BankService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Validated
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping("/create")
    public AccountDTO createAccount(
            @RequestParam @NotBlank @Size(min = 4, max = 20) String number,
            @RequestParam @NotBlank(message = "Имя не может быть пустым") String name,
            @RequestParam @PositiveOrZero(message = "Баланс не может быть отрицательным") BigDecimal balance
            ){
        Account account = bankService.createAccount(number, name, balance);
        return new AccountDTO(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }

    @GetMapping("/{number}")
    public AccountDTO getAccount(@PathVariable String number){
        Account account = bankService.getAccount(number);
        return new AccountDTO(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }

    @GetMapping("/transfer")
    public String transfer(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam @PositiveOrZero(message = "Сумма перевода не может быть отрицательной") BigDecimal amount){
        bankService.transfer(from, to, amount);
        return "Перевод выполнен успешно!";
    }

}
