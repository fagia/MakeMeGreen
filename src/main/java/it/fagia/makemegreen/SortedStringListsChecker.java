package it.fagia.makemegreen;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

class SortedStringListsChecker {
    private List<String> stringList;

    SortedStringListsChecker(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public String toString() {
        return stringList == null ? "null" : stringList.toString();
    }

    boolean isSorted() {
        throw new NotImplementedException("Implement me!");
    }
}
