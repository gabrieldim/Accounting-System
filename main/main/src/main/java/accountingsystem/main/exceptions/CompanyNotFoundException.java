package accountingsystem.main.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(){
        super("Company not found exception.");
    }
}
