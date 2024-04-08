package taxify;

import java.util.List;

public abstract class Vehicle implements IVehicle {
    private int id;
    private ITaxiCompany company;
    private IService service;
    private VehicleStatus status;
    private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private IRoute route;
    private IDriver driver;
    private int passengers;
    private IService service_shared;
        
    public Vehicle(int id, ILocation location, IDriver driver) {        
        this.id = id;
        this.service = null;
        this.service_shared = null;
        this.status = VehicleStatus.FREE;
        this.location = location;        
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = new Route(this.location, this.destination);
        this.driver = driver;
        this.passengers = 0;
    }
    
    @Override
    public IDriver getDriver() {
    	return this.driver;
    }
    @Override
    public int getPassengers(){
        return this.passengers;
    }
    @Override 
    public void addPassengers(){
        this.passengers += 1;
    }
    @Override
    public int getId() {
        return this.id;
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
    public IService getService() {
        return this.service;
    }
    @Override
    public IService getSharedService() {
        return this.service_shared;
    }
    
    @Override
    public IStatistics getStatistics() {
        return this.statistics;
    }
    
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    @Override
    public void pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"

        if(this.service == null && this.service_shared == null) {
            this.service = service;
            ILocation p1 = this.service.getPickupLocation();
            ILocation d1 = this.service.getDropoffLocation();

//            this.route = new Route(this.location, p1);
            IRoute route_1 = new Route(this.location, p1);
            IRoute route_2 = new Route(p1, d1);
            
            List<ILocation> route = route_1.getRoute();
            route.addAll(route_2.getRoute());
            
            this.route.setRoute(route);
            
        
            // this.route = new Route(this.location, this.destination);        
            this.status = VehicleStatus.PICKUP;
            this.destination = service.getDropoffLocation();
        } else if (this.service_shared == null) {
            this.service_shared = service;
            updateRoute();
            this.status = VehicleStatus.PICKUP;
            this.destination = service_shared.getDropoffLocation();
        }
    }

    @Override
    public void updateRoute() {
        if(this.status == VehicleStatus.PICKUP) {
            ILocation p1 = this.service.getPickupLocation();
            ILocation p2 = this.service_shared.getPickupLocation();
            ILocation d1 = this.service.getDropoffLocation();
            ILocation d2 = this.service_shared.getDropoffLocation();
            
            IRoute route_1 = new Route(this.location, p1);
            IRoute route_2 = new Route(p1, p2);
            IRoute route_3 = new Route(p2, d1);
            List<ILocation> route = route_1.getRoute();
            route.addAll(route_2.getRoute());
            route.addAll(route_3.getRoute());
            
            if(ApplicationLibrary.distance(d1, d2) != 0) {
                IRoute route_4 = new Route(d1, d2);
                route.addAll(route_4.getRoute());
            }
            this.route.setRoute(route);



        } else if (this.status == VehicleStatus.SERVICE) {
            ILocation p2 = this.service_shared.getPickupLocation();
            ILocation d1 = this.service.getDropoffLocation();
            ILocation d2 = this.service_shared.getDropoffLocation();
            
            IRoute route_1 = new Route(this.location, p2);
            IRoute route_2 = new Route(p2, d1);
            List<ILocation> route = route_1.getRoute();
            route.addAll(route_2.getRoute());
            
            if(ApplicationLibrary.distance(d1, d2) != 0) {
                IRoute route_3 = new Route(d1, d2);
                route.addAll(route_3.getRoute());
            }
            this.route.setRoute(route);
        }
        
    }

    @Override
    public void startService() {
        // set destination to the service drop-off location, and status to "service"

        // this.destination = service.getDropoffLocation();
        // this.route = new Route(this.location, this.destination);

        if(this.passengers == 1 && this.service_shared != null) {
            this.status = VehicleStatus.PICKUP;
        } else {
        	this.status = VehicleStatus.SERVICE;
        }

    }

    @Override
    public void endService() {
        boolean discount = false;
//        boolean shared = false;
        // update vehicle statistics
        if(this.service == null) {
            discount = true;
            this.statistics.updateBilling(this.calculateCost(discount));
            this.statistics.updateDistance(this.service_shared.calculateDistance());
            this.statistics.updateServices();
            
            // if the service is rated by the user, update statistics
            
            if (this.service_shared.getStars() != 0) {
                this.statistics.updateStars(this.service_shared.getStars());
                this.statistics.updateReviews();
                this.driver.setRating(this.statistics.getStars());

            }
            
            // set service to null, and status to "free"
            
            this.service_shared = null;
            this.destination = ApplicationLibrary.randomLocation(this.location);
            this.route = new Route(this.location, this.destination);
            this.status = VehicleStatus.FREE;

    
            // updates the drivers rating
    
            this.driver.setRating(this.statistics.getStars());
        } else if(this.service_shared == null) {
            // not rideshare, first person done
            this.statistics.updateBilling(this.calculateCost(discount));
            this.statistics.updateDistance(this.service.calculateDistance());
            this.statistics.updateServices();
            
            // if the service is rated by the user, update statistics
            
            if (this.service.getStars() != 0) {
                this.statistics.updateStars(this.service.getStars());
                this.statistics.updateReviews();
                this.driver.setRating(this.statistics.getStars());
            }
            
            // set service to null, and status to "free"
            
            this.service = null;
            this.destination = ApplicationLibrary.randomLocation(this.location);
            this.route = new Route(this.location, this.destination);
            this.status = VehicleStatus.FREE;
  
            // updates the drivers rating
            
    
            this.driver.setRating(this.statistics.getStars());

        } else {
            // ride share, first person done
        	discount = true;
            this.statistics.updateBilling(this.calculateCost(discount));
            this.statistics.updateDistance(this.service.calculateDistance());
            this.statistics.updateServices();
            
            // if the service is rated by the user, update statistics
            
            if (this.service.getStars() != 0) {
                this.statistics.updateStars(this.service.getStars());
                this.statistics.updateReviews();
                this.driver.setRating(this.statistics.getStars());
            }
            
            // set service to null, and status to "free"
            this.service_shared.setVehicleType(VehicleType.NORMAL);
            this.service = null;

    
            // updates the drivers rating
    
            this.driver.setRating(this.statistics.getStars());
        }
        
        
    }

