package com.keldorn;

import com.keldorn.model.composition.PersonalComputer;
import com.keldorn.model.encapsulation.EnhancedPlayer;
import com.keldorn.model.encapsulation.Player;
import com.keldorn.model.inheritance.ComputerCase;
import com.keldorn.model.inheritance.Monitor;
import com.keldorn.model.inheritance.Motherboard;
import com.keldorn.model.polymorphism.burger.MealOrder;
import com.keldorn.model.polymorphism.movie.Adventure;
import com.keldorn.model.polymorphism.movie.Comedy;
import com.keldorn.model.polymorphism.movie.Movie;
import com.keldorn.model.polymorphism.movie.ScienceFiction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ComputerCase theCase = new ComputerCase("2208", "Dell", "240");
        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");
        Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4, 6, "V2.44");

        PersonalComputer thePC = new PersonalComputer("2208", "Dell", theCase, theMonitor, theMotherboard);

        thePC.powerUp();

        enhancedPlayerTest();
        runTimeCompileTime();

        burgerTest();
    }

    private static void playerTest() {
        Player player = new Player();
        player.name = "Test";
        player.health = 20;
        player.weapon = "Sword";

        int damage = 10;
        player.loseHealth(damage);
        System.out.println("Remaining health = " + player.healthRemaining());

        player.health = 200;

        player.loseHealth(11);
        System.out.println("Remaining health = " + player.healthRemaining());

    }

    private static void enhancedPlayerTest() {
        EnhancedPlayer tim = new EnhancedPlayer("Tim", 200, "Sword");
        System.out.println("Initial health is " + tim.healthRemaining());
    }

    private static void movieTest() {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Type (A for Adventure, C for Comedy," +
                    "S for Science Fiction, or Q to quit): ");
            String type = s.nextLine();
            if ("Qq".contains(type)) {
                break;
            }
            System.out.print("Enter Movie Title: ");
            String title = s.nextLine();
            Movie movie = Movie.getMovie(type, title);
            movie.watchMovie();
        }
    }

    private static void runTimeCompileTime() {
        Movie movie = Movie.getMovie("A", "Jaws");
        movie.watchMovie();

        Adventure jaws = (Adventure) Movie.getMovie("A", "Jaws");
        jaws.watchMovie();

        Object comedy = Movie.getMovie("c", "Airplane");
        Comedy comedyMovie = (Comedy) comedy;
        comedyMovie.watchComedy();

        var airplane = Movie.getMovie("C", "Airplane");
        airplane.watchMovie();

        var plane = new Comedy("Airplane");
        plane.watchComedy();

        Object unknownObject = Movie.getMovie("s", "Star Wars");
        if (unknownObject.getClass().getSimpleName().equals("Comedy")) {
            Comedy c = (Comedy) unknownObject;
            c.watchComedy();
        } else if (unknownObject instanceof Adventure) {
            ((Adventure) unknownObject).watchAdventure();
        } else if (unknownObject instanceof ScienceFiction syfi) {
            syfi.watchScienceFiction();
        }
    }

    private static void burgerTest() {
        MealOrder regularMeal = new MealOrder();
        regularMeal.addBurgerToppings("BACON", "CHEESE", "MAYO");
        regularMeal.setDrinkSize("LARGE");
        regularMeal.printItemizedList();

        System.out.println();

        MealOrder deluxeMeal = new MealOrder("deluxe", "7-up", "chili");
        deluxeMeal.addBurgerToppings("AVOCADO", "BACON", "LETTUCE", "CHEESE", "MAYO");
        deluxeMeal.setDrinkSize("SMALL");
        deluxeMeal.printItemizedList();
    }

}
