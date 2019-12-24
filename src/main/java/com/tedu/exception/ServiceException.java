package com.tedu.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 8885690726084271650L;

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(){
        super();
    }


}
