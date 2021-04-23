package accountingsystem.main.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product invalid id exception");
    }
}
