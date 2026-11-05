package org.example.minibank.controller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.example.minibank.dto.AccountDTO;
import org.example.minibank.dto.CreateAccountRequestDTO;
import org.example.minibank.entity.Account;
import org.example.minibank.service.BankService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Valid
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @PostMapping("/create")
    public AccountDTO createAccount(@Valid @RequestBody CreateAccountRequestDTO request){
        Account account = bankService.createAccount(
                request.getAccountNumber(),
                request.getOwnerName(),
                request.getInitialBalance());
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
