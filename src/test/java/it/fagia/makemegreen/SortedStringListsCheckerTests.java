package it.fagia.makemegreen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@DisplayName("Sorted string lists checker")
class SortedStringListsCheckerTests {

    @TestFactory
    @DisplayName("should check that these lists are sorted")
    Stream<DynamicTest> sorted() {
        List<String> s1 = Arrays.asList("a) string", "b) string", "c) string");
        List<String> s2 = Arrays.asList("1. string", "2. string", "3. string");
        List<String> s3 = Collections.singletonList("only one string");
        return buildSortedTestStream(s1, s2, s3);
    }

    @TestFactory
    @DisplayName("should check that these lists aren't sorted")
    Stream<DynamicTest> notSorted() {
        List<String> u1 = Arrays.asList("c) string", "a) string", "b) string");
        List<String> u2 = Arrays.asList("2. string", "1. string", "3. string");
        return buildNotSortedTestStream(u1, u2, null);
    }

    @TestFactory
    @DisplayName("should check that these lists are sorted in a case insensitive way")
    Stream<DynamicTest> sortedCaseInsensitive() {
        List<String> i1 = Arrays.asList("a) string", "B) string", "c) string", "D) string");
        List<String> i2 = Arrays.asList("A) string", "b) string", "C) string", "d) string");
        List<String> i3 = Arrays.asList("A) string", "B) string", "C) string", "D) string");
        return buildSortedTestStream(i1, i2, i3);
    }

    @SafeVarargs
    private final Stream<DynamicTest> buildSortedTestStream(List<String>... sortedStringLists) {
        return buildTestStream(true, sortedStringLists);
    }

    @SafeVarargs
    private final Stream<DynamicTest> buildNotSortedTestStream(List<String>... unsortedStringLists) {
        return buildTestStream(false, unsortedStringLists);
    }

    @SafeVarargs
    private final Stream<DynamicTest> buildTestStream(boolean isSorted, List<String>... stringLists) {
        return Arrays.stream(stringLists)
                .map(stringList -> buildTestData(stringList, isSorted))
                .map(data -> DynamicTest.dynamicTest(
                        data.objectUnderTest.toString(),
                        () -> Assertions.assertEquals(data.isSorted, data.objectUnderTest.isSorted())));
    }

    private TestData buildTestData(List<String> strings, boolean isSorted) {
        return new TestData(new SortedStringListsChecker(strings), isSorted);
    }

    private class TestData {
        private SortedStringListsChecker objectUnderTest;
        private boolean isSorted;

        TestData(SortedStringListsChecker objectUnderTest, boolean isSorted) {
            this.objectUnderTest = objectUnderTest;
            this.isSorted = isSorted;
        }
    }
}