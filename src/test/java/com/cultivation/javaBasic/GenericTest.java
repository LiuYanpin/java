package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.Manager;
import com.cultivation.javaBasic.util.Pair;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;

import static com.oracle.javafx.jmx.json.JSONDocument.createArray;
import static javafx.scene.input.KeyCode.T;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericTest {
    @SuppressWarnings("unused")
    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};

        // TODO: please call getMiddle method for string
        // <--start
        final String middle = getMiddle(words);
        // --end-->

        assertEquals("Good", middle);
    }

    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        int minimumInteger = min(new Integer[]{1, 2, 3});
        double minimumReal = min(new Double[]{1.2, 2.2, -1d});

        assertEquals(1, minimumInteger);
        assertEquals(-1d, minimumReal, 1.0E-05);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
        //generic type
    }

    @Test
    void should_get_equal() {
        KeyValuePair<String, String> newPair = new KeyValuePair<>("Hello", "world");
        KeyValuePair anotherPair = newPair;
        KeyValuePair<Integer, Integer> thirdPair = anotherPair;
//        String value = thirdPair.getValue();

    }
    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused", "ConstantConditions"})
    @Test
    void should_be_careful_of_raw_type_generic() {
        Pair<Manager> managerPair = new Pair<>();
        Pair rawPair = managerPair;
        rawPair.setFirst(new Employee());

        boolean willThrow = false;
        try {
            Manager first = managerPair.getFirst();//转换失败。转换时错误。
        } catch (ClassCastException error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), willThrow);
    }

    @Test
    void should_swap() {
        Pair<String> pair = new Pair<>("Hello", "World");

        swap(pair);

        assertEquals("World", pair.getFirst());
        assertEquals("Hello", pair.getSecond());
    }

    @SuppressWarnings("unused")
    private static <T> T getMiddle(T[] args) {
        return args[args.length / 2];
    }

    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    @SuppressWarnings("unused")
    //Your Generic Parameter should extend Number and implements Comparable,
    //which means class A is a Number and Comparable.
    private static <T extends Number & Comparable<T>> T min(T[] values) {
        T minValue = values[0];

        for (int i = 0; i < values.length; i++) {
            if (minValue.compareTo(values[i]) > 0){
                minValue = values[i];
            }
        }
        return minValue;
    }


    @Test
    void should_get_length() {
//        assertEquals(5, toArray(5, (lenght) -> new String[lenght]));
        Class<?> itemClass = createArray(5).getClass().getComponentType();

    }



    //生成一个array,接口创建array
    //返回T[]
    private <T> T[] toArray(int length, CreateSomeArray<T> createSomeArray) {
        return createSomeArray.genericArray(length);
    }

    private <T> T getArrayLength(int length) {
        return (T)(Object)length;
    }
    // --end-->
    /*public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (next.compareTo(candidate) > 0)
                candidate = next;
        }
        return candidate;
    }*/

    // TODO: please implement following method to pass the test. But you cannot change the signature
    // <--start
    @SuppressWarnings("unused")
    private static void swap(Pair<?> pair) {//<?>为了表示自己是一个泛型，
        Object first = pair.getFirst();
        Object second = pair.getSecond();
        ((Pair)pair).setFirst(second);
        ((Pair)pair).setSecond(first);
    }
    private static void swap2(Pair<? extends Number> pair) {//<?>为了表示自己是一个泛型，
        Object first = pair.getFirst();
        Object second = pair.getSecond();
        ((Pair)pair).setFirst(second);
        ((Pair)pair).setSecond(first);
    }
    private static void swap3(Pair<? super Integer> pair) {//<?>为了表示自己是一个泛型，
        Object first = pair.getFirst();
        Object second = pair.getSecond();
        ((Pair)pair).setFirst(second);
        ((Pair)pair).setSecond(first);
    }

    @Test
    void should_swap_String() {
        Pair<Number> pair = new Pair<>(1, 2);
        swap2(pair);
        assertEquals(1, pair.getFirst());
        assertEquals(2, pair.getSecond());
        Class<?> T = Integer.class;
        ArrayList<Integer> list = getArrayList();
        ArrayList<? super Integer> anotherList = new ArrayList<>();

    }

    private <T> ArrayList getArrayList() {
        ArrayList<T> list = new ArrayList<>();

        return list;
    }






    // TODO: You can add additional method within the range if you like
    // <--start
//    private static void genericSwap
    // --end-->
}

interface CreateSomeArray<T>{
        T[] genericArray(int length);
}

/*
 * - Can you give an example when you have to specify a typed parameter in generic method call?
 * - Can type parameter have more than one bounds? Can type parameter bounds to class? Can type parameter bounds to both
 *   class and interface?
 * - What if you have 2 class that one is generic called `KeyValuePair<K, V>` and the second is a non-generic method
 *   called `KeyValuePair` in the same package?
 * - Can you use primitive types as type parameter?
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   String key = value.getKey();
 *   ```
 * - Please describe the wildcard generic type.
 */