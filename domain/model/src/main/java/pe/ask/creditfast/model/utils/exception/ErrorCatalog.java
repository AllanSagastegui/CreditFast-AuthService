package pe.ask.creditfast.model.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {


    ;

    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;
    private final Map<String, String> errors;
}
