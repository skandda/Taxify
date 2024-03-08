package taxify;

public class Shuttle extends Vehicle {
    public Shuttle(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public int calculateCost() {
        return this.service.calculateDistance() * 3;
    }
}