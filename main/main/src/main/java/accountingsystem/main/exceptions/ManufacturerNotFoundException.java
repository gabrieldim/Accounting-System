package accountingsystem.main.exceptions;

public class ManufacturerNotFoundException extends RuntimeException{
    public ManufacturerNotFoundException(){
        super("ManufacturerInvalidIdException");
    }
}
