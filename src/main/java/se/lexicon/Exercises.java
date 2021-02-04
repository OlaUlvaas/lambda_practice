package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> eriks = p -> p.getFirstName().equalsIgnoreCase("Erik");
        List<Person> names = storage.findMany(eriks);
        names.forEach(System.out::println);


        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> ladies = p -> p.getGender().equals(Gender.FEMALE);
        List<Person> ladyNames = storage.findMany(ladies);
        ladyNames.forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> bornAfter2000 = p -> p.getBirthDate().isAfter(LocalDate.of(1999,12,31));
        List<Person> youngPeople = storage.findMany(bornAfter2000);
        youngPeople.forEach(System.out::println );

        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> id123 = p -> p.getId() == 123;
        Person person123 = storage.findOne(id123);
        System.out.println(person123);

        System.out.println("----------------------");

    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message){
        System.out.println(message);

        //Write your code here
        Predicate<Person> id456 = p -> p.getId() == 456;
        Function<Person, String> fullName = p-> "Name: " + p.getFirstName() + " " + p.getLastName() + " born " + p.getBirthDate();

        String findPerson456 = storage.findOneAndMapToString(id456,fullName);
        System.out.println(findPerson456);



        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> startsWithE = p->p.getFirstName().startsWith("E")&& p.getGender().equals(Gender.MALE);
        Function<Person,String> pToStr = p-> p.getFirstName() + " " + p.getLastName();

        List<String> strList = storage.findManyAndMapEachToString(startsWithE,pToStr);
        strList.forEach(System.out::println);


        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> youngPeople = p-> Period.between(p.getBirthDate(),LocalDate.now()).getYears() < 10;
        Function<Person,String> youngToStr = p->p.getFirstName() + " " + p.getLastName() + " " + Period.between(p.getBirthDate(),LocalDate.now()).getYears() + " years";
        List<String> allYoungPeople = storage.findManyAndMapEachToString(youngPeople, youngToStr);
        allYoungPeople.forEach(System.out::println);

        System.out.println("----------------------");
//
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message){
        System.out.println(message);
        //Write your code here
        Predicate<Person> ulfs = p->p.getFirstName().equalsIgnoreCase("Ulf");
        Consumer<Person> ulfConsumer = p-> System.out.println(p.getFirstName() + " " + p.getLastName());
        storage.findAndDo(ulfs, ulfConsumer);


        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message){
        System.out.println(message);

        //Write your code here
        Predicate<Person> personsWithSame = p-> p.getLastName().contains(p.getFirstName());
        Consumer<Person> sameConsumer = p-> System.out.println(p.getFirstName() + " " + p.getLastName());
        storage.findAndDo(personsWithSame, sameConsumer);
        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message){
        System.out.println(message);
        //Write your code here
        //Predicate<Person> palindrome = p->p.getFirstName()
        //storage.findAndDo();
        Predicate<Person> isSame = p-> new StringBuilder(p.getFirstName()).reverse().toString().equalsIgnoreCase(p.getFirstName());
        Consumer<Person> printFullName = p-> System.out.println(p.getFirstName() + " " + p.getLastName());
        storage.findAndDo(isSame, printFullName);

        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message){
        System.out.println(message);
        //Write your code here



        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message){
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message){
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }
}
