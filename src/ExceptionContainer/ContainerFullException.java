package ExceptionContainer;

public class ContainerFullException extends RuntimeException {
    public ContainerFullException() { super("Exception: Container is full."); }
    public ContainerFullException (String message) { super(message); }
}
