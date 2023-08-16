package com.lck.picpaysimplificado.domain.model;

import com.lck.picpaysimplificado.domain.exception.UserCannotSendMoneyException;
import com.lck.picpaysimplificado.domain.exception.UserDoesntHaveEnoughtBallanceException;
import jakarta.persistence.*;
import lombok.*;

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
    private Double balance;
    private UserType userType;


    public User(Long id, String name, String password, Double balance, UserType userType) {
        this.id = id;
        this.name= name;
        this.password = password;
        this.balance = balance;
        this.userType = userType;
    }

    public boolean canSendMoney(){
        if (this.userType == UserType.COMMON){
            throw new UserCannotSendMoneyException();
        }
        return true;
    }

    public boolean canWithdraw(Double amount){
        if (amount <= this.balance){
            throw new UserDoesntHaveEnoughtBallanceException();
        }
        return true;
    }

    public void withdraw(Double amount){
        this.balance-=amount;
    }

    public void receiveMoney(Double amount){
        this.balance+=amount;
    }
}
