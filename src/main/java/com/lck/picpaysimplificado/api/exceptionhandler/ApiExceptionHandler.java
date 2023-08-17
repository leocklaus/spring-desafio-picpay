package com.lck.picpaysimplificado.api.exceptionhandler;

import com.lck.picpaysimplificado.domain.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(TransactionNonAuthorizedException.class)
    public ResponseEntity<?> handleTransactionNonAuthorizedException(TransactionNonAuthorizedException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var exceptionType = ExceptionType.TRANSACTION_NON_AUTHORIZED;
        String detail = ex.getMessage();

        ExceptionModel exception = exceptionBuilder(status, exceptionType, detail)
                .userMessage(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var bindingResult = ex.getBindingResult();
        String detail = "Um ou mais campos estão inválidos. Preencha o formulário novamente.";

        List<ExceptionModel.Object> errors = new ArrayList<>();

        errors = bindingResult.getFieldErrors().stream()
                .map(error -> {
                    return ExceptionModel.Object.builder()
                            .name(error.getField())
                            .userMessage(error.getDefaultMessage())
                            .build();
                }).toList();

        var exceptionType = ExceptionType.INVALID_DATA;

        ExceptionModel exception = exceptionBuilder((HttpStatus) status, exceptionType, detail)
                .userMessage(detail)
                .objects(errors)
                .build();

        return handleExceptionInternal(ex, exception, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleSystemException(Exception ex, WebRequest request){

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var exceptionType = ExceptionType.SYSTEM_ERROR;

        ExceptionModel exception = exceptionBuilder(status, exceptionType, GENERIC_ERROR_MESSAGE)
                .userMessage(GENERIC_ERROR_MESSAGE)
                .build();

        ex.printStackTrace();


        return handleExceptionInternal(ex, exception, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var detail = String.format("O recurso '%s' que você tentou acessar é inexistente", ex.getRequestURL());
        var exceptionType = ExceptionType.RESOURCE_NOT_FOUND;

        ExceptionModel exception = exceptionBuilder((HttpStatus) status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exception, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if(body == null){
            body = ExceptionModel.builder()
                    .status(statusCode.value())
                    .type(ex.getMessage())
                    .title(ex.getMessage())
                    .detail(ex.getMessage())
                    .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ExceptionModel.ExceptionModelBuilder exceptionBuilder(HttpStatus status, ExceptionType exceptionType, String detail){
        var exception = ExceptionModel.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .type(exceptionType.getURI())
                .title(exceptionType.getTitle())
                .detail(detail);
        return exception;
    }
}
