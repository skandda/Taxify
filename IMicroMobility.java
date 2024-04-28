package taxify;

public interface IMicroMobility {
    public int getId();
    public ILocation getLocation();
    public void startService();
    public void endService();
    public boolean isFree();
    public String toString();
    public double calculateCost();
	public ILocation getDestination();
	public void setCompany(ITaxiCompany company);
	public void pickService(IMicroService service);
	public IMicroService getService();
	public void setLocation(ILocation location);
	public MicroServiceStatus getStatus();
}