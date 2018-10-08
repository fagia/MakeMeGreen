package it.fagia.makemegreen;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

class SortedStringListsChecker {

    private List<String> stringList;

    SortedStringListsChecker(List<String> stringList) {
        this.stringList = stringList;
    }

    boolean isSorted() {
        throw new NotImplementedException("Implement me!"); // TODO
    }

    List<String> getStringList() {
        return stringList;
    }
}
