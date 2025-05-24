package exercise;

public class Cuboid extends Rectangle {

    private final double height;

    public Cuboid(double width, double length, double height) {
        super(width, length);
        this.height = height > 0 ? height : 0;
    }

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        return getArea() * height;
    }
}
