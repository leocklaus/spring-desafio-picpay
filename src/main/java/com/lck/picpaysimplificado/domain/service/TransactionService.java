package com.lck.picpaysimplificado.domain.service;

import com.lck.picpaysimplificado.api.dto.TransactionDTO;
import com.lck.picpaysimplificado.domain.exception.UserNotFoundException;
import com.lck.picpaysimplificado.domain.model.Transaction;
import com.lck.picpaysimplificado.domain.model.User;
import com.lck.picpaysimplificado.domain.repository.TransactionRepository;
import com.lck.picpaysimplificado.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Transaction createTransaction(TransactionDTO transactionDTO){
        var transaction = new Transaction(transactionDTO);
        var sender = getUserByIdOrThrowsException(transactionDTO.getSenderId());
        var receiver = getUserByIdOrThrowsException(transactionDTO.getReceiverId());
        Double transactionAmount = transactionDTO.getAmount();

        if(sender.canSendMoney() && sender.canWithdraw(transactionAmount)){
            sender.withdraw(transactionAmount);
            receiver.receiveMoney(transactionAmount);
        }

        transaction = transactionRepository.save(transaction);

        return transaction;
    }

    private User getUserByIdOrThrowsException(Long id){
        var user = userRepository.findById(id)
                .orElseThrow(()->  new UserNotFoundException(id));
        return user;
    }

}
