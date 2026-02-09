package practice;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import model.Candidate;
import model.Person;

public class StreamPractice {

    public int findMinEvenNumber(List<String> numbers) {
        return numbers.stream()
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter(n -> n % 2 == 0)
                .min()
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Can't get min value from list: " + numbers));
    }

    public Double getOddNumsAverage(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .filter(i -> i % 2 == 1)
                .map(numbers::get)
                .filter(n -> n % 2 != 0)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Person> selectMenByAge(List<Person> peopleList,
                                       int fromAge,
                                       int toAge) {
        return peopleList.stream()
                .filter(p -> p.getSex() == Person.Sex.MAN
                        && p.getAge() >= fromAge
                        && p.getAge() <= toAge)
                .toList();
    }

    public List<Person> getWorkablePeople(int fromAge,
                                          int femaleToAge,
                                          int maleToAge,
                                          List<Person> peopleList) {
        return peopleList.stream()
                .filter(p -> p.getAge() >= fromAge
                        && ((p.getSex() == Person.Sex.MAN && p.getAge() <= maleToAge)
                        || (p.getSex() == Person.Sex.WOMAN && p.getAge() <= femaleToAge)))
                .toList();
    }

    public List<String> getCatsNames(List<Person> peopleList,
                                     int femaleAge) {
        return peopleList.stream()
                .filter(p -> p.getSex() == Person.Sex.WOMAN
                        && p.getAge() >= femaleAge)
                .flatMap(p -> p.getCats() == null
                        ? Stream.empty()
                        : p.getCats().stream())
                .map(cat -> cat.getName())
                .toList();
    }

    public List<String> validateCandidates(List<Candidate> candidates) {
        CandidateValidator validator = new CandidateValidator();

        return candidates.stream()
                .filter(validator)
                .map(Candidate::getName)
                .sorted()
                .toList();
    }
}

