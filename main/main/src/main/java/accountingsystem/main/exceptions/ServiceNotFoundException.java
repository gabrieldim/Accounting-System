package accountingsystem.main.exceptions;

public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException(){
        super("Service not found exception");
    }
}
