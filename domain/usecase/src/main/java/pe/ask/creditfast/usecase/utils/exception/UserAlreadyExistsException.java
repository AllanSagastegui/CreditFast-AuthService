package pe.ask.creditfast.usecase.utils.exception;

import pe.ask.creditfast.model.utils.exception.BaseException;
import pe.ask.creditfast.model.utils.exception.ErrorCatalog;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException() {
        super(
                ErrorCatalog.USER_ALREADY_EXISTS.getErrorCode(),
                ErrorCatalog.USER_ALREADY_EXISTS.getExceptionName(),
                ErrorCatalog.USER_ALREADY_EXISTS.getMessage(),
                ErrorCatalog.USER_ALREADY_EXISTS.getStatus(),
                ErrorCatalog.USER_ALREADY_EXISTS.getErrors()
        );
    }
}
