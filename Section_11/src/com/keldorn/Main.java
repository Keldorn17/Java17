package com.keldorn;

import com.keldorn.model.animal.*;
import com.keldorn.model.store.ArtObject;
import com.keldorn.model.store.Furniture;
import com.keldorn.model.store.OrderItem;
import com.keldorn.model.store.Store;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        animalTest();
        storeTest();
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
}
