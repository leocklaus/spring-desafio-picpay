package com.lck.picpaysimplificado.domain.exception;

public class UserCannotSendMoneyException extends TransactionExceptions{

    public UserCannotSendMoneyException(String msg) {
        super(msg);
    }

    public UserCannotSendMoneyException(){
        this("Lojas não podem enviar dinheiro, apenas receber.");
    }
}
