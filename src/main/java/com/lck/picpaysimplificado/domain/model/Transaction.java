package com.lck.picpaysimplificado.domain.model;

import com.lck.picpaysimplificado.api.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "transactions")
@Entity
public class Transaction {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long receiverId;
    private Double amount;
    @CreationTimestamp
    private LocalDateTime creationTime;

    public Transaction(TransactionDTO dto){
        this.id = dto.getId();
        this.senderId = dto.getSenderId();
        this.receiverId = dto.getReceiverId();
        this.amount = dto.getAmount();
    }

}
