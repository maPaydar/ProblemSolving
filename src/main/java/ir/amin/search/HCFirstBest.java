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
        List<State> nextStates = new ArrayList<>();
        while (getCostFunction().costFunction(getCurrentState()) != 0) {
            do {
                successor = getSuccessor().successor(getCurrentState());
                nextState = successor.get(0);
                //System.out.println("fuck : " + nextState.getValue());
            } while (getCostFunction().costFunction(nextState) >= getCostFunction().costFunction(getCurrentState()));
            System.out.println("next state : " + nextState.getValue());
            nextStates.add(nextState);
            setCurrentState(nextState);
        }
        return nextStates;
    }

}
