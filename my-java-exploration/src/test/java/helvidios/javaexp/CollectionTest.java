package helvidios.javaexp;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.*;
import static org.hamcrest.MatcherAssert.*;

public class CollectionTest{
    
    @Test
    public void CollectionsNCopies(){
        List<Integer> list = Collections.nCopies(10, 1);
        assertThat(list, is(Arrays.asList(1,1,1,1,1,1,1,1,1,1)));
    }

    @Test
    public void CollectionsSortWithLambda(){
        List<String> list = Arrays.asList(
            "abr",
            "abracadabra",
            "abra",
            "ab",
            "abbabasbbbdbfbg"
        );
        // sort by string len in descending order
        Collections.sort(list, 
            (s1, s2) -> Integer.compare(s2.length(), s1.length()));
        assertThat(list, is(
            Arrays.asList(
                "abbabasbbbdbfbg",
                "abracadabra",
                "abra",
                "abr",
                "ab"
            )
        ));
    }

    @Test
    public void CollectionRemoveIf(){
        List<String> names = new ArrayList<>(Arrays.asList(
            "Jack",
            "Andy",
            "Sandy",
            "Alice"
        ));
        // remove names starting with 'A'
        names.removeIf(name -> name.startsWith("A"));
        assertThat(names, is(
            Arrays.asList(
                "Jack",
                "Sandy"
            )
        ));
    }

    @Test
    public void CollectionsBinarySearchWithLambda(){
        class Person{
            int age;
            String name;
            Person(String name, int age){
                this.name = name;
                this.age = age;
            }
        }
        List<Person> people = Arrays.asList(
            new Person("Jack", 15),
            new Person("Jane", 19),
            new Person("Sydney", 22),
            new Person("Sally", 35),
            new Person("Dick", 40)
        );
        int i = Collections.binarySearch(
            people, 
            new Person("Anybody", 22), 
            (Person p1, Person p2) -> Integer.compare(p1.age, p2.age)
        );
        assertThat(people.get(i).name, is("Sydney"));
    }

    @Test
    public void CollectionsSwap(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Collections.swap(list, 0, 4);
        assertThat(list, is(Arrays.asList(5, 2, 3, 4, 1)));
    }    

    @Test
    public void CollectionsMax(){
        class Person{
            int age;
            String name;
            Person(String name, int age){
                this.name = name;
                this.age = age;
            }
        }
        List<Person> people = Arrays.asList(
            new Person("Jack", 15),
            new Person("Jane", 19),
            new Person("Sydney", 22),
            new Person("Sally", 35),
            new Person("Dick", 40)
        );
        // find oldest person
        Person oldest = Collections.max(people, 
            (p1, p2) -> Integer.compare(p1.age, p2.age)
        );
        assertThat(oldest.name, is("Dick"));
    }

    @Test
    public void MapForEach(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "ONE");
        map.put(2, "TWO");
        map.put(3, "THREE");

        map.forEach((k, v) -> System.out.println(k + "->" + v));
    }

    @Test
    public void ArraysCopyOfRange(){
        int[] arr = new int[] {1, 2, 3, 4, 5, 6};
        int[] subArr = Arrays.copyOfRange(arr, 2, 5);
        assertThat(subArr, is(new int[] {3, 4, 5}));
    }
    
    @Test
    public void ArraysFill(){
        int[] arr = new int[5];
        Arrays.fill(arr, 10);
        assertThat(arr, is(new int[] {10,10,10,10,10}));
    }

    @Test
    public void ArraysSumAndAvgArray(){
        int[] arr = new int[]{1,2,3,4,5};
        int sum = Arrays.stream(arr).sum();
        double avg = Arrays.stream(arr).average().orElse(0);
        assertThat(sum, is(15));
        assertThat(avg, is(3.0));
    }
}