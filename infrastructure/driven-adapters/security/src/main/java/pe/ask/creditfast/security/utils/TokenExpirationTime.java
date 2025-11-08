package pe.ask.creditfast.security.utils;

import lombok.Getter;

@Getter
public enum TokenExpirationTime {
    ACCESS(3600),
    REFRESH(25200);
    
    private final long seconds;

    TokenExpirationTime(long seconds){
        this.seconds = seconds;
    }
}
