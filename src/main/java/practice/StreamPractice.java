import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;


public class StreamPractice {


    /**
     * Get average of numbers that are located on odd indexes.
     * Each selected number is divided by 2 before averaging.
     *
     * Example:
     * [6, 2, 3, 7, 2, 5]
     * odd indexes → 2, 7, 5
     * after /2 → 1, 3.5, 2.5
     * average → 2.333...
     *
     * If there are no elements on odd indexes → throw NoSuchElementException.
     */
    public Double getOddNumsAverage(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .filter(i -> i % 2 == 1)
                .mapToDouble(numbers::get)
                .map(n -> n / 2.0)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }
}