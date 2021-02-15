package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFS {
    // DFS -- returns nodes in reverse topological order (children before parent)
    public static ArrayList<Integer> DFS (HashMap<Integer, ArrayList<Integer>> input, int start) {
        // initialization
        ArrayList<Integer> order = new ArrayList<>();
        boolean next;
        ArrayList<Integer> stack = new ArrayList<>();
        stack.add(start);
        HashSet<Integer> explored = new HashSet<>();
        explored.add(start);
        // while input not fully explored
        while (!(stack.isEmpty())) {
            int curr = stack.get(stack.size() - 1);
            next = false;
            // explore neighbors
            ArrayList<Integer> neighbors = input.get(curr);
            for (Integer n : neighbors) {
                if (!(explored.contains(n))) {
                    next = true;
                    stack.add(n);
                    explored.add(n);
                    break;
                }
            }
            // remove if nothing new added to stack
            if (!next) {
                order.add(curr);
                stack.remove(stack.size() - 1);
            }
        }
        // return nodes in reverse topological order
        return order;
    }
}
