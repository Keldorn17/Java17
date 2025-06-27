package main.java.com.keldorn.model.theatre;

import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public class Theatre {
    private final int SEATS_IN_A_ROW = 10;
    private final String name;
    private final int rows;
    private final NavigableSet<Seat> seats = new TreeSet<>();

    public Theatre(String name, int rows) {
        this.name = name;
        int DEFAULT_ROW_NUMBER = 26;
        this.rows = rows > DEFAULT_ROW_NUMBER || rows < 0 ?
                DEFAULT_ROW_NUMBER : rows;
        int totalSeats = SEATS_IN_A_ROW * rows;

        char rowLetter = 'A';
        for (int i = 0; i < totalSeats; i++) {
            if (i % SEATS_IN_A_ROW == 0 && i != 0) {
                rowLetter++;
            }
            seats.add(new Seat(rowLetter, i % SEATS_IN_A_ROW + 1));
        }
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public void printSeatMap() {
        System.out.println("\n" + "-".repeat(24));
        System.out.print("\t1 2 3 4 5 6 7 8 9 10");
        seats.forEach(s -> {
            if (s.rowNumber == 1) {
                System.out.printf("%n%s : ", s.toString().charAt(0));
            }
            System.out.print(s.reserved ? "X " : "O ");
        });
        System.out.println("\n" + "-".repeat(24));
    }

    public void reserveSeat(String seatNumber) {
        try {
            Seat seat = new Seat(seatNumber.charAt(0), Integer.parseInt(seatNumber.substring(1)));
            Objects.requireNonNull(seats.ceiling(seat)).setReserved(true);
            System.out.printf("Seat has been reserved at %s%n", seat);
        } catch (NullPointerException e) {
            throw new NullPointerException("%s was an invalid seat number. Valid seatNumbers are (%s - %s) with %d seats in a row"
                    .formatted(seatNumber, seats.first(), seats.last(), SEATS_IN_A_ROW));
        }
    }

    public void reserveSeat(String... seatNumber) {
        for (var seat : seatNumber) {
            reserveSeat(seat);
        }
    }

    static class Seat implements Comparable<Seat> {
        private final char rowLetter;
        private final int rowNumber;
        private boolean reserved;

        public Seat(char rowLetter, int rowNumber) {
            this(rowLetter, rowNumber, false);
        }

        public Seat(char rowLetter, int rowNumber, boolean reserved) {
            this.rowLetter = Character.toUpperCase(rowLetter);
            this.rowNumber = rowNumber < 1 ? 2 : rowNumber;
            this.reserved = reserved;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void setReserved(boolean reserved) {
            if (this.reserved) {
                throw new IllegalArgumentException("Seat's already reserved at %s".formatted(toString()));
            }
            this.reserved = reserved;
        }

        @Override
        public String toString() {
            return "%c%03d".formatted(rowLetter, rowNumber);
        }

        @Override
        public int compareTo(Seat o) {
            return toString().compareTo(o.toString());
        }
    }
}
