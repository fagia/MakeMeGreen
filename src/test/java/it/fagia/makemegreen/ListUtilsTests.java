package it.fagia.makemegreen;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("List utils")
class ListUtilsTests {

    @Nested
    @DisplayName("string lists checker")
    class StringListsCheckerTests {

        @TestFactory
        @DisplayName("should check that these lists are sorted")
        Stream<DynamicTest> sorted() {
            List<String> s1 = asList("a) string", "b) string", "c) string");
            List<String> s2 = asList("1. string", "2. string", "3. string");
            List<String> s3 = singletonList("only one string");
            return buildTestStreamForSorted(s1, s2, s3);
        }

        @TestFactory
        @DisplayName("should check that these lists aren't sorted")
        Stream<DynamicTest> notSorted() {
            List<String> u1 = asList("c) string", "a) string", "b) string");
            List<String> u2 = asList("2. string", "1. string", "3. string");
            return buildTestStreamForNotSorted(u1, u2, null);
        }

        @TestFactory
        @DisplayName("should check that these lists are sorted in a case insensitive way")
        Stream<DynamicTest> sortedCaseInsensitive() {
            List<String> i1 = asList("a) string", "B) string", "c) string", "D) string");
            List<String> i2 = asList("A) string", "b) string", "C) string", "d) string");
            List<String> i3 = asList("A) string", "B) string", "C) string", "D) string");
            return buildTestStreamForSorted(i1, i2, i3);
        }

        @SafeVarargs
        private final Stream<DynamicTest> buildTestStreamForSorted(List<String>... sortedStringLists) {
            return buildTestStream(true, sortedStringLists);
        }

        @SafeVarargs
        private final Stream<DynamicTest> buildTestStreamForNotSorted(List<String>... unsortedStringLists) {
            return buildTestStream(false, unsortedStringLists);
        }

        @SafeVarargs
        private final Stream<DynamicTest> buildTestStream(boolean isSorted, List<String>... stringLists) {
            return stream(stringLists)
                    .map(TestData::new)
                    .map(t -> dynamicTest(t.toString(), () -> assertEquals(isSorted, t.isSorted())));
        }

    }

    private class TestData extends ListUtils {
        TestData(List<String> stringList) {
            super(stringList);
        }

        @Override
        public String toString() {
            return getStringList() == null ? "<null>" : getStringList().toString();
        }
    }

}