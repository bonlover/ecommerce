package dev.gurung.exceptions;

public class QuantityOutOfStockException extends RuntimeException{
    public QuantityOutOfStockException(String message){
        super(message);
    }
}
