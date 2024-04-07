package taxify;

public class Shuttle extends Vehicle {
    public Shuttle(int id, ILocation location, IDriver driver) {
        super(id, location, driver);
    }

    @Override
    public int calculateCost() {
        return this.getService().calculateDistance() * 3;
    }
}