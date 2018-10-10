package it.fagia.makemegreen;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@DisplayName("List utils")
class ListUtilsTests {

    @Nested
    @DisplayName("string occurrences counter")
    class StringOccurrenceCounterTests extends TestBase<ListUtilsTests.TestData, List<String>, Integer> {

        @TestFactory
        @DisplayName("should count the occurrences of a matching string in a list")
        Stream<DynamicTest> occurrenceOfString() {
            String aMatchingString = "a string";
            Pair<List<String>, Integer> o1 = new ImmutablePair<>(asList("a string", "another string", "one more string"), 1);
            Pair<List<String>, Integer> o2 = new ImmutablePair<>(asList("lorem", "ipsum"), 0);
            Pair<List<String>, Integer> o3 = new ImmutablePair<>(asList("lorem", "a string", "ipsum", "a string"), 2);
            Pair<List<String>, Integer> o4 = new ImmutablePair<>(null, null);
            return buildTestStream(aMatchingString, o1, o2, o3, o4);
        }

        @TestFactory
        @DisplayName("should ignore in the count the occurrences of a partially matching string")
        Stream<DynamicTest> occurrenceOfPartiallyMatchingString() {
            String aPartiallyMatchingString = "string";
            Pair<List<String>, Integer> o1 = new ImmutablePair<>(asList("a string", "another string", "one more string"), 0);
            Pair<List<String>, Integer> o2 = new ImmutablePair<>(asList("lorem", "ipsum"), 0);
            return buildTestStream(aPartiallyMatchingString, o1, o2);
        }

        @SafeVarargs
        private final Stream<DynamicTest> buildTestStream(String aString, Pair<List<String>, Integer>... testPairs) {
            return buildTestStream(TestData::new, testData -> testData.countOccurrencesOf(aString), testPairs);
        }

    }

    @Nested
    @DisplayName("string lists checker")
    class StringListsCheckerTests extends TestBase<ListUtilsTests.TestData, List<String>, Boolean> {

        @TestFactory
        @DisplayName("should check that these lists are sorted")
        Stream<DynamicTest> sorted() {
            Pair<List<String>, Boolean> s1 = new ImmutablePair<>(asList("a) string", "b) string", "c) string"), true);
            Pair<List<String>, Boolean> s2 = new ImmutablePair<>(asList("1. string", "2. string", "3. string"), true);
            Pair<List<String>, Boolean> s3 = new ImmutablePair<>(singletonList("only one string"), true);
            return buildTestStream(s1, s2, s3);
        }

        @TestFactory
        @DisplayName("should check that these lists aren't sorted")
        Stream<DynamicTest> notSorted() {
            Pair<List<String>, Boolean> u1 = new ImmutablePair<>(asList("c) string", "a) string", "b) string"), false);
            Pair<List<String>, Boolean> u2 = new ImmutablePair<>(asList("2. string", "1. string", "3. string"), false);
            Pair<List<String>, Boolean> u3 = new ImmutablePair<>(null, false);
            return buildTestStream(u1, u2, u3);
        }

        @TestFactory
        @DisplayName("should check that these lists are sorted in a case insensitive way")
        Stream<DynamicTest> sortedCaseInsensitive() {
            Pair<List<String>, Boolean> i1 = new ImmutablePair<>(asList("a) string", "B) string", "c) string", "D) string"), true);
            Pair<List<String>, Boolean> i2 = new ImmutablePair<>(asList("A) string", "b) string", "C) string", "d) string"), true);
            Pair<List<String>, Boolean> i3 = new ImmutablePair<>(asList("A) string", "B) string", "C) string", "D) string"), true);
            return buildTestStream(i1, i2, i3);
        }

        @SafeVarargs
        private final Stream<DynamicTest> buildTestStream(Pair<List<String>, Boolean>... testPairs) {
            return buildTestStream(TestData::new, TestData::isSorted, testPairs);
        }

    }

    class TestData extends ListUtils {
        TestData(List<String> stringList) {
            super(stringList);
        }

        @Override
        public String toString() {
            return getStringList() == null ? "<null>" : getStringList().toString();
        }
    }
}