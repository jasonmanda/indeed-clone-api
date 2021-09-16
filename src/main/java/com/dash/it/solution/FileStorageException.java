package com.dash.it.solution;

public class FileStorageException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -8524924539827226251L;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}