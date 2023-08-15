package com.lck.picpaysimplificado.domain.exception;

public class UserNotFoundException extends UserException{
    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(Long id){
        this("Usuário não encontrado com o id: " + id);
    }
}
