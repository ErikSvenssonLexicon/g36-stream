package org.example;

import org.example.model.Person;
import org.example.stream_lecture.Examples;
import org.example.util.PersonGenerator;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Collection<Person> PEOPLE = PersonGenerator.getInstance().generate(1000);


    public static void main( String[] args )
    {
        Examples examples = new Examples();

        //System.out.println(examples.countElementsInStream(PEOPLE));
        //System.out.println(examples.getYoungestPerson(PEOPLE));

        /*
        Person person = examples.getOldestPerson(PEOPLE)
               .orElseThrow(IllegalArgumentException::new);

        System.out.println(person);
         */


        //System.out.println(examples.combineToSingleString("Collect", " is", " tricky"," to"," understand"));

        //LocalDate[] yearsDates = examples.getYearOf(2021);

        //Map<Month, List<LocalDate>> monthMap = examples.getMapByMonth(yearsDates);

        //monthMap.forEach(((month, localDates) -> localDates.forEach(System.out::println)));

        //examples.findAndConvert(PEOPLE, "svensson").forEach(System.out::println);

        //Map<Boolean, List<Person>> personByGender = examples.divideByGender(PEOPLE);

        //personByGender.get(true).forEach(System.out::println);

        examples.sequential("A", "B", "C", "D", "E");


    }
}
