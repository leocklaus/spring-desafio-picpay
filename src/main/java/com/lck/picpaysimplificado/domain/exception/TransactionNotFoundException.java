package com.lck.picpaysimplificado.domain.exception;

public class TransactionNotFoundException extends TransactionExceptions{

    public TransactionNotFoundException(String msg) {
        super(msg);
    }

    public TransactionNotFoundException(Long id){
        this("Transação não encontrada com o id: " + id);
    }
}
