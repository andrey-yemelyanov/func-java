package helvidios.javaexp;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.*;

public class FunctionTest{

    @FunctionalInterface
    interface Converter<F, T>{
        T convert(F from);
    }

    @Test
    public void FuncInterface(){
        Converter<String, Integer> converter = (s) -> Integer.valueOf(s);
        assertThat(converter.convert("321"), is(321));
    }

    @Test
    public void FuncInterfaceMethodRef(){
        Converter<String, Integer> converter = Integer::valueOf;
        assertThat(converter.convert("321"), is(321));
    }

    @Test
    public void Predicate(){
        // Represents a predicate (boolean-valued function) of one argument.
        Predicate<String> predicate = (s) -> s.startsWith("G");
        List<String> names = new ArrayList<>();
        names.add("Jane");
        names.add("Sydney");
        names.add("Gregg");
        names.add("Arnold");
        names.add("Ginger");
        assertThat(names.removeIf(predicate), is(true));
        assertThat(names, is(List.of("Jane", "Sydney", "Arnold")));
    }

    @Test
    public void ConverterFunction(){
        // Represents a function that accepts one argument and produces a result.
        Function<String, Integer> converter = Integer::valueOf;
        assertThat(converter.apply("123"), is(123));
    }

    @Test
    public void StringSupplier(){
        // Represents a supplier of results.
        Supplier<String> supplier = () -> "test";
        assertThat(supplier.get(), is("test"));
    }

    @Test
    public void StringConsumer(){
        StringBuilder sb = new StringBuilder();
        // performs an operation on a single input parameter
        Consumer<String> consumer = (s) -> sb.append(
            s + "->" + s.length() + "\n");
        consumer.accept("one");
        consumer.accept("two");
        consumer.accept("three");
        assertThat(sb.toString(), is("one->3\ntwo->3\nthree->5\n"));
    }

    @Test
    public void OptionalIfPresent(){
        StringBuilder sb = new StringBuilder();
        Optional<String> op = Optional.ofNullable(null);
        op.ifPresent(sb::append);
        assertThat(sb.length(), is(0));
        String str = op.orElse("N/A");
        assertThat(str, is("N/A"));
        op = Optional.of("HELLO");
        op.ifPresent(sb::append);
        assertThat(sb.toString(), is("HELLO")); 
    }
}