package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.cultivation.javaBasic.util.KeyValuePair;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        /*1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();*/
        // --end-->
        {
            assertIterableEquals(
                words,
                wordStream.collect(toList())
            );
            assertFalse(wordStream instanceof Iterable);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Stream.of(words);
        // --end-->
        {
            assertArrayEquals(
                words,
                wordStream.toArray(String[]::new)
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();
        //static <T> Stream <T> empty(){}
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }


    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> infiniteEchos = Stream.generate(() -> "Echo");
        //String[] infiniteEchosArray = Stream.generate(() -> "Echo").toArray((length) -> new String[length]);
        Stream<String> infiniteEchos2 = Stream.generate(() -> "Echo");
        //Stream<String> infiniteEchos = Stream.generate(() -> "Echo").limit(10002);//answer
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @Test
    void should_be_able_to_generic_array_when_given_a_length() {
        String[] genericArrays = Stream.generate(() ->
        {
            System.out.println("Echo");
            return "Echo";
        }).limit(10).toArray((length) -> new String[length]);

        Stream.generate(() -> "Hello").close();

    }
    /*
    只有必须用到值的时候才能使用。
    Intermediate：
    map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、
    limit、 skip、 parallel、 sequential、 unordered
    Terminal：
    forEach、 forEachOrdered、 toArray、 reduce、 collect、
    min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
    Short-circuiting：
    anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
    */

    @Test
    void should_be_able_to_excute_once() {
        final int[] time = {0};
        Stream.generate(() ->
        {
            time[0] = time[0] + 1;
            return "Echo";
        }).limit(10).filter((s) -> s.contains("c"));
        assertEquals(0, time[0]);

        Stream.generate(() -> {
            return "Echo";
        }).skip(10).findFirst().get();
        //闭包里面的字段必须是final的。

    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.iterate(0, n -> n + 1);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());//执行了10000次，初始值的时候0不执行，执行9999次，findFirst一次，
        }
    }
    @Test
    void should_get_array() {
        int[] i = {0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, (n) -> {
            i[0] = i[0] + 1;
            return n = n + 1;
        });
        infiniteSequence.skip(10000).findFirst();
        assertEquals(10000, i[0]);
        //0的时候不执行
    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(word -> word.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map(String::toUpperCase);
        // --end-->
        {
            assertArrayEquals(
                new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                new AnimeCharacter[] {
                    new AnimeCharacter("Naruto"),
                    new AnimeCharacter("Kisuke"),
                    new AnimeCharacter("Tomoya")
                },
                actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {
        Stream<Stream<Character>> nameStream = Stream
            .of("Naruto", "Kisuke", "Tomoya")
            .map(StreamingTest::letters);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> flatted = nameStream.flatMap(x -> x);
        // --end-->
        {
            assertArrayEquals(
                new Character[] {
                    'N', 'a', 'r', 'u', 't', 'o', 'K', 'i', 's', 'u', 'k',
                    'e', 'T', 'o', 'm', 'o', 'y', 'a'
                },
                flatted.toArray(Character[]::new));
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
        // --end-->
        {
            assertArrayEquals(
                letters("HelloWorld").toArray(Character[]::new),
                concatStream.toArray(Character[]::new)
            );
        }
    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                new Character[] {'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
            .iterate(0, i -> i + 1)
            .limit(20)
            .filter(v -> v % 2 == 0)
            .peek(v -> holder.setValue(holder.getValue() + v));
        //peek方法方法会使用一个Consumer消费流中的元素，但是返回的流还是包含原来的流中的元素。

        hookStream.forEach(i -> {});

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = new NoSuchElementException().getClass();
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = (() -> empty.orElseThrow(IllegalStateException::new));
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = (opt) -> {
            if (opt.isPresent()){
                result.add(opt.get().toUpperCase());
            }
        };
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = (optionalS -> {
            if (optionalS.isPresent()) {
                result.add(optionalS.get().toUpperCase());
            }
            return Optional.ofNullable(Optional.of(optionalS.isPresent()).orElseThrow(IllegalStateException::new));
        });
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 4)
            .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 1)
            .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = (optionalStream) -> {
            return optionalStream.findFirst().map((yieldOptional) -> {
                return yieldOptional.get();
            }).orElseGet(() -> Optional.empty());
        };
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        Stream<String> stream = Stream.of("Hello", "What", "is", "your", "name");

        // TODO: please implement toList collector using `stream.collect`.
        // You cannot use existing `toList` collector.
        // <--start
//        ArrayList<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> list =  stream.collect(
                ArrayList::new,
                List::add,
                List::addAll
        );
        // --end-->

        assertEquals(ArrayList.class, list.getClass());
        assertIterableEquals(
            Arrays.asList("Hello", "What", "is", "your", "name"),
            list
        );
    }

    @Test
    void should_concat_two_array() {
        ArrayList<String> resultArray = new ArrayList<>();
        List<String> originList1 = Arrays.asList("Java", "Python", "C", "Php");
        List<String> originList2 = Arrays.asList("Html", "Ruby", "C++", "JavaScript");

        ArrayList<String> dest1 = new ArrayList<>();
        ArrayList<String> dest2 = new ArrayList<>();
        ArrayList<String> dest3 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            dest2.add(originList1.get(i));
        }

        for (int i = originList1.size() - 2; i < originList1.size(); i++) {
            dest2.add(originList1.get(i));
        }
    }

    @Test
    void should_copy_to_array() {
        List<String> originList = Arrays.asList("Hello", "World");
        List<String> result = collectToString(originList);//唯一的抽象方法
        assertIterableEquals(originList, result);
    }

    private List<String> collectToString(List<String> arrayList) {
        List<String> result = new ArrayList<>();
        for (String item: arrayList
             ) {
            result.add(item);
        }
        return result;
    }


    @SuppressWarnings({"ConstantConditions", "unused"})
           /*Map<Integer, String> result1 = list.stream().collect(
                Collectors.toMap(Hosting::getId, Hosting::getName));

        System.out.println("Result 1 : " + result1);

        // key = name, value - websites
        Map<String, Long> result2 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebsites));

        System.out.println("Result 2 : " + result2);

        // Same with result1, just different syntax
        // key = id, value = name
        Map<Integer, String> result3 = list.stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x.getName()));

        System.out.println("Result 3 : " + result3);*/
    /*<R> collect(Supplier<R> resultSupplier,
                BiConsumer<R, T> accumulator,
                BiConsumer<R, R> combiner)*/
            /*Set<String> uniqueStrings = strings.stream()
            .collect(HashSet::new,
                    HashSet::add,
                    HashSet::addAll)*/
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
        HashMap<String, Integer> map =
                stream.collect(
                        HashMap::new,
                        (hashMap, currentItem) -> {
                            hashMap.put(currentItem.getKey(), currentItem.getValue());
                        },
                        HashMap::putAll
                );
//        HashMap<String, Integer> map =
//                stream.collect(Collectors.toMap(Function.identity(), //如何生成key
//                (k, v) ->  v ));//如何生成value

        /*一个能创建目标类型实例的方法，例如HashSet的构造函数。
        添加元素到目标中的方法，例如add方法。
        将两个对象整合到一起的方法。*/
        // --end-->
        //System.out.println(map);
        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());

    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = stream.collect(
                HashMap::new,
                (currentHashMap, currentItemToBeAdd) -> {
                    String keyOfCurrentItemToBeAdd = currentItemToBeAdd.getKey();
                    Integer valueOfCurrentItemToBeAdd = currentItemToBeAdd.getValue();

                    if (currentHashMap.containsKey(keyOfCurrentItemToBeAdd)) {
                        List<Integer> currentValuesOfKey = currentHashMap.get(keyOfCurrentItemToBeAdd);
                        currentValuesOfKey.add(valueOfCurrentItemToBeAdd);
                        currentHashMap.put(keyOfCurrentItemToBeAdd, currentValuesOfKey);
                    }else {
                        List<Integer> valueOfCurrentItem = new ArrayList<>();
                        valueOfCurrentItem.add(valueOfCurrentItemToBeAdd);
                        currentHashMap.put(keyOfCurrentItemToBeAdd, valueOfCurrentItem);
                    }
                },
                (finallyHashMap, hashMapToBeMerge) -> {
                    hashMapToBeMerge.forEach((String keyOfHashMapToBeMerge, List<Integer> valuesOfHashMapToBeMerge) -> {
                        if (finallyHashMap.containsKey(keyOfHashMapToBeMerge)) {
                            List<Integer> valuesOfFinallyHashMap = finallyHashMap.get(keyOfHashMapToBeMerge);
                            valuesOfFinallyHashMap.addAll(valuesOfHashMapToBeMerge);
                            finallyHashMap.put(keyOfHashMapToBeMerge, valuesOfFinallyHashMap);
                        }else {
                            finallyHashMap.put(keyOfHashMapToBeMerge, valuesOfHashMapToBeMerge);
                        }
                    });
                }
/*        @Override
        public BinaryOperator<Map<K, List<T>>> combiner() {
            return (m1, m2) -> {
                m2.forEach((k, v) -> {
                    List<T> value = Optional.ofNullable(m1)
                            .map(m -> m.get(k)).orElse(Lists.newArrayList());
                    value.addAll(v);
                    m1.put(k, value);
                });

                return m1;
            };
        }*/

        );
        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }


    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();//| BrE ˈparəlɛl, AmE ˈpɛrəˌlɛl |

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
        /*Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,
                Supplier<M> mapFactory,
                Collector<? super T, A, D> downstream)*/
        //Map<String, List<Integer>> map = stream.collect(Collectors.groupingBy(
        Map<String, List<Integer>> map = stream.collect(
                Collectors.groupingBy(
                        (keyValuePair) -> keyValuePair.getKey(),
                        Collectors.mapping(
                                item -> item.getValue()
                                , Collectors.toList())
                )
        );
                /*stream.collect(Collectors.groupingBy(
                (keyValuePair) -> keyValuePair.getKey(),
                HashMap::new,
                Collectors.mapping((x) -> x.getKey(), toList())
                )
        ));*/
        /*
        public static <T, K, D, A, M extends Map<K, D>>
    Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,
                                  Supplier<M> mapFactory,
                                  Collector<? super T, A, D> downstream)
                                  */
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
        //singletonList(T) 方法用于返回一个只包含指定对象的不可变列表。
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = stream.collect(Collectors.groupingBy(
                item -> item.getKey(),
                Collectors.counting()
        ));
        Map<String, Integer> map2 = stream.collect(Collectors.groupingBy(
                item -> item.getKey(),
                Collectors.reducing(
                        0,
                        word -> 1,
                        (counting1, counting2) -> counting1 + counting2
                )
        ));
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @Test
    void should_collect_number_and_group_them() {
        ArrayList<String> originArrayList = new ArrayList<>();
        originArrayList.add("Hello");
        originArrayList.add("World");
        Stream<ArrayList> arrayListStream = Stream.of(originArrayList);
        ArrayList<String> arrayList = null;

        Map<String, Integer> actualMap = arrayListStream.collect(Collectors.groupingBy(
                item -> item.toString(),
                HashMap::new,
                Collectors.reducing(
                        0,
                        item -> 1,
                        Integer::sum
                )
        ));
    }

    @Test
    void should_collect_number_and_group_them_by_divide_two() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
        Map<String, List<Integer>> result = integerStream.collect(Collectors.groupingBy(
                (item) -> {
                    int divideLeft = item % 2;
                    return new Integer(divideLeft).toString();
                },
                toList()
        ));
        System.out.println(result.get("1"));
        List<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(3);
        expect.add(5);
        assertArrayEquals(expect.toArray(), result.get("1").toArray());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        //这种先将元素分组的收集器叫做上游收集器，
        //之后执行其他运算的收集器叫做下游收集器(downstream Collector)。
        // <--start
        Map<String, Integer> map = stream.collect(Collectors.groupingBy(
                item -> item.toString(),
                HashMap::new,
                Collectors.reducing(
                        0,
                        item -> 1,
                        Integer::sum
                )
        ));/*stream.collect(Collectors
                .groupingBy(
                        item -> item.getKey(),
                        Collectors.summarizingInt(x -> getValue(new Optional.of(0)))));//下游收集器*/
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
            .iterate(1, i -> i + 1)
            .limit(100)
            .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = Optional.ofNullable(numbers.stream().mapToInt(x -> x).reduce(0, Integer::sum));

        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = words.stream().map(x -> x.length()).reduce(0, Integer::sum);
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */