package com.keldorn.model.printer;

public class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, int pagesPrinted, boolean duplex) {
        this.tonerLevel = (tonerLevel >= 0 && tonerLevel <= 100) ? tonerLevel : -1;
        this.pagesPrinted = pagesPrinted;
        this.duplex = duplex;
    }

    public Printer() {
        this(100, 0, false);
    }

    public int addToner(int tonerAmount) {
        int temp = tonerAmount + tonerLevel;
        if (temp > 100 || temp < 0) {
            return -1;
        }
        tonerLevel = temp;
        return tonerLevel;
    }

    public int printPages(int pages) {
        return pagesPrinted += (duplex) ? (pages / 2) + (pages % 2) : pages;
    }
}
