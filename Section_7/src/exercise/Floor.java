package exercise;

public class Floor {

    private final double width;
    private final double length;

    public Floor(double width, double length) {
        this.width = width > 0 ? width : 0;
        this.length = length > 0 ? length : 0;
    }

    public double getArea() {
        return this.width * this.length;
    }
}
