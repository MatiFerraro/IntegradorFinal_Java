package exceptions;

public class ControllerException extends ServiceException {

    public ControllerException(){
        super();
    }

    public ControllerException(String message){
        super(message);
    }

    public ControllerException(Throwable cause){
        super(cause);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

}
