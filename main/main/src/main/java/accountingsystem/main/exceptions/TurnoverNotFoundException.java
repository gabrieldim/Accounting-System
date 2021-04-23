package accountingsystem.main.exceptions;

public class TurnoverNotFoundException extends RuntimeException{
    public TurnoverNotFoundException(){
        super("Turnover Not Found Exception");
    }
}
