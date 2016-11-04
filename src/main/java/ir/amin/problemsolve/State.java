package ir.amin.problemsolve;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 03/11/2016.
 */
public class State <T> {

    private T value;
    private List<T> nextStates = new ArrayList();

    public State(T value) {
        this.value = value;
    }

    public T getValue() {
        return (T)value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<T> getNextStates() {
        return nextStates;
    }

    public void setNextStates(List<T> nextStates) {
        this.nextStates = nextStates;
    }
}
