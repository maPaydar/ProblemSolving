package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ir.amin on 10/26/16.
 */
public class BFSearch extends Search {

    private Queue<State> queue;


    public BFSearch(State initialState, GoalFormul goalFormul) {
        super(initialState, goalFormul);
        this.queue = new LinkedList<>();
    }

    @Override
    public List<State> run() {
        this.queue.add(super.getInitialState());
        //this.root.visited = true;
        while (!this.queue.isEmpty()) {
            super.setCurrentState(this.queue.remove());
            System.out.println(this.getCurrentState().getValue());
            super.getPathToGoal().add(super.getCurrentState());
            if (super.getGoalFormul().isGoal(super.getCurrentState())) {
                return super.getPathToGoal();
            }
            for (Object nextState : super.getCurrentState().getNextStates()) {
                this.queue.add((State) nextState);
            }
        }
        return null;
    }
}
