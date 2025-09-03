package org.escapeone.db.exception;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg, Throwable cause){
        super(msg, cause);
    }

    public DatabaseException(String msg){
        super(msg);
    }
}
