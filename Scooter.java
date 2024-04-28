package taxify;

public class Scooter extends MicroMobility {
	
	public Scooter(int id, ILocation location) {
		super(id, location);
	}
	
	public double calculateCost() {
		return this.getService().calculateDistance() * 0.75;
	}

}
