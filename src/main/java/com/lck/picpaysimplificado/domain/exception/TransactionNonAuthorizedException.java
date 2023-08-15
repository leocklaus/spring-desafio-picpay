package com.lck.picpaysimplificado.domain.exception;

public class TransactionNonAuthorizedException extends TransactionExceptions{

    public TransactionNonAuthorizedException(String msg) {
        super(msg);
    }

    public TransactionNonAuthorizedException(){
        this("Transação não autorizada.");
    }
}
