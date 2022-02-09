package com.school.app.exception;

public class ForbiddenException extends RuntimeException
{
    public ForbiddenException(String message)
    {
        super("This operation is not allowed because " + message);
        System.out.println(super.getMessage());
    }
}
