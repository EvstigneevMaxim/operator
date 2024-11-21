package com.example.operator.service;

import com.example.operator.util.Operator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OperatorService {

    public List<String> processCollection(List<String> input) {
        return Operator.modify(input)
                .add("New Element")
                .remove(s -> s.length() < 3)
                .sort(Comparator.naturalOrder())
                .get();
    }

    public Map<String, Object> exampleUsage() {
        List<String> input = Arrays.asList("abc", "de", "f");
        List<String> result1 = Operator.modify(input)
                .add("1234")
                .add(input)
                .add("zzzzz")
                .get();

        Set<Integer> inputSet = new LinkedHashSet<>(Set.of(12345, 23456, 34567, 45678, 56789));
        Predicate<Number> greaterThan30000 = i -> i.doubleValue() > 30000;
        Set<Integer> result2 = Operator.modify(inputSet)
                .remove(greaterThan30000)
                .add(99999)
                .get();

        return Map.of(
                "Example List", result1,
                "Example Set", result2
        );
    }
}
