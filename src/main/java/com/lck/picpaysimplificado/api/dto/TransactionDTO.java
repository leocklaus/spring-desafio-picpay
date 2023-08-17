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
    @NotNull(message = "O id do 'Sender' é obrigatório.")
    private Long senderId;
    @NotNull(message = "O id do 'Receiver' é obrigatório")
    private Long receiverId;
    @NotNull(message = "O valor é obrigatório")
    private Double amount;

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.senderId = transaction.getSenderId();
        this.receiverId = transaction.getReceiverId();
        this.amount = transaction.getAmount();
    }

}
