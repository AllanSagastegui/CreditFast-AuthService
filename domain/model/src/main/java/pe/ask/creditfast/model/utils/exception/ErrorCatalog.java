package pe.ask.creditfast.model.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {
    USER_ALREADY_EXISTS(
            "AUTH_USER_ALREADY_EXISTS",
            "User Already Exists",
            "A user with this information already exists. Please use different information to Sign Up.",
            409,
            Map.of("email or National ID", "This email or National ID is already in use.")
    ),
    ROLE_NOT_FOUND(
            "AUTH_ROLE_NOT_FOUND",
            "Role Not Found",
            "The role you specified does not exist. Please check and try again.",
            404,
            Map.of("role", "The specified role does not exist.")
    )

    ;

    private final String errorCode;
    private final String exceptionName;
    private final String message;
    private final int status;
    private final Map<String, String> errors;
}
