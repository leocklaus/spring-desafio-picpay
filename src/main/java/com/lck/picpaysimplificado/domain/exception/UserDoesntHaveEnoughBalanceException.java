package com.lck.picpaysimplificado.domain.exception;

public class UserDoesntHaveEnoughBalanceException extends TransactionExceptions{

    public UserDoesntHaveEnoughBalanceException(String msg) {
        super(msg);
    }

    public UserDoesntHaveEnoughBalanceException(){
        this("Saldo insuficiente para concluir a transação.");
    }
}
