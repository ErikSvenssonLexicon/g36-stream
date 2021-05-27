package org.example.stream_lecture;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.model.PersonDTO;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Examples {

    public <T,R> Collection<R> streamAndCollect(Collection<T> source, Function<T, R> mapper, Supplier<Collection<R>> supplier){
        return source.stream()
                .map(mapper)
                .collect(Collectors.toCollection(supplier));
    }

    public <T,R> Collection<R> streamAndCollect(Collection<T> source, Predicate<T> filter, Function<T, R> mapper, Supplier<Collection<R>> supplier){
        return source.stream()
                .filter(filter)
                .map(mapper)
                .collect(Collectors.toCollection(supplier));
    }

    public long countElementsInStream(Collection<Person> people){
        return people.stream()
                .count();
    }

    public Optional<Person> getYoungestPerson(Collection<Person> people){
        return people.stream()
                .min((p1, p2) -> p1.getAge() - p2.getAge());
    }

    public Optional<Person> getOldestPerson(Collection<Person> people){
        return people.stream()
                .max(Comparator.comparingInt(Person::getAge));
    }

    public String combineToSingleString(String...strings){
        StringBuilder stringBuilder = Stream.of(strings)
                .collect(
                        () -> new StringBuilder(), //Supplier
                        (sb, string) -> sb.append(string), //Accumulator
                        (sb1, sb2) -> sb1.append(sb2) //Combiner
                );
        return stringBuilder.toString();
    }

    public Person findById(Collection<Person> source, int id){
        return source.stream()
                .filter(person -> person.getId() == id)
                .findFirst() //Stream terminates and returns an Optional<Person>
                .orElseThrow(RuntimeException::new); //Should really be named getOrElseThrow
    }

    public LocalDate[] getYearOf(int year){
        return Stream.iterate(LocalDate.ofYearDay(year, 1), localDate -> localDate.plusDays(1))
                .limit(Year.of(year).isLeap() ? 366 : 365)
                .toArray(LocalDate[]::new);
    }

    public Collection<PersonDTO> findAndConvert(final Collection<Person> source, final String lastName){
        return source.stream()
                .filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .map(person -> new PersonDTO(person.getId(), person.getFirstName(), person.getLastName()))
                .collect(Collectors.toList());
    }

    public Map<Month, List<LocalDate>> getMapByMonth(LocalDate...dates){
        return Stream.of(dates)
                .collect(Collectors.groupingBy(LocalDate::getMonth));
    }

    public Map<Boolean, List<Person>> divideByGender(Collection<Person> people){
        return people.stream()
                .collect(Collectors.partitioningBy(person -> person.getGender() == Gender.FEMALE));
    }

    public void sequential(String...strings){
        Stream.of(strings)
                .parallel()
                .forEach(s -> {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " prints: " + s);
                });
    }
}
