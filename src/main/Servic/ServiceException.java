package main.Servic;


public class ServiceException extends Exception {
    public ServiceException(Exception e){
        super(e);
    }
}
