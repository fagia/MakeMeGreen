package it.fagia.makemegreen;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Word utils")
class WordUtilsTests {

    @Nested
    @DisplayName("valid word checker")
    class StringListsCheckerTests {

        @TestFactory
        @DisplayName("should identify simple strings as valid words")
        Stream<DynamicTest> valid() {
            Pair<String, Boolean> w1 = new ImmutablePair<>("word", true);
            Pair<String, Boolean> w2 = new ImmutablePair<>("another", true);
            Pair<String, Boolean> w3 = new ImmutablePair<>("last ", true);
            return buildTestStream(w1, w2, w3);
        }

        @TestFactory
        @DisplayName("should identify complex strings as invalid words")
        Stream<DynamicTest> notValid() {
            Pair<String, Boolean> n1 = new ImmutablePair<>("not valid", false);
            Pair<String, Boolean> n2 = new ImmutablePair<>("another not valid", false);
            Pair<String, Boolean> n3 = new ImmutablePair<>(" one more not valid ", false);
            return buildTestStream(n1, n2, n3);
        }

        @TestFactory
        @DisplayName("should identify empty strings as invalid words")
        Stream<DynamicTest> empty() {
            Pair<String, Boolean> e1 = new ImmutablePair<>("", false);
            Pair<String, Boolean> e2 = new ImmutablePair<>("  ", false);
            Pair<String, Boolean> e3 = new ImmutablePair<>(null, false);
            return buildTestStream(e1, e2, e3);
        }

        @SafeVarargs
        private final Stream<DynamicTest> buildTestStream(Pair<String, Boolean>... testPairs) {
            return stream(testPairs)
                    .map(testPair -> new ImmutablePair<>(new TestData(testPair.getLeft()), testPair.getRight()))
                    .map(testPair -> dynamicTest(testPair.getLeft().toString(),
                            () -> assertEquals(testPair.getRight(), testPair.getLeft().isValid())));
        }

    }

    private class TestData extends WordUtils {
        TestData(String word) {
            super(word);
        }

        @Override
        public String toString() {
            return getWord() == null ? "<null>" : (StringUtils.isBlank(getWord()) ? "<blank>" : getWord());
        }
    }
}