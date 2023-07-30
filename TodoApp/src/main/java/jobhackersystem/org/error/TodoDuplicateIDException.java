package jobhackersystem.org.error;

public class TodoDuplicateIDException extends RuntimeException{
    public TodoDuplicateIDException(String message) {
        super(message);
    }
}
