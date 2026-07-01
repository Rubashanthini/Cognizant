import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


record Person(String name, int age) {}

class RecordsExample {
    public static void main(String[] args) {

      
        Person p1 = new Person("Ravi", 20);
        Person p2 = new Person("Priya", 17);
        Person p3 = new Person("Arun", 25);

       
        System.out.println("Person Details:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

       
        List<Person> people = Arrays.asList(p1, p2, p3);

        
        List<Person> adults = people.stream()
                                    .filter(person -> person.age() >= 18)
                                    .collect(Collectors.toList());

       
        System.out.println("\nAdults:");

        for (Person person : adults) {
            System.out.println(person);
        }
    }
}