package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public class HCFirstBest extends HillClimbSearch {


    public HCFirstBest(State initialState, GoalFormul goalFormul) {
        super(initialState, goalFormul);
    }

    public HCFirstBest(State initialState, GoalFormul goalFormul, ICost iCostFunction) {
        super(initialState, goalFormul, iCostFunction);
    }

    public HCFirstBest(State initialState, GoalFormul goalFormul, ICost iCostFunction, ISuccessor iSuccessor) {
        super(initialState, goalFormul, iCostFunction, iSuccessor);
    }

    @Override
    public List<State> run() {
        List<State> successor = null;
        State nextState;
        int iterator = 0;
        List<State> nextStates = new ArrayList<>();
        while (getCostFunction().costFunction(getCurrentState()) != 0) {
            System.out.println(iterator);
            nextState = getSuccessor().successor(getCurrentState()).get(0);
            System.out.println(getCurrentState().getValue() + " " + getCostFunction().costFunction(getCurrentState()));
            if (getCostFunction().costFunction(nextState) >= getCostFunction().costFunction(getCurrentState())) {
                if (iterator > 56)
                    return new ArrayList<State>();
                iterator++;
                continue;
            }
            iterator = 0;
            nextStates.add(nextState);
            setCurrentState(nextState);
        }
        return nextStates;
    }
}
