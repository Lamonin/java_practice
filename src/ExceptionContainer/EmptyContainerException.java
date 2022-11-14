package ExceptionContainer;

public class EmptyContainerException extends RuntimeException {
    public EmptyContainerException() { super("Exception: Container is empty."); }

    public EmptyContainerException(String message) { super(message); }
}
