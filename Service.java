package taxify;

public class Service implements IService {
    private IUser user;
    private ILocation pickup;
    private ILocation dropoff;
    private int stars;
    private VehicleType vehicleType;
    private SoundType soundType;
    
    public Service(IUser user, ILocation pickup, ILocation dropoff, VehicleType vehicleType, SoundType soundType) {
        this.user = user;
        this.pickup = pickup;
        this.dropoff = dropoff; 
        this.stars = 0;
        this.vehicleType = vehicleType;
        this.soundType = soundType;
    }
    @Override
    public VehicleType getVehicleType() {
    	return this.vehicleType;
    }
    
    @Override
    public void setVehicleType(VehicleType type) {
    	this.vehicleType = type;
    }
    
    @Override
    public SoundType getSoundType() {
    	return this.soundType;
    }
    
    @Override
    public IUser getUser() {
        return this.user;
    }
    
    @Override
    public ILocation getPickupLocation() {
        return this.pickup;
    }
    
    @Override
    public ILocation getDropoffLocation() {
        return this.dropoff;
    }
    
    @Override
    public int getStars() {
        return this.stars;
    }
    
    @Override
    public void setStars(int stars) {
        this.stars = stars;
    }
    
    @Override
    public int calculateDistance() {
        return Math.abs(this.pickup.getX() - this.dropoff.getX()) + Math.abs(this.pickup.getY() - this.dropoff.getY());
    }
    
    @Override
    public String toString() {
        return this.getPickupLocation().toString() + " to " + this.getDropoffLocation().toString();
    }
}