package com.keldorn.model.exercise.structures;

public class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return root;
    }

    @Override
    public boolean addItem(ListItem listItem) {
        if (listItem == null) {
            root = listItem;
            return true;
        }

        ListItem currentItem = root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(listItem);
            if (comparison < 0) {
                if (currentItem.next() != null) {
                    currentItem.next();
                } else {
                    currentItem.setNext(listItem).setPrevious(currentItem);
                }
            } else if (comparison > 0) {
                if (currentItem.previous() != null) {
                    currentItem.previous().setNext(listItem).setPrevious(currentItem.previous());
                    listItem.setNext(currentItem).setPrevious(listItem);
                } else {
                    listItem.setNext(root).setPrevious(listItem);
                    root.setPrevious(listItem);
                    root = listItem;
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem listItem) {
        if (listItem != null) {
            ListItem currentItem = root;
            while (currentItem != null) {
                int comparison = currentItem.compareTo(listItem);
                if (comparison == 0) {
                    if (currentItem == root) {
                        root = currentItem.next();
                    } else {
                        currentItem.previous().setNext(currentItem.next());
                        if (currentItem.next() != null) {
                            currentItem.next().setPrevious(currentItem.previous());
                        }
                    }
                    return true;
                } else if (comparison < 0) {
                    currentItem = currentItem.next();
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void traverse(ListItem listItem) {
        if (root == null) {
            System.out.println("The list is empty");
            return;
        }
        while (root != null) {
            System.out.println(root.getValue());
            root = root.next();
        }
    }
}
