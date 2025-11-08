package pe.ask.creditfast.model.utils.gateways;

public interface PasswordEncoderGateway {
    String hash(String password);
    boolean matches(String rawPassword, String hashedPassword);
}
