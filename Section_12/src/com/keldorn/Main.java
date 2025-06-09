package com.keldorn;

import com.keldorn.dto.Affiliation;
import com.keldorn.dto.BaseballPlayer;
import com.keldorn.dto.FootballPlayer;
import com.keldorn.model.generics.LPAStudent;
import com.keldorn.model.generics.LPAStudentComparator;
import com.keldorn.model.generics.Students;
import com.keldorn.model.location.*;
import com.keldorn.model.student.Student;
import com.keldorn.model.student.StudentGPAComparator;
import com.keldorn.model.team.Team;
import com.keldorn.util.QueryList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        baseballTest();
        locationTest();
        compareToTest();
        genericsTest();
    }

    private static void separator() {
        System.out.println("-".repeat(30));
    }

    private static void baseballTest() {
        separator();
        var philly = new Affiliation("city", "Philadelphia, PA", "US");
        Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies", philly);
        Team<BaseballPlayer, Affiliation> astro = new Team<>("Houston Astros");
        scoreResult(phillies, 3, astro, 4);

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        var tex = new FootballPlayer("Tex Walker", "Centre half forward");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
//        phillies.addTeamMember(tex);
        phillies.listTeamMembers();
    }

    private static void scoreResult(Team team1, int t1_score, Team team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    private static void locationTest() {
        separator();
        Mappable grandCanyon = new Park("Grand Canyon", new Location(40.1021, -75.4231));
        Mappable yellowstone = new Park("Yellowstone", new Location(44.4882, -110.5916));
        Mappable missouri = new River("Missouri", new Location(45.9239, -111.4983),
                new Location(38.8146, -90.1218));

        Layer<Mappable> layers = new Layer<>(grandCanyon, yellowstone);
        layers.addElement(missouri);
        layers.renderLayer();
    }

    private static void compareToTest() {
        separator();
        Integer five = 5;
        Integer[] others = {0, 5, 10, -50, 50};

        for (var i : others) {
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo=%d%n", five,
                    (val == 0) ? "==" : (val < 0) ? "<" : ">", i, val);
        }

        String banana = "banana";
        String[] fruit = {"apple", "banana", "pear", "BANANA"};
        for (var i : fruit) {
            int val = banana.compareTo(i);
            System.out.printf("%s %s %s: compareTo=%d%n", banana,
                    (val == 0) ? "==" : (val < 0) ? "<" : ">", i, val);
        }

        Arrays.sort(fruit);
        System.out.println(Arrays.toString(fruit));

        Student tim = new Student("Tim");
        Student[] students = {new Student("Zach"), new Student("Tim"), new Student("Ann")};

        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

        System.out.println("result = " + tim.compareTo(new Student("TIM")));

        Comparator<Student> gpaSorter = new StudentGPAComparator();
        Arrays.sort(students, gpaSorter.reversed());
        System.out.println(Arrays.toString(students));
    }

    private static void genericsTest() {
        separator();
        int studentCount = 10;
        List<Students> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Students());
        }
        students.add(new LPAStudent());
        printMoreLists(students);

        List<LPAStudent> lpaStudents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        printMoreLists(lpaStudents);

        testList(new ArrayList<>(List.of("Able", "Barry", "Charlie")));
        testList(new ArrayList<>(List.of(1, 2, 3)));

        var queryList = new QueryList<>(lpaStudents);
        var matches = queryList.getMatches("Course", "Python");
        printMoreLists(matches);

        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
//        var students2021 = QueryList.<Students>getMatches(new ArrayList<>(), "YearStarted", "2021");
        printMoreLists(students2021);

        QueryList<LPAStudent> queryList1 = new QueryList<>();
        for (int i = 0; i < 25; i++) {
            queryList1.add(new LPAStudent());
        }
        System.out.println("Ordered");
        queryList1.sort(Comparator.naturalOrder());

        printMoreLists(queryList1);

        System.out.println("Matches");
        matches = queryList1.getMatches("PercentComplete", "50")
                .getMatches("Course", "Python");
        matches.sort(new LPAStudentComparator());
        printMoreLists(matches);

        System.out.println("Ordered");
        matches.sort(null);
        printMoreLists(matches);
    }

    private static void printMoreLists(List<? extends Students> students) {
        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    private static <T extends Students> void printList(List<T> students) {
        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    private static void testList(List<?> list) {
        for (var element : list) {
            if (element instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }

//    private static void testList(List<String> list) {
//        for (var element : list) {
//            System.out.println("String: " + element.toUpperCase());
//        }
//    }
//
//    private static void testList(List<Integer> list) {
//        for (var element : list) {
//            System.out.println("Integer: " + element.floatValue());
//        }
//    }
}
