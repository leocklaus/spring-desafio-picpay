package com.lck.picpaysimplificado.api.controller;

import com.lck.picpaysimplificado.api.dto.TransactionDTO;
import com.lck.picpaysimplificado.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionDTO = transactionService.createTransaction(transactionDTO);

        //trocar status para created

        return ResponseEntity
                .ok(transactionDTO);
    }

}
