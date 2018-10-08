package it.fagia.makemegreen;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

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
            String w1 = "word";
            String w2 = "another";
            String w3 = "last ";
            return buildTestStreamForValid(w1, w2, w3);
        }

        @TestFactory
        @DisplayName("should identify complex strings as invalid words")
        Stream<DynamicTest> notValid() {
            String n1 = "not valid";
            String n2 = "another not valid";
            String n3 = " one more not valid ";
            return buildTestStreamForNotValid(n1, n2, n3);
        }

        @TestFactory
        @DisplayName("should identify empty strings as invalid words")
        Stream<DynamicTest> empty() {
            String e1 = "";
            String e2 = "  ";
            return buildTestStreamForNotValid(e1, e2, null);
        }

        private Stream<DynamicTest> buildTestStreamForValid(String... validStrings) {
            return buildTestStream(true, validStrings);
        }

        private Stream<DynamicTest> buildTestStreamForNotValid(String... notValidStrings) {
            return buildTestStream(false, notValidStrings);
        }

        private Stream<DynamicTest> buildTestStream(boolean isValid, String... strings) {
            return Arrays.stream(strings)
                    .map(TestData::new)
                    .map(t -> dynamicTest(t.toString(), () -> assertEquals(isValid, t.isValid())));
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