package library;

import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra {
    // DIJKSTRA -- finds shortest path distance, outputs MAX_VALUE if no path is found
    // input: tail maps to hashmap of neighbors (head, length)
    public static HashMap<Integer, Integer> shortestPath (HashMap<Integer, HashMap<Integer, Integer>> input, int start) {
        HashMap<Integer, Integer> distances = new HashMap<>();
        ArrayList<int[]> visited = new ArrayList<>();
        for (int i = 1; i <= input.size(); i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        ArrayList<int[]> queue = new ArrayList<>();
        int[] begin = {start, 0};
        queue.add(begin);
        int curr = queue.get(0)[0];
        int currLength = queue.get(0)[1];

        while (!(queue.isEmpty())) {
            HashMap<Integer, Integer> neighbors = input.get(curr);
            for (Integer n : neighbors.keySet()) {
                addEdge(queue, currLength, neighbors, n, visited);
            }
            queue.remove(0);
            boolean added = false;
            while (!(queue.isEmpty()) && !added) {
                curr = queue.get(0)[0];
                currLength = queue.get(0)[1];
                if (distances.get(curr) < currLength) {
                    queue.remove(0);
                }
                else {
                    distances.put(curr, currLength);
                    added = true;
                }
            }
        }
        return distances;
    }

    // adds neighbor to queue (DIJKSTRA)
    public static void addEdge (ArrayList<int[]> queue, int currLength, HashMap<Integer, Integer> neighbors, int endPoint, ArrayList<int[]> visited) {
        int edge = neighbors.get(endPoint);
        int[] newPoint = {endPoint, edge + currLength};
        if (!(contained(visited, newPoint))) {
            int index = 0;
            while (index < queue.size() && queue.get(index)[1] <= newPoint[1]) {
                index++;
            }
            queue.add(index, newPoint);
            visited.add(newPoint);
        }
    }

    // checks if queue contains element
    public static boolean contained (ArrayList<int[]> visited, int[] newPoint) {
        for (int[] element : visited) {
            if (newPoint[0] == element[0] && newPoint[1] == element[1]) {
                return true;
            }
        }
        return false;
    }
}
