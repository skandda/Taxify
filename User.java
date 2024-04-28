package taxify;

import java.time.LocalDate;

public class User implements IUser, IMovable {
    private int id;
    private String firstName;
    private String lastName;
    private char gender;
    private LocalDate birthDate;
    
    private ITaxiCompany company;
    private boolean service;
    private IRoute route;
    private ILocation location;
    private IMicroService microService;
    private IMicroMobility vehicle;
    
    
    public User(int id, String firstName, String lastName, char gender, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.service = false;
        this.microService = null;
        this.route = null;
    }
    
    @Override
    public IRoute getRoute() {
    	return this.route;
    }
    
    @Override
    public void setVehicle(IMicroMobility vehicle) {
    	this.vehicle = vehicle;
    }
    
    @Override
    public void setRoute(IRoute route) {
    	this.route = route;
    }
    
	@Override
	public void move() {
//		System.out.println("User id: " + this.id + " Route: " + this.route);
//		System.out.println("User location: " + this.location + "Vehicle Origin: " + this.vehicle.getLocation() + " Destination: " + this.microService.getDropoffLocation());
//		System.out.println("Dropoff 1: " + this.microService.getDropoffLocation() + " Location 1: " + this.location);
		this.location = this.route.getNextLocation();
		this.vehicle.setLocation(this.location);
//		System.out.println("Dropoff 2: " + this.microService.getDropoffLocation() + " Location 2: " + this.location);
	
		ILocation originMicroService = this.vehicle.getLocation();
		ILocation destinationMicroService = this.microService.getDropoffLocation();
		
		if (this.location.getX() == originMicroService.getX() && this.location.getY() == originMicroService.getY()
				&& this.vehicle.getStatus() == MicroServiceStatus.BOOKED) {

			notifyArrivalAtMicroPickup();

        } else if (this.location.getX() == destinationMicroService.getX() && this.location.getY() == destinationMicroService.getY()) {
        	
        	notifyArrivalAtMicroDropOff();

        }        
	}

	
    @Override
    public void notifyArrivalAtMicroPickup() {
        // notify the company that the vehicle is at the pickup location and start the service
		this.company.arrivedAtMicroMobility(this);
        this.vehicle.startService();
    }
        
    @Override
    public void notifyArrivalAtMicroDropOff() {
        // notify the company that the vehicle is at the drop off location and end the service
        this.company.arrivedAtMicroDropOff(vehicle);
        this.vehicle.endService();
        this.vehicle = null;
     }
    
    @Override
    public IMicroMobility getVehicle() {
    	return this.vehicle;
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
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }
    
    @Override
    public void setMicroService(IMicroService service) {
    	this.microService = service;
    }
    
    @Override
    public String getLastName() {
        return this.lastName;
    }
    
    @Override
    public char getGender() {
       return this.gender;
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    @Override
    public boolean getService() {
        return this.service;
    }
    
    @Override
    public void setService(boolean service) {
        this.service = service;
    }
    
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    @Override
    public void requestService(VehicleType vehicleType, SoundType soundType) {
        this.company.provideService(this.id, vehicleType, soundType);
    }
    
    @Override
    public boolean getMicroService() {
    	if(this.microService == null) {
    		return false;
    	}else {
    		return true;
    	}
    }
    @Override
    public void rateService(IService service) {
        // users rate around 50% of the services (1 to 5 stars)
        
        if (ApplicationLibrary.rand() % 2 == 0) {
            service.setStars(ApplicationLibrary.rand(5) + 1);
        }
    }
    
    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s",this.getFirstName() + " " + this.getLastName());
    }


}
