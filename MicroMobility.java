package taxify;

public class MicroMobility implements IMicroMobility {
	
	private int id;
	private ILocation location;
	private ILocation destination;
	private MicroServiceStatus status;
	private IMicroService service;
	private ITaxiCompany company;
	
	public MicroMobility(int id, ILocation location) {
		this.id = id;
		this.location = location;
		this.status = MicroServiceStatus.FREE;
		this.service = null;
		this.destination = null;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	@Override
	public void setLocation(ILocation location) {
		this.location = location;
	}
	
	@Override
	public ILocation getLocation() {
		return this.location;
	}
	
	@Override
	public ILocation getDestination() {
		return this.destination;
	}
	
	@Override
	public IMicroService getService() {
		return this.service;
	}
	
	@Override
	public MicroServiceStatus getStatus() {
		return this.status;
	}
	
	@Override
	public void startService() {
		this.destination = service.getDropoffLocation();
		this.status = MicroServiceStatus.INRIDE;
		this.service.getUser().setRoute(new Route(this.location, this.destination));
	}
	
	@Override
	public void endService() {
		this.service.getUser().setRoute(null);
		this.service = null;
		this.destination = null;
		this.status = MicroServiceStatus.FREE;

	}
	
	@Override
	public boolean isFree() {
		if(this.status == MicroServiceStatus.FREE) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		if(this.status == MicroServiceStatus.FREE) {
			return this.id + " at " + this.location + " is FREE.";
		} else if(this.status == MicroServiceStatus.BOOKED) {
			return this.id + " at " + this.location + " is BOOKED, waiting for user " + this.service.getUser().getId();
		} else {
			return this.id + " at " + this.location + " is in service driving to " + this.destination;
		}
	}
	
	@Override
	public double calculateCost() {
		return this.service.calculateDistance();
	}

	@Override
	public void setCompany(ITaxiCompany company) {
		this.company = company;
	}

	@Override
	public void pickService(IMicroService service) {
		this.service = service;
		this.status = MicroServiceStatus.BOOKED;
		this.destination = this.location;
		this.service.getUser().setRoute(new Route(this.service.getUser().getLocation(), this.destination));
		if(this.service.getUser().getLocation().getX() == this.location.getX() && this.service.getUser().getLocation().getY() == this.location.getY()) {
			this.service.getUser().notifyArrivalAtMicroPickup();
		}
		
	}

	

}
