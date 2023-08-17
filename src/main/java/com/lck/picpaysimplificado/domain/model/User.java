package com.lck.picpaysimplificado.domain.model;

import com.lck.picpaysimplificado.domain.exception.SenderIsAlsoTheReceiverException;
import com.lck.picpaysimplificado.domain.exception.UserCannotSendMoneyException;
import com.lck.picpaysimplificado.domain.exception.UserDoesntHaveEnoughBalanceException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;


    public User(Long id, String name, String password, BigDecimal balance, UserType userType) {
        this.id = id;
        this.name= name;
        this.password = password;
        this.balance = balance;
        this.userType = userType;
    }

    public boolean canSendMoney(){
        if (this.userType == UserType.BUSINESS){
            throw new UserCannotSendMoneyException();
        }
        return true;
    }

    public boolean canWithdraw(BigDecimal amount){
        if (amount.compareTo(this.balance) > 0){
            throw new UserDoesntHaveEnoughBalanceException();
        }
        return true;
    }

    public boolean isNotTheSender(Long senderId){
        if(this.getId() == senderId){
            throw new SenderIsAlsoTheReceiverException();
        }
        return true;
    }

    public void withdraw(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
    }

    public void receiveMoney(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }
}
