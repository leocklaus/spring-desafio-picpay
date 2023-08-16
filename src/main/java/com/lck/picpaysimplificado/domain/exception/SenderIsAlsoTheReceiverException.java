package com.lck.picpaysimplificado.domain.exception;

public class SenderIsAlsoTheReceiverException extends TransactionExceptions{

    public SenderIsAlsoTheReceiverException(String msg) {
        super(msg);
    }

    public SenderIsAlsoTheReceiverException(){
        this("Não é possível enviar dinheiro para si mesmo.");
    }
}
