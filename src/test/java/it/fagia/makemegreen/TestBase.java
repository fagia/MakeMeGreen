package it.fagia.makemegreen;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DynamicTest;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class TestBase<T, P, R> {

    @SafeVarargs
    final Stream<DynamicTest> buildTestStream(Function<P, T> dataBuilder, Function<T, R> actionExecutor, Pair<P, R>... testPairs) {
        return stream(testPairs)
                .map(testPair -> new ImmutablePair<>(dataBuilder.apply(testPair.getLeft()), testPair.getRight()))
                .map(testPair -> dynamicTest(testPair.getLeft().toString(),
                        () -> assertEquals(testPair.getRight(), actionExecutor.apply(testPair.getLeft()))));
    }
}
