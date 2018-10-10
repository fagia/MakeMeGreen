package it.fagia.makemegreen;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

class ListUtils {

    private List<String> stringList;

    ListUtils(List<String> stringList) {
        this.stringList = stringList;
    }

    boolean isSorted() {
        throw new NotImplementedException("Implement me!"); // TODO
    }

    int countOccurrencesOf(String aString) {
        throw new NotImplementedException("Implement me!"); // TODO
    }

    List<String> getStringList() {
        return stringList;
    }
}
