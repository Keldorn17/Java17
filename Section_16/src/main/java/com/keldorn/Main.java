package main.java.com.keldorn;

import main.java.com.keldorn.external.child.Child;
import main.java.com.keldorn.external.domain.LivingPerson;
import main.java.com.keldorn.external.util.Logger;
import main.java.com.keldorn.external.util.Separator;
import main.java.com.keldorn.model.bank.Bank;
import main.java.com.keldorn.model.bank.BankAccount;
import main.java.com.keldorn.model.bank.BankCustomer;
import main.java.com.keldorn.model.bank.enums.AccountType;
import main.java.com.keldorn.model.consumer.specific.ChildClass;
import main.java.com.keldorn.model.game.GameConsole;
import main.java.com.keldorn.model.game.ShooterGame;
import main.java.com.keldorn.model.generic.BaseClass;
import main.java.com.keldorn.model.hacker.PersonOfInterest;
import main.java.com.keldorn.model.immutable.Person;
import main.java.com.keldorn.model.immutable.PersonImmutable;
import main.java.com.keldorn.model.immutable.PersonRecord;
import main.java.com.keldorn.model.parent.Generation;
import main.java.com.keldorn.model.parent.Parent;
import main.java.com.keldorn.model.parent.PersonConstructors;
import main.java.com.keldorn.model.person.PersonR;
import main.java.com.keldorn.model.pirate.PirateGame;
import main.java.com.keldorn.model.unmodifiable.Student;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        finalExplored();
        immutableClasses();
        personRecord();
        personImmutable();
        copyingClasses();
        unmodifiableCollections();
        bankTest();
        constructorsProject();
        pirateGame();
    }

    private static void finalExplored() {
        Separator.separator();
        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        Separator.separator();
        childReferredToAsBase.recommendedMethod();
        Separator.separator();
        child.recommendedMethod();

        Separator.separator();
        parent.recommendedStatic();
        Separator.separator();
        childReferredToAsBase.recommendedStatic();
        Separator.separator();
        child.recommendedStatic();

        Separator.separator();
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
        Separator.separator();
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
        Separator.separator();
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

    private static void personImmutable() {
        Separator.separator();
        PersonImmutable jane = new PersonImmutable("Jane", "01/01/1930");
        PersonImmutable jim = new PersonImmutable("Jim", "02/02/1932");
        PersonImmutable joe = new PersonImmutable("Joe", "03/03/1934");

        PersonImmutable[] johnsKids = {jane, jim, joe};
        PersonImmutable john = new PersonImmutable("John", "05/05/1900", johnsKids);
        System.out.println(john);

        PersonImmutable[] kids = john.getKids();
        kids[0] = jim;
        kids[1] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(john);

        johnsKids[0] = new PersonImmutable("Ann", "04/04/1936");
        System.out.println(john);

        LivingPerson johnLiving = new LivingPerson(john.getName(), john.getKids());
        System.out.println(johnLiving);

        LivingPerson anne = new LivingPerson("Ann", null);
        johnLiving.addKid(anne);
        System.out.println(johnLiving);

        PersonOfInterest johnCopy = new PersonOfInterest(john);
        System.out.println(johnCopy);

        kids = johnCopy.getKids();
        kids[1] = anne;
        System.out.println(johnCopy);
        System.out.println(john);
    }

    private static void copyingClasses() {
        Separator.separator();

        PersonR joe = new PersonR("Joe", "01/01/1961", null);
        PersonR jim = new PersonR("Jim", "02/02/1962", null);
        PersonR jack = new PersonR("Jack", "03/03/1963", new PersonR[] {joe, jim});
        PersonR jane = new PersonR("Jane", "04/04/1964", null);
        PersonR jill = new PersonR("Jill", "05/05/1965", new PersonR[] {joe, jim});

        PersonR[] persons = {joe, jim, jack, jane, jill};
//        PersonR[] personsCopy = persons.clone();
//        PersonR[] personsCopy = Arrays.copyOf(persons, persons.length);
        PersonR[] personsCopy = new PersonR[5];
        Arrays.setAll(personsCopy, i -> new PersonR(persons[i]));
//        for (int i = 0; i < 5; i++) {
//            personsCopy[i] = new PersonR(persons[i]);
//        }

        var jillsKids = personsCopy[4].kids();
        jillsKids[1] = jane;

        for (int i = 0; i < 5; i++) {
            if (persons[i] == personsCopy[i]) {
                System.out.println("Equal References " + persons[i]);
            }
        }
        System.out.println(persons[4]);
        System.out.println(personsCopy[4]);
    }

    private static void unmodifiableCollections() {
        Separator.separator();
        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        List<Student> students = new ArrayList<>(List.of(bob, bill));
        List<Student> studentsFirstCopy = new ArrayList<>(students);
        List<Student> studentsSecondCopy = List.copyOf(students);
        List<Student> studentsThirdCopy = Collections.unmodifiableList(students);

        studentsFirstCopy.add(new Student("Bonnie", new StringBuilder()));
//        studentsThirdCopy.set(0, new Student("Bonnie", new StringBuilder()));
        studentsFirstCopy.sort(Comparator.comparing(Student::getName));
        students.add(new Student("Bonnie", new StringBuilder()));
        bobsNotes.append("Bob was one of mz first students.");

        StringBuilder bonniesNotes = studentsFirstCopy.get(2).getStudentNotes();
        bonniesNotes.append("Bonnie is taking 3 of my courses");

        students.forEach(System.out::println);
        Separator.separator();
        studentsFirstCopy.forEach(System.out::println);
        Separator.separator();
        studentsSecondCopy.forEach(System.out::println);
        Separator.separator();
        studentsThirdCopy.forEach(System.out::println);
        Separator.separator();
    }

    private static void bankTest() {
        Separator.separator();
        Bank bank = new Bank(123456);
        bank.addCustomer("Joe", 500, 10000);

        BankCustomer joe = bank.getCustomer(bank.getCustomerId("joe"));
        System.out.println(joe);

        bank.doTransaction(joe.getCustomerId().toString(), AccountType.CHECKING, 35);
        System.out.println(joe);

        bank.doTransaction(joe.getCustomerId().toString(), AccountType.CHECKING, -535);
        System.out.println(joe);

        BankAccount checking = joe.getAccount(AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));

    }

    private static void constructorsProject() {
        Separator.separator();
        Parent parent = new Parent("Jane Doe", "01/01/1950", 4);
        Child child = new Child();

        System.out.println("Parent: " + parent);
        System.out.println("Child: " + child);

        PersonConstructors joe = new PersonConstructors("Joe", "01-01-1950");
        System.out.println(joe);

        PersonConstructors joeCopy = new PersonConstructors(joe);
        System.out.println(joeCopy);

        Generation g = Generation.BABY_BOOMER;
    }

    private static void shooterGame() {
        Separator.separator();
        var console = new GameConsole<>(new ShooterGame("The Shootout Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }

    private static void pirateGame() {
        Separator.separator();
//        Weapon weapon = Weapon.getWeaponByChar('P');
//        System.out.println("weapon = " + weapon + ", hitPoints = " +
//                weapon.getHitPoints() + ", minLevel = " + weapon.getMinLevel());
//
//        var list = Weapon.getWeaponsByLevel(1);
//        list.forEach(System.out::println);
//
//        Pirate tim = new Pirate("Tim");
//        System.out.println(tim);
//
//        Objects.requireNonNull(PirateGame.getTowns(0)).forEach(t -> System.out.println(t.information()));
//        Separator.separator();
//        Objects.requireNonNull(PirateGame.getTowns(1)).forEach(t -> System.out.println(t.information()));
//
//        Separator.separator();
        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}
