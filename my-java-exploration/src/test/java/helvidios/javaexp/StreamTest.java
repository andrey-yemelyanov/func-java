package helvidios.javaexp;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.hamcrest.MatcherAssert.*;

public class StreamTest{
    
    @Test
    public void Filter(){
        List<Integer> filtered = List.of(1,2,3,4,5,6,7,8,9)
                                     .stream()
                                     .filter(i -> i % 2 == 0)
                                     .collect(Collectors.toList());
        assertThat(filtered, is(List.of(2,4,6,8)));
    }

    @Test
    public void Sorted(){
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9)
                                 .stream()
                                 .sorted((i, j) -> Integer.compare(j, i))
                                 .collect(Collectors.toList());
        assertThat(list, is(List.of(9,8,7,6,5,4,3,2,1)));
    }

    @Test
    public void Map(){
        List<String> list = List.of(2,3,4)
                                 .stream()
                                 .map(i -> String.format("%d^2=%d", i, i * i))
                                 .collect(Collectors.toList());
        assertThat(list, is(List.of(
            "2^2=4",
            "3^2=9",
            "4^2=16"
        )));
    }

    @Test
    public void AllMatch(){
        boolean allLessThan10 = List.of(1,2,3,4,5)
                                    .stream()
                                    .allMatch((i) -> i < 10);
        assertThat(allLessThan10, is(true));
    }

    @Test
    public void Count(){
        long nOddNumbers = List.of(1,2,3,4,5,6,7,8,9)
                               .stream()
                               .filter((i) -> i % 2 != 0)
                               .count();
        assertThat(nOddNumbers, is(5L));
    }

    @Test
    public void Reduce(){
        int sum = List.of(1,2,3,4,5,6,7,8,9)
                      .stream()
                      .reduce(0, (i, j) -> i + j);
        assertThat(sum, is(45));
    }

    @Test
    public void BuildFreqTable(){
        List<Integer> list = List.of(1,2,3,1,4,4,4,4,1,2);
        Map<Integer, Integer> freq = new HashMap<>();
        list.stream()
            .forEach((i) -> freq.put(i, freq.getOrDefault(i, 0) + 1));
        assertThat(freq, is(Map.of(
            1,3,
            2,2,
            3,1,
            4,4
        )));
    }

    @Test
    public void PrimitiveIntStream(){
        class Person{
            int age;
            public Person(int age){
                this.age = age;
            }
        }
        List<Person> list = IntStream.range(0, 100)
                                     .mapToObj((i) -> new Person(i))
                                     .collect(Collectors.toList());
        assertThat(list.size(), is(100));
        for(int i = 0; i < 100; i++){
            assertThat(list.get(i).age, is(i));
        }
    }
}