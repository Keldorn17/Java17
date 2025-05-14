public class Main {

    public static void main(String[] args) {
        System.out.println("Bello World");

        boolean isAlien = false;
        if (!isAlien) {
            System.out.println("It is not an alien!");
        }

        boolean isCar;
        if (isCar = true) {
            System.out.println("This is not supposed to happen! isCar = " + isCar);
        }

        String makeOfCar = "Volkswagen";
        boolean isDomestic = makeOfCar == "Volkswagen" ? false : true; // makeOfCar != "Volkswagen" works just like this.
        // !makeOfCar.equals("Volkswagen") also works

        String s = isDomestic ? "This car is domestic" : "This car is not domestic";
        System.out.println(s);

        //Challenge
        double a = 20d;
        double b = 80d;
        double calc = (a + b) * 100 % 40;
        boolean remainder = calc != 0;
        System.out.println(remainder);
        if (remainder) {
            System.out.println("Got some remainder");
        }
    }
}
