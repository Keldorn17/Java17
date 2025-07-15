package main.java.com.keldorn;

import main.java.com.keldorn.external.util.Logger;
import main.java.com.keldorn.external.util.Separator;
import main.java.com.keldorn.model.consumer.specific.ChildClass;
import main.java.com.keldorn.model.generic.BaseClass;
import main.java.com.keldorn.model.immutable.Person;
import main.java.com.keldorn.model.immutable.PersonRecord;

public class Main {

    public static void main(String[] args) {
        finalExplored();
        immutableClasses();
        personRecord();
    }

    private static void finalExplored() {
        Separator.SEPARATOR();
        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        Separator.SEPARATOR();
        childReferredToAsBase.recommendedMethod();
        Separator.SEPARATOR();
        child.recommendedMethod();

        Separator.SEPARATOR();
        parent.recommendedStatic();
        Separator.SEPARATOR();
        childReferredToAsBase.recommendedStatic();
        Separator.SEPARATOR();
        child.recommendedStatic();

        Separator.SEPARATOR();
        BaseClass.recommendedStatic();
        ChildClass.recommendedStatic();

        String xArgument = "This is all I've got to say about Section ";
        StringBuilder zArgument = new StringBuilder("Only saying this: Section ");
        doXYZ(xArgument, 16, zArgument);
        System.out.println("After Method, xArgument: " + xArgument);
        System.out.println("After Method, zArgument: " + zArgument);

        StringBuilder tracker = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker.toString());
        tracker.append(", Step 2 is xyz");
        Logger.logToConsole(tracker.toString());
        System.out.println("After logging, tracker = " + tracker);
    }

    private static void doXYZ(String x, int y, final StringBuilder z) {
        final String c = x + y;
        System.out.println("c = " + c);
        x = c;
        z.append(y);
    }

    private static void immutableClasses() {
        Separator.SEPARATOR();
        Person jane = new Person("Jane", "01/01/1930");
        Person jim = new Person("Jim", "02/02/1932");
        Person joe = new Person("Joe", "03/03/1934");

        Person[] johnsKids = {jane, jim, joe};
        Person john = new Person("John", "05/05/1900", johnsKids);
        System.out.println(john);

        john.setKids(new Person[] {new Person("Ann", "04/04/1930")});
        System.out.println(john);

        Person[] kids = john.getKids();
        kids[0] = jim;
        System.out.println(john);

        kids = null;
        System.out.println(john);
        john.setKids(kids);
        System.out.println(john);
    }

    private static void personRecord() {
        Separator.SEPARATOR();
        PersonRecord jane = new PersonRecord("Jane", "01/01/1930");
        PersonRecord jim = new PersonRecord("Jim", "02/02/1932");
        PersonRecord joe = new PersonRecord("Joe", "03/03/1934");

        PersonRecord[] johnsKids = {jane, jim, joe};
        PersonRecord john = new PersonRecord("John", "05/05/1900", johnsKids);
        System.out.println(john);

        PersonRecord johnCopy = new PersonRecord("John", "05/05/1900");
        System.out.println(johnCopy);

        PersonRecord[] kids = johnCopy.kids();
        kids[0] = jim;
        kids[1] = new PersonRecord("Ann", "04/04/1936");
        System.out.println(johnCopy);

        johnsKids[0] = new PersonRecord("Ann", "04/04/1936");
        System.out.println(john);
    }
}
