package com.vsouza.managerproducts.controllers.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message){
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
