package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl(){
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance(){
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for(Person person : personList){
            if(filter.test(person)){
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
         for(Person person : personList){
             if(filter.test(person)){
                 return person;
             }
         }
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString){
        Person findPerson = findOne(filter);
        if(findPerson != null){
            return personToString.apply(findPerson);
        }
        return null;
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString){
        List<Person> personList = findMany(filter);
        List<String> stringList = new ArrayList<>();
        for(Person person : personList){
            String convert = personToString.apply(person);
            stringList.add(convert);
        }
        return stringList;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer){
        for(Person person : findMany(filter)){
            consumer.accept(person);
        }
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator){
       List<Person> returnList = new ArrayList<>(personList);
       Collections.sort(returnList, comparator);
       return returnList;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator){
        List<Person> returnList = findMany(filter);
        returnList.sort(comparator);
        return returnList;
    }
}
