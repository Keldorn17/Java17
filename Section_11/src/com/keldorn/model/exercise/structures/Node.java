package com.keldorn.model.exercise.structures;

public class Node extends ListItem {
    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        return rightLink;
    }

    @Override
    ListItem setNext(ListItem listItem) {
        rightLink = listItem;
        return rightLink;
    }

    @Override
    ListItem previous() {
        return leftLink;
    }

    @Override
    ListItem setPrevious(ListItem listItem) {
        leftLink = listItem;
        return leftLink;
    }

    @Override
    int compareTo(ListItem listItem) {
        int result = -1;
        if (listItem != null) {
            result = ((String) this.getValue()).compareTo((String) listItem.getValue());
        }
        return result;
    }
}
