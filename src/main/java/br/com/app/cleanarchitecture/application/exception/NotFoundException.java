package br.com.app.cleanarchitecture.application.exception;

public class NotFoundException extends RuntimeException {

    private String resourceName;
    private Long id;

    public NotFoundException(String resourceName, Long id) {
        super("Resource not found!");
        this.resourceName = resourceName;
        this.id = id;
    }

    public NotFoundException(String resourceName) {
        super("Resource not found!");
        this.resourceName = resourceName;
    }

    public NotFoundException(Long id) {
        super("Resource not found!");
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}