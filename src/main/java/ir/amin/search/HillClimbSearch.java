package ir.amin.search;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;

import java.util.List;

/**
 * Created by Amin on 04/11/2016.
 */
public abstract class HillClimbSearch extends Search {

    private ICost iCostFunction;
    private ISuccessor iSuccessor;


    public HillClimbSearch(State initialState, GoalFormul goalFormul) {
        super(initialState, goalFormul);
    }

    public HillClimbSearch(State initialState, GoalFormul goalFormul, ICost iCostFunction) {
        super(initialState, goalFormul);
        this.iCostFunction = iCostFunction;
    }

    public HillClimbSearch(State initialState, GoalFormul goalFormul, ICost iCostFunction, ISuccessor iSuccessor) {
        super(initialState, goalFormul);
        this.iCostFunction = iCostFunction;
        this.iSuccessor = iSuccessor;
    }

    public void setCostFunction(ICost iCostFunction) {
        this.iCostFunction = iCostFunction;
    }

    public ICost getCostFunction() {
        return iCostFunction;
    }

    public void setSuccessor(ISuccessor iSuccessor) {
        this.iSuccessor = iSuccessor;
    }

    public ISuccessor getSuccessor() {
        return iSuccessor;
    }

    @Override
    public abstract List<State> run();

    @Override
    public boolean isSuccessFul(List<State> nextStates) {
        if(nextStates.size() == 0)
            return false;
        return this.getCostFunction().costFunction(nextStates.get(nextStates.size() - 1)) == 0;
    }
}
