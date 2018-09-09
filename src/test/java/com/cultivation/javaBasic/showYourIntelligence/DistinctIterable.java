package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final Iterator<E> iterator;
    private Set<E> currentSet = new HashSet<>(20);
    private int currentIndex = 0;
    private E currentNumber;
    private int size;

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
        this.size = 10;
    }

    @Override
    public boolean hasNext() {
        currentNumber = next();
        while (hasNext()) {
            if (currentSet.contains(currentNumber)){
                return false;
            }
            currentSet.add(currentNumber);
            return true;
        }
        currentIndex++;
        return currentIndex < size;
    }

    @Override
    public E next() {
        return currentNumber;
    }
    // --end->
}