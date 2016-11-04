package ir.amin.problems.toys;

import ir.amin.problemsolve.GoalFormul;
import ir.amin.problemsolve.State;
import ir.amin.search.HCSteepestDescent;
import ir.amin.search.ICost;
import ir.amin.search.ISuccessor;
import ir.amin.search.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Amin on 04/11/2016.
 */
public class EightQueens {

    private Search search;

    public EightQueens(Search search) {
        this.search = search;
    }

    public void solve(State<List<Integer>> initialState) {

        HCSteepestDescent hcSteepestDescent = new HCSteepestDescent(initialState, new GoalFormul<List<Integer>>() {
            @Override
            public boolean isGoal(State<List<Integer>> t) {
                List<Integer> board = t.getValue();
                for (int i = 1; i < board.size(); i++) {
                    for (int j = 0; j < i; j++) {
                        if (board.get(i) == board.get(j) | Math.abs(board.get(i) - board.get(j)) == Math.abs(i - j)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        });

        hcSteepestDescent.setCostFunction(new ICost() {
            @Override
            public int costFunction(State s) {
                ArrayList<Integer> board = (ArrayList<Integer>) s.getValue();
                int cost = 0;
                for (int i = 1; i < board.size(); i++) {
                    for (int j = 0; j < i; j++) {
                        if (board.get(i) == board.get(j) | Math.abs(board.get(i) - board.get(j)) == Math.abs(i - j)) {
                            cost++;
                        }
                    }
                }
                return cost;
            }
        });

        hcSteepestDescent.setSuccessor(new ISuccessor() {
            @Override
            public List<State> successor(State currentState) {
                ArrayList<State> nextStates = new ArrayList<State>();
                ArrayList<Integer> currnetBoard = (ArrayList<Integer>) currentState.getValue();
                for (int i = 0; i < 8; i++) {
                    for (int j = 1; j < 8; j++) {
                        int row = currnetBoard.get(i);
                        int newRow = row + j;
                        if (newRow > 7 && newRow % 7 != 0) {
                            newRow = (newRow % 7) - 1;
                        } else if (newRow > 7 && newRow % 7 == 0) {
                            newRow = 7;
                        }
                        ArrayList<Integer> newArrayList = (ArrayList<Integer>) currnetBoard.clone();
                        newArrayList.set(i, newRow);
                        nextStates.add(new State(newArrayList));
                    }
                }
                return nextStates;
            }
        });

        List<State> l = hcSteepestDescent.run();
        System.out.println(hcSteepestDescent.isSuccessFul(l));
    }

    public static void main(String[] args) {
        for (int t = 0; t < 500; t++) {
            new EightQueens(null).solve(new State(generateBoard()));
        }
    }


    private static ArrayList<Integer> generateBoard() {
        ArrayList<Integer> al = new ArrayList<>();
        Random gen = new Random();
        for (int i = 0; i < 8; i++) {
            al.add(gen.nextInt(8));
        }
        return al;
    }

}