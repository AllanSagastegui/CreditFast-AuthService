package pe.ask.creditfast.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.ask.creditfast.model.utils.gateways.LoggableGateway;

@Slf4j
@Component
public class LoggableAdapter implements LoggableGateway {
    @Override
    public void trace(String message, Object... args) {
        log.trace(message, args);
    }

    @Override
    public void info(String message, Object... args) {
        log.info(message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        log.warn(message, args);
    }

    @Override
    public void error(String message, Object... args) {
        log.error(message, args);
    }
}
