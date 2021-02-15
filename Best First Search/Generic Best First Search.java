package OptimizationBestFS.OptimizationByBestFS;

import com.google.common.collect.MinMaxPriorityQueue;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenericBestFirstSearch<T extends Configuration> {
    private static final int DEFAULT_MAX_HEAP_SIZE = 100000;
    private static final int NUMBER_ELEMENTS_TO_REMOVE = 10000;
    private final Set<String> inHeap = new HashSet<>();

    public T search(T seed) {
        return search(seed, DEFAULT_MAX_HEAP_SIZE);
    }

    public T search(T seed, int maxHeapSize) {
        MinMaxPriorityQueue<T> heap = MinMaxPriorityQueue
                .orderedBy(Comparator.comparing(T::getScore,
                        Long::compareTo))
                .create();
        heap.add(seed);
        T best = null;
        while (!heap.isEmpty()) {
            T cur = heap.poll();
            inHeap.remove(cur.toString());
            if (best == null || best.compareTo(cur) > 0) {
                best = cur;
                System.out.println(best);
            }
            if (heap.size() <= maxHeapSize) {
                @SuppressWarnings("unchecked")
                List<T> neighbors = (List<T>) cur.generateNeighbors();
                for (T neighbor : neighbors) {
                    if (heap.size() > maxHeapSize) {
                        // heap contains too many items, remove low ranking items
                        for (int i = 0; i < NUMBER_ELEMENTS_TO_REMOVE; i++) {
                            T item = heap.removeLast();
                            inHeap.remove(item.toString());
                        }
                    }
                    if (!inHeap.contains(neighbor.toString())) {
                        heap.add(neighbor);
                        inHeap.add(neighbor.toString());
                    }
                }
            }
        }
        return best;
    }
}
