package com.lck.picpaysimplificado.api.dto;

import com.lck.picpaysimplificado.domain.model.Transaction;
import com.lck.picpaysimplificado.domain.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;
    @NotNull
    private Double amount;

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.senderId = transaction.getSenderId();
        this.receiverId = transaction.getReceiverId();
        this.amount = transaction.getAmount();
    }

}
