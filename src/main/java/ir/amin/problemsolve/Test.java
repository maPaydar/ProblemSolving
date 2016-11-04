package ir.amin.problemsolve;

import ir.amin.search.HillClimbSearch;
import ir.amin.search.ICost;
import ir.amin.search.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Amin on 03/11/2016.
 */
public class Test {

    public static void main(String[] args) {

        GoalFormul<String> goalFormul = new GoalFormul<String>() {

            @Override
            public boolean isGoal(State<String> t) {
                return (t.getValue() == "E");
            }
        };

        State n = new State("A");
        State c1 = new State("B");
        State c2 = new State("C");
        State c3 = new State("D");
        State c4 = new State("E");
        State c5 = new State("F");
        State c6 = new State("G");

        c2.setNextStates(new ArrayList() {{
            add(c5);
            add(c6);
        }});

        c1.setNextStates(new ArrayList() {{
            add(c3);
            add(c4);
        }});

        n.setNextStates(new ArrayList() {{
            add(c1);
            add(c2);
        }});

        Agent<String> agent = new Agent<>(n, goalFormul);
        agent.act(null);


        Search hill = new HillClimbSearch(new State("a"), new GoalFormul() {
            @Override
            public boolean isGoal(State t) {
                return false;
            }
        }, new ICost() {
            @Override
            public int costFunction(State s) {
                List<Integer> l = (List<Integer>) s.getValue();
                int cost = 0;
                for (int i = 1; i < l.size(); i++) {
                    for (int j = 0; j < i; j++) {
                        // 2 queens in one colomn or in cross
                        if (l.get(i) == l.get(j) | Math.abs(l.get(i) - l.get(j)) == Math.abs(i - j)) {
                            cost++;
                        }
                    }
                }
                return cost;
            }
        });

        System.out.println(hillFirstBest(new ArrayList<Integer>() {{
            for(int i = 0; i < 8; i++) {
                add(i);
            }
        }}));
    }

    static int cost(ArrayList<Integer> board) {
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

    ArrayList<Integer> randomBoard() {
        ArrayList<Integer> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int randomnumber = 0; // generate random number between 0 and n
            board.add(randomnumber);
        }
        return board;
    }

    ArrayList<Integer> hillRandomStart(ArrayList<Integer> startBoard) {
        ArrayList<Integer> temp = startBoard;
        while (cost(temp) != 0) {
            temp = randomBoard();
        }
        return temp;
    }



    static ArrayList<Integer> hillSteepestDescent(ArrayList<Integer> startBoard) {
        ArrayList<Integer> temp = (ArrayList<Integer>) startBoard.clone();
        int bestCost;
        while (cost(temp) != 0) {
            bestCost = cost(temp);
            for (int i = 0; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    int row = temp.get(i);
                    int newRow = row + j;
                    if (newRow > 7 && newRow % 7 != 0) {
                        newRow = (newRow % 7) - 1;
                    } else if (newRow > 7 && newRow % 7 == 0) {
                        newRow = 7;
                    }
                    temp.set(i, newRow);
                    if (cost(temp) > bestCost) {
                        temp.set(i, row);
                    }
                }
            }
        }
        return temp;
    }

    static ArrayList<Integer> hillFirstBest(ArrayList<Integer> startBoard) {
        ArrayList<Integer> temp = (ArrayList<Integer>) startBoard.clone();
        int bestCost;
        Random random = new Random();
        while (cost(temp) != 0) {
            bestCost = cost(temp);
            int i = random.nextInt(8);
            int j = random.nextInt(8);
            int row = temp.get(i);
            int newRow = row + j;
            if (newRow > 7 && newRow % 7 != 0) {
                newRow = (newRow % 7) - 1;
            } else if (newRow > 7 && newRow % 7 == 0) {
                newRow = 7;
            }
            temp.set(i, newRow);
            if (cost(temp) > bestCost) {
                temp.set(i, row);
            }
        }
        return temp;
    }
}
