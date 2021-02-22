package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFS {
    // DFS -- returns nodes in  topological order (parent before children) -- pre order
    public static ArrayList<Integer> DFS (HashMap<Integer, ArrayList<Integer>> input, int start) {
        // initialization
        ArrayList<Integer> order = new ArrayList<>();
        ArrayList<Integer> stack = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        stack.add(start);
        visited.add(start);
        // while input not fully explored
        while (!(stack.isEmpty())) {
            int curr = stack.remove(stack.size() - 1);
            // explore neighbors
            ArrayList<Integer> neighbors = input.get(curr);
            if (input.containsKey(curr)) {
                for (Integer n : neighbors) {
                    if (!(visited.contains(n))) {
                        stack.add(n);
                    }
                }
            }
            order.add(curr);
            visited.add(curr);
        }
        // return nodes in topological order
        return order;
    }
}
