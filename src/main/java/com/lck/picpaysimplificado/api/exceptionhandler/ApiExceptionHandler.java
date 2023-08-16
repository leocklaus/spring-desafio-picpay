package com.lck.picpaysimplificado.api.exceptionhandler;

import com.lck.picpaysimplificado.domain.exception.SenderIsAlsoTheReceiverException;
import com.lck.picpaysimplificado.domain.exception.UserCannotSendMoneyException;
import com.lck.picpaysimplificado.domain.exception.UserDoesntHaveEnoughBalanceException;
import com.lck.picpaysimplificado.domain.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String GENERIC_ERROR_MESSAGE
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var exceptionType = ExceptionType.USER_NOT_FOUND;
        String detail = ex.getMessage();

        ExceptionModel exception = exceptionBuilder(status, exceptionType, detail)
                .userMessage(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserCannotSendMoneyException.class)
    public ResponseEntity<?> handleUserCannotSendMoneyException(UserCannotSendMoneyException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var exceptionType = ExceptionType.USER_CANNOT_SEND_MONEY;
        String detail = ex.getMessage();

        ExceptionModel exception = exceptionBuilder(status, exceptionType, detail)
                .userMessage(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserDoesntHaveEnoughBalanceException.class)
    public ResponseEntity<?> handleUserDoesnHaveEnoughBalanceException(UserDoesntHaveEnoughBalanceException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var exceptionType = ExceptionType.USER_DOESNT_HAVE_ENOUGH_BALANCE;
        String detail = ex.getMessage();

        ExceptionModel exception = exceptionBuilder(status, exceptionType, detail)
                .userMessage(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(SenderIsAlsoTheReceiverException.class)
    public ResponseEntity<?> handleSenderIsAlsoTheReceiverException(SenderIsAlsoTheReceiverException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var exceptionType = ExceptionType.SENDER_IS_THE_RECEIVER;
        String detail = ex.getMessage();

        ExceptionModel exception = exceptionBuilder(status, exceptionType, detail)
                .userMessage(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ExceptionModel.ExceptionModelBuilder exceptionBuilder(HttpStatus status, ExceptionType exceptionType, String detail){
        var exception = ExceptionModel.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .type(exceptionType.getURI())
                .title(exceptionType.getTitle())
                .detail(detail);
        return exception;
    }
}
