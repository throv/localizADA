package tech.ada.localizada.exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException (String message){
        super(message);
    }
}
