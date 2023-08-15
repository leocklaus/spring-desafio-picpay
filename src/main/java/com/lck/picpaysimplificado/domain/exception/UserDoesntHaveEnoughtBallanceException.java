package com.lck.picpaysimplificado.domain.exception;

public class UserDoesntHaveEnoughtBallanceException extends TransactionExceptions{

    public UserDoesntHaveEnoughtBallanceException(String msg) {
        super(msg);
    }

    public UserDoesntHaveEnoughtBallanceException(){
        this("Saldo insuficiente para concluir a transação.");
    }
}
