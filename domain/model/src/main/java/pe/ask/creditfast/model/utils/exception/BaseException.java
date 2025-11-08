package pe.ask.creditfast.model.utils.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class BaseException extends RuntimeException {
    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;
    private final Map<String, String> errors;

    protected BaseException(
            String errorCode,
            String exceptionName,
            String message,
            int status,
            Map<String, String> errors
    ) {
        super(message);
        this.errorCode = errorCode;
        this.exceptionName = exceptionName;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }
}
