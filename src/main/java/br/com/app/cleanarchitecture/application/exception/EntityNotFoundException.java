package br.com.app.cleanarchitecture.application.exception;

public class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Long id) {
        this(String.format("Not found with ID: %d", id));
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
