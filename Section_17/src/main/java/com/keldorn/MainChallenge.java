package main.java.com.keldorn;

import main.java.com.keldorn.model.course.Student;
import main.java.com.keldorn.model.course.dto.Course;

import java.util.Comparator;
import java.util.stream.Stream;

public class MainChallenge {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course cgj = new Course("CGJ", "Creating Games in Java");

        var data = Stream.generate(() -> Student.getRandomStudent(pymc, jmc))
                .limit(5000)
                .toList();

//        var data = Stream.iterate(1, s -> s <= 5000, s -> s + 1)
//                .map(s -> Student.getRandomStudent(jmc, pymc))
//                .toList();

        double totalPercent = data.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .reduce(0, Double::sum);
//                .sum();

        double avgPercent = totalPercent / data.size();
        System.out.printf("Average Percentage Complete = %.2f%% %n", avgPercent);

        int topPercent = (int) (1.25 * avgPercent);
        System.out.printf("Best Percentage Complete = %d%% %n", topPercent);

        var hardWorkers = data.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .toList();

        System.out.println("hardWorkers = " + hardWorkers.size());

        hardWorkers.stream()
                .sorted(Comparator.comparing(Student::getYearEnrolled))
                .limit(10)
                .forEach(s -> s.addCourse(cgj));

    }
}
