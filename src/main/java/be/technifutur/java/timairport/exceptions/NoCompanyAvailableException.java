package be.technifutur.java.timairport.exceptions;

public class NoCompanyAvailableException  extends RuntimeException {

    public NoCompanyAvailableException(){
        super("the requested company was not found");
    }

    public NoCompanyAvailableException(Throwable cause){
        super("the requested company was not found", cause);
    }

}
