package taxify;

public class Bike extends MicroMobility {
	
	public Bike(int id, ILocation location) {
		super(id, location);
	}
	
	public double calculateCost() {
		return this.getService().calculateDistance() * 0.5;
	}

}
