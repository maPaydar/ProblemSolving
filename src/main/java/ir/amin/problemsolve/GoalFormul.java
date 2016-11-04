package ir.amin.problemsolve;

/**
 * Created by Amin on 04/11/2016.
 */
public abstract class GoalFormul<T> {
    public abstract boolean isGoal(State<T> t);
}
