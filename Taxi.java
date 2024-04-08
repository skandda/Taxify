package taxify;

public class Taxi extends Vehicle {
    public Taxi(int id, ILocation location, IDriver driver) {
        super(id, location, driver);
    }

    @Override
    public int calculateCost(boolean discount) {
    	if(discount) {
    		if(this.getPassengers() == 2) {
    			return this.getService().calculateDistance() * 3 - 2;
    		} else {
    			System.out.println(this.getPassengers());
        		return this.getSharedService().calculateDistance() * 3 - 2;
    		} 
    	} else {
    		return this.getService().calculateDistance() * 3;
    	}

    }
}