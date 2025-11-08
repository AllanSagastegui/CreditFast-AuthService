package pe.ask.creditfast.model.utils.gateways;

public interface LogExecutionGateway {
    void trace(String message, Object... args);
    void info(String message, Object... args);
    void warn(String message, Object... args);
    void error(String message, Object... args);
}