    @Override
    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location and start the service
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    }
        
    @Override
    public void notifyArrivalAtDropoffLocation() {
        // notify the company that the vehicle is at the drop off location and end the service
//    	System.out.println("NUM PASS BEFORE DECREMENT: " + this.passengers);
        this.company.arrivedAtDropoffLocation(this);
        this.passengers -= 1;
        this.endService();
     }
        
    @Override
    public boolean isFree() {
        // returns true if the status of the vehicle is "free" and false otherwise
        if(this.status == VehicleStatus.FREE) {
            return true;
        } else {
            return false;
        }
    }   
    
    @Override
    public void move() {
        // get the next location from the driving route
        
        this.location = this.route.getNextLocation();        
        
        // if the route has more locations the vehicle continues its route, otherwise the vehicle has arrived to a pickup or drop off location
        
//        if (!this.route.hasLocations()) {
            if (this.service == null && this.service_shared == null) {
                // the vehicle continues its random route

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = new Route(this.location, this.destination);
            }
            
            else {
                // check if the vehicle has arrived to a pickup or drop off location
            	
            	if(this.service_shared != null && this.service != null
                		&& this.service.getDropoffLocation().getX() == this.service_shared.getDropoffLocation().getX()
                		&& this.service.getDropoffLocation().getY() == this.service_shared.getDropoffLocation().getY()) {
            		
            		System.out.println("hit on same destination");
            		
            		ILocation origin = this.service.getPickupLocation();
                    ILocation destination = this.service.getDropoffLocation();
            		ILocation origin2 = this.service_shared.getPickupLocation();
                    
	                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {
	                	notifyArrivalAtPickupLocation();
	                } else if(this.location.getX() == origin2.getX() && this.location.getY() == origin2.getY()) {
	                	notifyArrivalAtPickupLocation();
	                } else if(this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {
	                    notifyArrivalAtDropoffLocation();
	                    notifyArrivalAtDropoffLocation();
	            	}
            		

                        
                } else if (this.service_shared != null && this.service != null) {
                	
            		ILocation origin = this.service.getPickupLocation();
            		ILocation origin2 = this.service_shared.getPickupLocation();
                    ILocation destination = this.service.getDropoffLocation();
	                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {
	                	notifyArrivalAtPickupLocation();
	                } else if(this.location.getX() == origin2.getX() && this.location.getY() == origin2.getY()) {
	                	notifyArrivalAtPickupLocation();
	                } 
	                if (destination.getX() == this.location.getX() && destination.getY() == this.location.getY()) {
	                    notifyArrivalAtDropoffLocation();
	                }
                }
            	else if(this.service_shared == null) {
 
            		ILocation origin = this.service.getPickupLocation();
                    ILocation destination = this.service.getDropoffLocation();
                    
	                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {
	                	notifyArrivalAtPickupLocation();
	
	                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()){
	                    notifyArrivalAtDropoffLocation();
	
	            	}	
	               
                }
            	
            	else if (this.service_shared != null && this.service == null) {
	               	 ILocation origin2 = this.service_shared.getPickupLocation();
	                 ILocation destination2 = this.service_shared.getDropoffLocation();
                 
	                 if (this.location.getX() == origin2.getX() && this.location.getY() == origin2.getY()) {
	                	 notifyArrivalAtPickupLocation();
	
	                 } else if (this.location.getX() == destination2.getX() && this.location.getY() == destination2.getY()) {
	                     notifyArrivalAtDropoffLocation();
	
                 	}    
            	}        
            }
        }

    @Override
    public int calculateCost(boolean discount) {
        if(discount) {
            return this.service.calculateDistance() - 2;
        } else {
            return this.service.calculateDistance();
        }

    }

    @Override
    public String toString() {
    	if(this.service == null && this.service_shared != null) {
            return this.id + " at " + this.location + " driving to " + this.destination +
                    ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString(): ((this.status == VehicleStatus.PICKUP) ?
                    " to pickup user " + this.service_shared.getUser().getId() : " in service "));
    	} else {
    		// this.service is null but service shared is also null
            return this.id + " at " + this.location + " driving to " + this.destination +
                    ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString(): ((this.status == VehicleStatus.PICKUP) ?
                    " to pickup user " + this.service.getUser().getId() : " in service "));
    	}

    }    
}