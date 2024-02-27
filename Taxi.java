package taxify;

public class Taxi extends Vehicle {
    public Taxi(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public double calculateCost() {
        return this.service.calculateDistance() * 2;
    }
}