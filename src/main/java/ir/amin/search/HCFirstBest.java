package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public class HCFirstBest extends HillClimbSearch {

    private int maxNextStateNumbers;
    private List<State> generatedStates = new ArrayList<State>();

    public HCFirstBest(State initialState, GoalFormul goalFormul, int maxNextStateNumbers) {
        super(initialState, goalFormul);
        this.maxNextStateNumbers = maxNextStateNumbers;
    }

    public HCFirstBest(State initialState, GoalFormul goalFormul, ICost iCostFunction, int maxNextStateNumbers) {
        super(initialState, goalFormul, iCostFunction);
        this.maxNextStateNumbers = maxNextStateNumbers;
    }

    public HCFirstBest(State initialState, GoalFormul goalFormul, ICost iCostFunction, ISuccessor iSuccessor, int maxNextStateNumbers) {
        super(initialState, goalFormul, iCostFunction, iSuccessor);
        this.maxNextStateNumbers = maxNextStateNumbers;
    }

    @Override
    public List<State> run() {
        List<State> successor = null;
        State nextState;
        List<State> nextStates = new ArrayList<>();
        while (getCostFunction().costFunction(getCurrentState()) != 0) {
            successor = getSuccessor().successor(getCurrentState());
            nextState = successor.get(0);
            if (generatedStates.contains(nextState)) {
                continue;
            }
            generatedStates.add(nextState);
            if (getCostFunction().costFunction(nextState) < getCostFunction().costFunction(getCurrentState())) {
                generatedStates.clear();
                nextStates.add(nextState);
                setCurrentState(nextState);
            }
            if (generatedStates.size() == maxNextStateNumbers) {
                break;
            }
        }
        return nextStates;
    }
}
