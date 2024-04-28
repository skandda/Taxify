package taxify;

public interface IMicroService {

	public int calculateDistance();
	public ILocation getDropoffLocation();
	public ILocation getPickupLocation();
	public IUser getUser();

}
