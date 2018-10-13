package it.fagia.makemegreen;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Word utils")
class WordUtilsTests {

    @Nested
    @DisplayName("valid word checker")
    class ValidWordCheckerTests extends TestBase<WordUtilsTests.TestData, String, Boolean> {

        @TestFactory
        @DisplayName("should identify simple strings as valid words")
        Stream<DynamicTest> valid() {
            Pair<String, Boolean> w1 = new ImmutablePair<>("word", true);
            Pair<String, Boolean> w2 = new ImmutablePair<>("another", true);
            Pair<String, Boolean> w3 = new ImmutablePair<>("bird ", true);
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
            return buildTestStream(TestData::new, TestData::isValid, testPairs);
        }

    }

    @Nested
    @DisplayName("palindrome word checker")
    class PalindromeWordCheckerTests extends TestBase<WordUtilsTests.TestData, String, Boolean> {

        @TestFactory
        @DisplayName("should check that these are palindrome words")
        Stream<DynamicTest> palindrome() {
            Pair<String, Boolean> p1 = new ImmutablePair<>("noon", true);
            Pair<String, Boolean> p2 = new ImmutablePair<>("civic", true);
            Pair<String, Boolean> p3 = new ImmutablePair<>("tattarrattat", true);
            return buildTestStream(p1, p2, p3);
        }

        @TestFactory
        @DisplayName("should check that these aren't palindrome words")
        Stream<DynamicTest> notPalindrome() {
            Pair<String, Boolean> n1 = new ImmutablePair<>("", false);
            Pair<String, Boolean> n2 = new ImmutablePair<>("   ", false);
            Pair<String, Boolean> n3 = new ImmutablePair<>(null, false);
            Pair<String, Boolean> n4 = new ImmutablePair<>("not", false);
            Pair<String, Boolean> n5 = new ImmutablePair<>("not palindrome", false);
            return buildTestStream(n1, n2, n3, n4, n5);
        }

        @SafeVarargs
        private final Stream<DynamicTest> buildTestStream(Pair<String, Boolean>... testPairs) {
            return buildTestStream(TestData::new, TestData::isPalindrome, testPairs);
        }

    }

    @Nested
    @DisplayName("word reverser")
    class WordReverserTests extends TestBase<WordUtilsTests.TestData, String, String> {

        @Nested
        @DisplayName("for not valid words")
        class NotValidWordsTests extends TestBase<WordUtilsTests.TestData, String, Class> {

            @Test
            @DisplayName("should throw exception if not valid word")
            void notValid() {
                WordUtils wordUtils = new WordUtils("not valid");
                assertThrows(IllegalArgumentException.class, wordUtils::getReversed);
            }

        }

        @Nested
        @DisplayName("for valid words")
        class ValidWordsTests extends TestBase<WordUtilsTests.TestData, String, String> {

            @TestFactory
            @DisplayName("should reverse the following words")
            Stream<DynamicTest> notPalindrome() {
                Pair<String, String> r1 = new ImmutablePair<>("hello", "olleh");
                Pair<String, String> r2 = new ImmutablePair<>("world", "dlrow");
                Pair<String, String> r3 = new ImmutablePair<>("a", "a");
                Pair<String, String> r4 = new ImmutablePair<>("tattarrattat", "tattarrattat");
                return buildTestStream(r1, r2, r3, r4);
            }

            @SafeVarargs
            private final Stream<DynamicTest> buildTestStream(Pair<String, String>... testPairs) {
                return buildTestStream(TestData::new, TestData::getReversed, testPairs);
            }

        }

    }

    class TestData extends WordUtils {
        TestData(String word) {
            super(word);
        }

        @Override
        public String toString() {
            return getWord() == null ? "<null>" : (StringUtils.isBlank(getWord()) ? "<blank>" : getWord());
        }
    }
}