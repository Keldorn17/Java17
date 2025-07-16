package main.java.com.keldorn.model.hacker;

import main.java.com.keldorn.model.immutable.PersonImmutable;

public class PersonOfInterest extends PersonImmutable {

    public PersonOfInterest(PersonImmutable person) {
        super(person);
    }

//    @Override
//    public PersonImmutable[] getKids() {
//        return super.kids;
//    }
}
