package com.lck.picpaysimplificado.domain.service;

import com.lck.picpaysimplificado.api.dto.TransactionAuthDTO;
import com.lck.picpaysimplificado.api.dto.TransactionDTO;
import com.lck.picpaysimplificado.domain.exception.TransactionNonAuthorizedException;
import com.lck.picpaysimplificado.domain.exception.UserNotFoundException;
import com.lck.picpaysimplificado.domain.model.Transaction;
import com.lck.picpaysimplificado.domain.model.User;
import com.lck.picpaysimplificado.domain.repository.TransactionRepository;
import com.lck.picpaysimplificado.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

            if(transactionAuthorizationServiceReturnsSuccess()){
                sender.withdraw(transactionAmount);
                receiver.receiveMoney(transactionAmount);
            } else {
                throw new TransactionNonAuthorizedException();
            }
        }

        transaction = transactionRepository.save(transaction);

        return transaction;
    }

    private User getUserByIdOrThrowsException(Long id){
        var user = userRepository.findById(id)
                .orElseThrow(()->  new UserNotFoundException(id));
        return user;
    }

    private boolean transactionAuthorizationServiceReturnsSuccess(){
        final var URL = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";
        final var SUCCESS_MESSAGE = "Autorizado";
        var restTemplate = new RestTemplate();

        ResponseEntity<TransactionAuthDTO> transactionAuthDTO =
                restTemplate.getForEntity(URL, TransactionAuthDTO.class);

        return transactionAuthDTO.getBody().getMessage().equals(SUCCESS_MESSAGE);
    }

}
