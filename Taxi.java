package taxify;

public class Taxi extends Vehicle {
    public Taxi(int id, ILocation location, IDriver driver) {
        super(id, location, driver);
    }

    @Override
    public int calculateCost() {
        return this.getService().calculateDistance() * 4;
    }
}