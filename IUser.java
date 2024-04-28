package taxify;

import java.time.LocalDate;

public interface IUser extends IMovable {

    public int getId();
    public String getFirstName();
    public String getLastName();
    public char getGender();
    public LocalDate getBirthDate();
    public boolean getService();
    public void setService(boolean service);
    public void setCompany(ITaxiCompany company);
    public void requestService(VehicleType vehicleType, SoundType soundType);
    public void rateService(IService service);
    public String toString();
	public void setLocation(ILocation location);
	public void notifyArrivalAtMicroPickup();
	public void notifyArrivalAtMicroDropOff();
	public void setVehicle(IMicroMobility vehicle);
	public boolean getMicroService();
	public IMicroMobility getVehicle();
	public void setRoute(IRoute route);
	public ILocation getLocation();
	public void setMicroService(IMicroService service);
	public IRoute getRoute();

}