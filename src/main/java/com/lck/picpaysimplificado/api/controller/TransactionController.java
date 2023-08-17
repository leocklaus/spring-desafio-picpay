package com.lck.picpaysimplificado.api.controller;

import com.lck.picpaysimplificado.api.dto.TransactionDTO;
import com.lck.picpaysimplificado.domain.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        List<TransactionDTO> transactions = transactionService.getAll();
        return ResponseEntity
                .ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id){
        TransactionDTO transaction = transactionService.getById(id);
        return ResponseEntity
                .ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO){
        transactionDTO = transactionService.createTransaction(transactionDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(transactionDTO.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(transactionDTO);
    }

}
