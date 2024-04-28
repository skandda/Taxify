package taxify;

public interface ITaxiCompany {

    public String getName();    
    public int getTotalServices();
    public boolean provideService(int user, VehicleType vehicleType, SoundType soundType);
    public void arrivedAtPickupLocation(IVehicle vehicle);
    public void arrivedAtDropoffLocation(IVehicle vehicle);
    public boolean provideMicroService(int user);
	public void arrivedAtMicroMobility(IUser user);
	public void arrivedAtMicroDropOff(IMicroMobility mobility);
    
}