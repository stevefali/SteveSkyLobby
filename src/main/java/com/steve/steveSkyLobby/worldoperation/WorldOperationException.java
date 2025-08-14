package com.steve.steveSkyLobby.worldoperation;

public class WorldOperationException extends Exception{
    public WorldOperationException(String message) {
        super(message);
    }

    public WorldOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
