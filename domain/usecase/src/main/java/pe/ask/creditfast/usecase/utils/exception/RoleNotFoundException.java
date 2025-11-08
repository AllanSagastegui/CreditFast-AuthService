package pe.ask.creditfast.usecase.utils.exception;

import pe.ask.creditfast.model.utils.exception.BaseException;
import pe.ask.creditfast.model.utils.exception.ErrorCatalog;

public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException(){
        super(
                ErrorCatalog.ROLE_NOT_FOUND.getErrorCode(),
                ErrorCatalog.ROLE_NOT_FOUND.getExceptionName(),
                ErrorCatalog.ROLE_NOT_FOUND.getMessage(),
                ErrorCatalog.ROLE_NOT_FOUND.getStatus(),
                ErrorCatalog.ROLE_NOT_FOUND.getErrors()
        );
    }
}
