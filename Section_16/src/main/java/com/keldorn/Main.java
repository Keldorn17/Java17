package main.java.com.keldorn;

import main.java.com.keldorn.external.util.Logger;
import main.java.com.keldorn.model.consumer.specific.ChildClass;
import main.java.com.keldorn.model.generic.BaseClass;

public class Main {
    private static final String SEPARATOR = "-".repeat(30);

    public static void main(String[] args) {
        finalExplored();
    }

    private static void finalExplored() {
        System.out.println(SEPARATOR);
        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        System.out.println(SEPARATOR);
        childReferredToAsBase.recommendedMethod();
        System.out.println(SEPARATOR);
        child.recommendedMethod();

        System.out.println(SEPARATOR);
        parent.recommendedStatic();
        System.out.println(SEPARATOR);
        childReferredToAsBase.recommendedStatic();
        System.out.println(SEPARATOR);
        child.recommendedStatic();

        System.out.println(SEPARATOR);
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
}
