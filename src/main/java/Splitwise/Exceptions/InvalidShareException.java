package Splitwise.Exceptions;

public class InvalidShareException extends Exception {
    public InvalidShareException(String msg) {
        super(msg);
    }
    public InvalidShareException(String msg, Throwable cause) {
        super(msg,cause);
    }
}