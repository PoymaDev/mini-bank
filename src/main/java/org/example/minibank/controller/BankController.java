package org.example.minibank.controller;
import org.example.minibank.entity.Account;
import org.example.minibank.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping("/create")
    public Account createAccount(
            @RequestParam String number,
            @RequestParam String name,
            @RequestParam BigDecimal balance
            ){
        return bankService.createAccount(number, name, balance);
    }

    @GetMapping("/{number}")
    public Account getAccount(@PathVariable String number){
        return bankService.getAccount(number);
    }

    @GetMapping("/transfer")
    public String transfer(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount){
        bankService.transfer(from, to, amount);
        return "Перевод выполнен успешно!";
    }

}
