package org.example.minibank.service;

import org.example.minibank.dto.SystemStatus;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    public SystemStatus method(){
        return new SystemStatus("Пять минут полет", "67.67.67");
    }
}
