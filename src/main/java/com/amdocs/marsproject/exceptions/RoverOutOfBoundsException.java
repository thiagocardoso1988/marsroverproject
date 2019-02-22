package com.amdocs.marsproject.exceptions;

public class RoverOutOfBoundsException extends RuntimeException
{
    private static final long serialVersionUID = 3521359656923818414L;

    public RoverOutOfBoundsException(String pMessage)
    {
        super(pMessage);
    }
}
