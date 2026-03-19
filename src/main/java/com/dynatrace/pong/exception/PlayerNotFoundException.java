package com.dynatrace.pong.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Long id) {
        super("Player not found with id: " + id);
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }
}

