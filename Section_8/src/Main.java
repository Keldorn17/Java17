import model.composition.PersonalComputer;
import model.encapsulation.EnhancedPlayer;
import model.encapsulation.Player;
import model.inheritance.ComputerCase;
import model.inheritance.Monitor;
import model.inheritance.Motherboard;

public class Main {

    public static void main(String[] args) {
        ComputerCase theCase = new ComputerCase("2208", "Dell", "240");
        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");
        Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4, 6, "V2.44");

        PersonalComputer thePC = new PersonalComputer("2208", "Dell", theCase, theMonitor, theMotherboard);

        thePC.powerUp();

        enhancedPlayerTest();
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
}
