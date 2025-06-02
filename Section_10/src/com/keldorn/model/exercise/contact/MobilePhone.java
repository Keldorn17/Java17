package com.keldorn.model.exercise.contact;

import java.util.ArrayList;

public class MobilePhone {
    private final String myNumber;
    private final ArrayList<Contact> myContacts;
    private final int NOT_FOUND_CONTACT = -1;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact) {
        boolean saved = false;
        if (findContact(contact.getName()) == NOT_FOUND_CONTACT) {
            saved = true;
            myContacts.add(contact);
        }
        return saved;
    }

    public boolean updateContact(Contact toUpdate, Contact withUpdate) {
        boolean updated = false;
        int index;
        if ((index = findContact(toUpdate)) != NOT_FOUND_CONTACT) {
            updated = true;
            myContacts.set(index, withUpdate);
        }
        return updated;
    }

    public boolean removeContact(Contact contact) {
        boolean deleted = false;
        int index;
        if ((index = findContact(contact)) != NOT_FOUND_CONTACT) {
            deleted = true;
            myContacts.remove(index);
        }
        return deleted;
    }

    private int findContact(Contact contact) {
        return myContacts.indexOf(contact);
    }

    private int findContact(String name) {
        int index = NOT_FOUND_CONTACT;
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Contact queryContact(String name) {
        int index = findContact(name);
        return index != NOT_FOUND_CONTACT ? myContacts.get(index) : null;
    }

    public void printContacts() {
        System.out.println("Contact List:");
        int index = 1;
        for (var contact : myContacts) {
            System.out.printf("%d. %s -> %s%n", index++, contact.getName(), contact.getPhoneNumber());
        }
    }
}
