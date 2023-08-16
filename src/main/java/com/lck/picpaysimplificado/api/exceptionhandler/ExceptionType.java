package com.lck.picpaysimplificado.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionType {

    TRANSACTION_NON_AUTHORIZED("Transação não autorizada", "/non-authorized-transaction"),
    USER_CANNOT_SEND_MONEY("Usuário não pode enviar dinheiro", "/user-cannot-send-money"),
    USER_DOESNT_HAVE_ENOUGH_BALANCE("Saldo insuficiente", "/not-enought-balance"),
    USER_NOT_FOUND("Id inválido", "/user-not-found"),
    SENDER_IS_THE_RECEIVER("Mesmo usuário envia e recebe", "/sender-is-also-the-receiver");


    private String title;
    private String URI;

    ExceptionType(String title, String URI){
        this.title = title;
        this.URI = URI;
    }

}
