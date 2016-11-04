package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public class HCSteepestDescent extends HillClimbSearch {

    public HCSteepestDescent(State initialState, GoalFormul goalFormul) {
        super(initialState, goalFormul);
    }

    public HCSteepestDescent(State initialState, GoalFormul goalFormul, ICost iCostFunction) {
        super(initialState, goalFormul, iCostFunction);
    }

    public HCSteepestDescent(State initialState, GoalFormul goalFormul, ICost iCostFunction, ISuccessor iSuccessor) {
        super(initialState, goalFormul, iCostFunction, iSuccessor);
    }

    @Override
    public List<State> run() {
        List<State> successor = null;
        List<State> nextStates = new ArrayList<>();
        State nextState = null;
        while (this.getCostFunction().costFunction(this.getCurrentState()) != 0) {
            System.out.println(this.getCostFunction().costFunction(this.getCurrentState()));
            successor = this.getSuccessor().successor(this.getCurrentState());
            nextState = null;
            for (State s : successor) {
                if(this.getCostFunction().costFunction(s) < this.getCostFunction().costFunction(this.getCurrentState())) {
                    nextState = s;
                }
            }
            if(nextState == null) {
                break;
            }
            nextStates.add(nextState);
            this.setCurrentState(nextState);
        }
        return nextStates;
    }

}
