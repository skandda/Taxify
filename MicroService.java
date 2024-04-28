package taxify;

public class MicroService implements IMicroService {
    private IUser user;
    private ILocation pickup;
    private ILocation dropoff;

    
    public MicroService(IUser user, ILocation pickup, ILocation dropoff) {
        this.user = user;
        this.pickup = pickup;
        this.dropoff = dropoff; 
        
    }
    @Override
    public IUser getUser(){
        return this.user;
    }
    @Override
    public ILocation getPickupLocation(){
        return this.pickup;
    }
    @Override
    public ILocation getDropoffLocation(){
        return this.dropoff;
    }
    @Override
    public int calculateDistance(){
        return Math.abs(this.user.getVehicle().getLocation().getX() - this.dropoff.getX()) + Math.abs(this.user.getVehicle().getLocation().getY() - this.dropoff.getY());
    }
    @Override
    public String toString(){
        return this.getPickupLocation().toString() + " to " + this.getDropoffLocation().toString();
    }
}