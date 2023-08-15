package com.lck.picpaysimplificado.api.dto;

import com.lck.picpaysimplificado.domain.model.Transaction;
import com.lck.picpaysimplificado.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private Double amount;

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.senderId = transaction.getSenderId();
        this.receiverId = transaction.getReceiverId();
        this.amount = transaction.getAmount();
    }

}
