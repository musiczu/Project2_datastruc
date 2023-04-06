package com.project2;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class LightSolver {
    private Graph<PuzzleState, DefaultEdge> G;
    private Graph<String, DefaultEdge> Kawin_G;
    private int size_number;
    private int area;
    private PuzzleState startState;
    private PuzzleState endState;
    private String kawinsolution;
    private String initi al;

    public LightSolver(String input, int board_size) {

        initial = input;
        size_number = board_size;
        area = size_number * size_number;
        startState = new PuzzleState(initial);
        String sol = "";
        for (int i = 0; i < area; i++)
            sol = sol + "0";
        kawinsolution = sol;
        endState = new PuzzleState(sol);
        G = new SimpleDirectedGraph<>(DefaultEdge.class);
        Kawin_G.addVertex(kawinsolution);
    }

    public ArrayList<Integer> KawinSolve() {
        ShortestPathAlgorithm<String, DefaultEdge> shpath_1 = new DijkstraShortestPath<>(Kawin_G);
        ArrayList<String> sol = new ArrayList<>();
        ArrayList<Integer> solButton = new ArrayList<>();
        ArrayDeque<PuzzleState> q = new ArrayDeque<>();
        String temp = "";
        for (int i = 0; i < area; i++) {
            String newState = KawintoggledState(temp, i);
            // newState.setButton(i);
            if (!Kawin_G.containsVertex(newState)) {
                Kawin_G.addVertex(newState);
                q.add(newState);
            }
            Kawin_G.addEdge(temp, newState);
        }
        return null;
    }

    public ArrayList<Integer> Solve() {
        ShortestPathAlgorithm<PuzzleState, DefaultEdge> shortestPath = null;
        List<PuzzleState> sol = new ArrayList<>();
        ArrayList<Integer> solButton = new ArrayList<>();
        ArrayDeque<PuzzleState> q = new ArrayDeque<>();
        PuzzleState tempState = null;
        q.add(startState);
        while (!q.isEmpty()) {
            tempState = q.pop();
            if (!G.containsVertex(endState))
                G.addVertex(endState);
            if (!G.containsVertex(tempState))
                G.addVertex(tempState);
            for (int i = 0; i < area; i++) {
                PuzzleState newState = toggledState(tempState, i);
                newState.setButton(i);
                if (!G.containsVertex(newState)) {
                    G.addVertex(newState);
                    q.add(newState);
                }
                G.addEdge(tempState, newState);
            }
        }
        if (G.containsVertex(endState)) {
            sol = shortestPath.getPath(startState, endState).getVertexList();
            for (int i = 1; i < sol.size(); i++) {
                solButton.add(sol.get(i).getButton());
            }
            return solButton;
        } else {
            return null;
        }
    }

    /*
     * public ArrayList<Integer> Solve() {
     * ShortestPathAlgorithm<PuzzleState, DefaultEdge> shortestPath = null;
     * shortestPath = new DijkstraShortestPath<>(G);
     * List<PuzzleState> sol = new ArrayList<PuzzleState>();
     * ArrayList<Integer> solButton = new ArrayList<Integer>();
     * 
     * ArrayDeque<PuzzleState> q = new ArrayDeque<>();
     * PuzzleState tempState = null;
     * q.add(startState);
     * while (!q.isEmpty()) {
     * tempState = q.pop();
     * if (G.containsVertex(endState)) {
     * break;
     * }
     * if (!G.containsVertex(tempState)) {
     * G.addVertex(tempState);
     * }
     * for (int i = 0; i < area; i++) {
     * PuzzleState newState = toggledState(tempState, i);
     * newState.setButton(i);
     * if (!G.containsVertex(newState)) {
     * G.addVertex(newState);
     * q.add(newState);
     * }
     * G.addEdge(tempState, newState);
     * }
     * }
     * if (!G.containsVertex(endState)) {
     * return null;
     * } else {
     * sol = shortestPath.getPath(startState, endState).getVertexList();
     * for (int i = 1; i < sol.size(); i++) {
     * solButton.add(sol.get(i).getButton());
     * }
     * return solButton;
     * }
     * }
     */

    public void printLayout() {
        System.out.println("===========================Puzzle button layout========================");
        for (int k = 0; k < 6 * size_number + 1; k++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < area; i++) {
            if ((i + 1) % size_number == 0) {
                System.out.printf("| %-3d |", i + 1);
                System.out.println();
                for (int k = 0; k < 6 * size_number + 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            } else {
                System.out.printf("| %-3d ", i + 1);
            }
        }
    }

    public void printBoard(String s) {
        System.out.print("Bit string = ");
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
        }
        System.out.println();
        for (int k = 0; k < 6 * size_number + 1; k++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < s.length(); i++) {
            if ((i + 1) % size_number == 0) {
                System.out.printf("| %-3s |", s.charAt(i));
                System.out.println();
                for (int k = 0; k < 6 * size_number + 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            } else {
                System.out.printf("| %-3s ", s.charAt(i));
            }
        }
    }

    public void printSolution(ArrayList<Integer> Solution) {
        String newState;
        newState = initial;
        String switchButton;
        System.out.println("===========================Puzzle solution=============================");
        System.out.print("Puzzle solution: ");
        if (Solution != null) {
            for (int i = 0; i < Solution.size(); i++) {
                if (i != 0)
                    System.out.print("->");
                System.out.print(Solution.get(i));
            }
            System.out.println();
            System.out.println(Solution.size() + " moves to turn off all lights");
            System.out.println("Show solution:");
            for (int i = 0; i < Solution.size(); i++) {
                if (newState.charAt(Solution.get(i) - 1) == '1')
                    switchButton = "off";
                else
                    switchButton = "on";
                System.out.println(">>> Move " + (i + 1) + " : turn " + switchButton + " button " + Solution.get(i));
                PuzzleState temp = new PuzzleState(newState);
                newState = toggledState(temp, Solution.get(i) - 1).getState();
                printBoard(newState);
            }
        } else
            System.out.println("No solution");
        System.out.println("=====================The puzzle has been solved!=======================");
    }

    public String KawintoggledState(String p, int index) {
        int pos = index + 1;
        char state[] = p.toCharArray();
        state[index] = toggle(state[index]);
        if (pos % size_number != 0) {
            state[index + 1] = toggle(state[index + 1]);
        }
        if (pos % size_number != 1) {
            state[index - 1] = toggle(state[index - 1]);
        }
        if (pos <= area - size_number) {
            state[index + size_number] = toggle(state[index + size_number]);
        }
        if (pos > size_number) {
            state[index - size_number] = toggle(state[index - size_number]);
        }
        String newS = new String();
        newS.getChars(0, size_number, state, 0);
        return newS;
    }

    public PuzzleState toggledState(PuzzleState p, int index) {
        int pos = index + 1;
        String s = p.getState();
        char state[] = s.toCharArray();

        state[index] = toggle(state[index]);
        if (pos % size_number != 0) {
            state[index + 1] = toggle(state[index + 1]);
        }
        if (pos % size_number != 1) {
            state[index - 1] = toggle(state[index - 1]);
        }
        if (pos <= area - size_number) {
            state[index + size_number] = toggle(state[index + size_number]);
        }
        if (pos > size_number) {
            state[index - size_number] = toggle(state[index - size_number]);
        }

        PuzzleState newP = new PuzzleState(String.valueOf(state));
        return newP;
    }

    public char toggle(char a) {
        int n = Integer.parseInt(String.valueOf(a));
        n = (n + 1) % 2;
        return Character.forDigit(n, 10);
    }

    public PuzzleState getstartState() {
        return startState;
    }
}