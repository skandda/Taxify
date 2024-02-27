package taxify;

public class Shuttle extends Vehicle {
    public Shuttle(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public double calculateCost() {
        return this.service.calculateDistance() * 1.5;
    }
}