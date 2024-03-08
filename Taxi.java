package taxify;

public class Taxi extends Vehicle {
    public Taxi(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public int calculateCost() {
        return this.service.calculateDistance() * 4;
    }
}