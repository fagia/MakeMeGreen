package it.fagia.makemegreen;

import org.apache.commons.lang3.NotImplementedException;

class WordUtils {

    private String word;

    WordUtils(String word) {
        this.word = word;
    }

    boolean isValid() {
        throw new NotImplementedException("Implement me!"); // TODO
    }

    boolean isPalindrome() {
        throw new NotImplementedException("Implement me!"); // TODO
    }

    String getWord() {
        return word;
    }
}
