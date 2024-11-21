package com.example.operator.util;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Operator<T> {
    private final Collection<T> collection;

    private Operator(Collection<T> collection) {
        this.collection = collection;
    }

    public static <T> Operator<T> modify(Collection<T> collection) {
        return new Operator<>(collection);
    }

    public Operator<T> add(T element) {
        collection.add(element);
        return this;
    }

    public Operator<T> add(Collection<? extends T> elements) {
        collection.addAll(elements);
        return this;
    }

    public Operator<T> remove(Predicate<? super T> predicate) {
        collection.removeIf(predicate);
        return this;
    }

    public Operator<T> sort(Comparator<? super T> comparator) {
        if (collection instanceof List) {
            ((List<T>) collection).sort(comparator);
        } else {
            throw new UnsupportedOperationException("Sorting is supported only for List.");
        }
        return this;
    }

    public Operator<T> each(Consumer<? super T> consumer) {
        collection.forEach(consumer);
        return this;
    }


    public <C extends Collection<T>> Operator<T> copyTo(Supplier<C> collectionSupplier) {
        C newCollection = collectionSupplier.get();
        newCollection.addAll(collection);
        return new Operator<>(newCollection);
    }

    public <R, C extends Collection<R>> Operator<R> convertTo(Supplier<C> collectionSupplier, Function<? super T, ? extends R> mapper) {
        C newCollection = collectionSupplier.get();
        collection.stream().map(mapper).forEach(newCollection::add);
        return new Operator<>(newCollection);
    }

    public <C extends Collection<T>> C get() {
        return (C) collection;
    }
}
