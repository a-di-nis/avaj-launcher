package fr.ecole.avaj.Exceptions;

public class InvalidArgumentCountException extends Exception {
    static final long serialVersionUID = 1;

    public InvalidArgumentCountException(String errorMessage) {
        super(errorMessage);
    }
}
