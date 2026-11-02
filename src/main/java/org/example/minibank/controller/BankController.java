package org.example.minibank.controller;
import org.example.minibank.dto.SystemStatus;
import org.example.minibank.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping("/status")
    public SystemStatus checkStatus(){
        return bankService.method();
    }
}
