package ExceptionContainer;

import java.util.ArrayList;

public class ExceptionContainer<T> {
    private final int CAPACITY = 10;
    private final ArrayList<T> elements;
    private int count;

    public ExceptionContainer() {
        this.elements = new ArrayList<T>(CAPACITY);
    }

    public void push(T value) throws ContainerFullException {
        if (count == CAPACITY)
        {
            throw new ContainerFullException("Exception: Container is full.");
        }

        elements.add(value);
        count += 1;
    }

    public T pop() throws EmptyContainerException {
        if (count == 0)
        {
            throw new EmptyContainerException();
        }

        count -= 1;
        return elements.remove(count);
    }

    public boolean contains(T value) throws EmptyContainerException {
        if (count == 0)
        {
            throw new EmptyContainerException();
        }

        return elements.contains(value);
    }

    public void clear() {
        elements.clear();
        count = 0;
    }

    public int size() { return count; }
}
