package com.lck.picpaysimplificado.domain.model;

import lombok.Getter;

@Getter
public enum UserType {

    COMMON(DocumentType.CPF),
    BUSINESS(DocumentType.CNPJ);

    private UserType(DocumentType documentType){
    };

    @Getter
    public enum DocumentType{
        CPF,
        CNPJ
    }
}
