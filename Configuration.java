package OptimizationBestFS.OptimizationByBestFS;

import java.util.List;

public abstract class Configuration<T extends Configuration, C> implements Comparable<Configuration> {
    private final long _score;
    public final C _config;
    static final int SAME = 0;
    static final int HIGHER = 1;
    static final int LOWER = -1;

    public long getScore() {
        return _score;
    }

    public C getConfig() {
        return _config;
    }

    public Configuration(C config) {
        _config = config;
        _score = computeScore();
    }

    public abstract List<T> generateNeighbors();

    public abstract int computeScore();

    public abstract String toString();

    public int compareTo(Configuration other) {
        if (getScore() > other.getScore()) {
            return HIGHER;
        } else if (getScore() < other.getScore()) {
            return LOWER;
        }
        return SAME;
    }

}
