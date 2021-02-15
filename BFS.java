package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BFS {
    // BFS -- returns nodes in the order they were explored in (parent --> children)
    public static ArrayList<Integer> BFS (HashMap<Integer, ArrayList<Integer>> input, int start) {
        // initialization
        ArrayList<Integer> order = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        // while input not fully explored
        while (!(queue.isEmpty())) {
            int curr = queue.remove(0);
            // explore neighbors
            if (input.containsKey(curr)) {
                for (Integer n : input.get(curr)) {
                    if (!(visited.contains(n))) {
                        queue.add(n);
                        visited.add(n);
                    }
                }
            }
            order.add(curr);
        }
        // return nodes in increasing levels on tree
        return order;
    }
}
