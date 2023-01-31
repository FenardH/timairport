package be.technifutur.java.timairport.exceptions;

public class NoPlaneAvailableException extends RuntimeException {


    public NoPlaneAvailableException(){
        super("the requested plane was not found");
    }

    public NoPlaneAvailableException(Throwable cause){
        super("the requested plane was not found", cause);
    }

}
