package org.program.streamAPIconcepts.prepare;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomStatisticsCollector implements Collector<Integer, CustomStatisticsCollector.CustomStatistics, CustomStatisticsCollector.CustomStatistics> {

    static class CustomStatistics {
        private int sum = 0;
        private int product = 1;
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;
        private long count = 0;

        public void accept(int value) {
            sum += value;
            product *= value;
            min = Math.min(min, value);
            max = Math.max(max, value);
            count++;
        }

        public void combine(CustomStatistics other) {
            sum += other.sum;
            product *= other.product;
            min = Math.min(min, other.min);
            max = Math.max(max, other.max);
            count += other.count;
        }

        public int getSum() {
            return sum;
        }

        public int getProduct() {
            return product;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public long getCount() {
            return count;
        }

        public double getAverage() {
            return getCount() > 0 ? (double) getSum() / getCount() : 0.0d;
        }

        public double getRange() {
            return getMax() - getMin();
        }

        @Override
        public String toString() {
            return String.format(
                    "%s{count=%d, sum=%d, product=%d, min=%d, average=%f, max=%d}",
                    this.getClass().getSimpleName(),
                    getCount(),
                    getSum(),
                    getProduct(),
                    getMin(),
                    getAverage(),
                    getMax()
            );
        }
    }

    @Override
    public Supplier<CustomStatistics> supplier() {
        return CustomStatistics::new;
    }

    @Override
    public BiConsumer<CustomStatistics, Integer> accumulator() {
        return CustomStatistics::accept;
    }

    @Override
    public BinaryOperator<CustomStatistics> combiner() {
        return (stats1, stats2) -> {
            stats1.combine(stats2);
            return stats1;
        };
    }

    @Override
    public Function<CustomStatistics, CustomStatistics> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

}
