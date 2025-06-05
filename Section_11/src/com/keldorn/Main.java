package com.keldorn;

import com.keldorn.enums.FlightStages;
import com.keldorn.interfaces.FlightEnabled;
import com.keldorn.interfaces.OrbitEarth;
import com.keldorn.interfaces.Trackable;
import com.keldorn.model.animal.*;
import com.keldorn.model.machine.Jet;
import com.keldorn.model.machine.Satellite;
import com.keldorn.model.machine.Truck;
import com.keldorn.model.store.ArtObject;
import com.keldorn.model.store.Furniture;
import com.keldorn.model.store.OrderItem;
import com.keldorn.model.store.Store;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        animalTest();
        storeTest();
        interfaceTest();
    }

    private static void separator() {
        System.out.println("-".repeat(30));
    }

    private static void doAnimalStuff(Animal animal) {
        animal.makeNoise();
        animal.move("slow");
        if (animal instanceof Mammal mammal) {
            mammal.shedHair();
        }
    }

    private static void animalTest() {
        separator();
        Dog dog = new Dog("Wolf", "big", 100);
        dog.makeNoise();

        doAnimalStuff(dog);

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(new Dog("German Shepard", "big", 150));
        animals.add(new Fish("Goldfish", "small", 1));
        animals.add(new Fish("Barracuda", "big", 75));
        animals.add(new Dog("Pig", "small", 20));

        animals.add(new Horse("Clydesdale", "large", 1000));

        for (var animal : animals) {
            doAnimalStuff(animal);
        }
    }

    private static void storeTest() {
        separator();
        Store store = new Store();
        store.addProduct(new ArtObject("Oil Painting", 1350,
                "Impressionistic work by ABF painted in 2010"));
        store.addProduct(new ArtObject("Sculpture", 2000,
                "Bronze work by JKF, produced in 1959"));
        store.addProduct(new Furniture("Desk", 500, "Mahogany Desk"));
        store.addProduct(new Furniture("Lamp", 200,
                "Tiffany Lamp with HummingBirds"));
        store.listProducts();

        separator();
        System.out.println("Order 1");
        var order1 = new ArrayList<OrderItem>();
        store.addItemToOrder(order1, 1, 2);
        store.addItemToOrder(order1, 0, 1);
        store.printOrder(order1);

        separator();
        System.out.println("Order 2");
        var order2 = new ArrayList<OrderItem>();
        store.addItemToOrder(order2, 3, 5);
        store.addItemToOrder(order2, 0, 1);
        store.addItemToOrder(order2, 2, 1);
        store.printOrder(order2);
    }

    private static void interfaceTest() {
        separator();
        Bird bird = new Bird();
        Animal animal = bird;
        FlightEnabled flier = bird;
        Trackable tracked = bird;

        animal.move("slow");
//        flier.takeOff();
//        flier.fly();
//        tracked.track();
//        bird.land();
        inFlight(bird);
        inFlight(new Jet());
        Trackable truck = new Truck();
        truck.track();
        System.out.println(truck.getClass().getSimpleName());

        separator();
        double kmsTraveled = 100;
        double milesTraveled = kmsTraveled * FlightEnabled.KM_TO_MILES;
        System.out.printf("The truck traveled %.2f km or %.2f miles%n", kmsTraveled, milesTraveled);

        LinkedList<FlightEnabled> fliers = new LinkedList<>();
        fliers.add(bird);

        List<FlightEnabled> betterFliers = new ArrayList<>();
        betterFliers.add(bird);

        triggerFliers(fliers);
        flyFliers(fliers);
        landFliers(fliers);

        triggerFliers(betterFliers);
        flyFliers(betterFliers);
        landFliers(betterFliers);

        separator();
        orbit(new Satellite());

        separator();
    }

    private static void inFlight(FlightEnabled flier) {
        flier.takeOff();
        flier.transition(FlightStages.LAUNCH);
        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }

    private static void orbit(OrbitEarth flier) {
        flier.takeOff();
        flier.fly();
        flier.land();
    }

    private static void triggerFliers(List<FlightEnabled> fliers) {
        for (var flier : fliers) {
            flier.takeOff();
        }
    }

    private static void flyFliers(List<FlightEnabled> fliers) {
        for (var flier : fliers) {
            flier.fly();
        }
    }

    private static void landFliers(List<FlightEnabled> fliers) {
        for (var flier : fliers) {
            flier.land();
        }
    }
}
