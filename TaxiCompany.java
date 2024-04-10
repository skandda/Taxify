package taxify;

import java.util.List;

public class TaxiCompany implements ITaxiCompany, ISubject {
    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;
    private IObserver observer;
    
    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;        
        this.totalServices = 0;
        
        for (IUser user : this.users) {
            user.setCompany(this);
        }
        
        for (IVehicle vehicle : this.vehicles) {
            vehicle.setCompany(this);
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getTotalServices() {
        return this.totalServices;
    }
        
    @Override
    public boolean provideService(int user, VehicleType vehicleType, SoundType soundType) {
        int userIndex = findUserIndex(user);

        ILocation origin, destination;

        origin = ApplicationLibrary.randomLocation();
        destination = ApplicationLibrary.randomLocation(origin);

        int vehicleIndex = findFreeVehicle(vehicleType, origin, destination);
        
        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks
        
        if (vehicleIndex != -1) {

            // update the user status
                       
            this.users.get(userIndex).setService(true);
            
            // create a service with the user, the pickup and the drop-off location
          
            this.vehicles.get(vehicleIndex).addPassengers();

            IService service = new Service(this.users.get(userIndex), origin, destination, vehicleType, soundType);
            
            // assign the new service to the vehicle
            
            this.vehicles.get(vehicleIndex).pickService(service);            
             
            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + ", the ride is assigned to " +
                           this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                           this.vehicles.get(vehicleIndex).getLocation().toString());
            
            // update the counter of services
            
            this.totalServices++;
            
            return true;
        }
        System.out.println("Could not find ride. Sorry!\n");
        return false;
    }

    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        // notify the observer a vehicle arrived at the pickup location 
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " has arrived to the pickup location.");      
        
    }
  
    @Override
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        // a vehicle arrives at the drop-off location
    	IService service;
        if(vehicle.getPassengers() == 1 && vehicle.getService() == null && vehicle.getSharedService() != null) {
            service = vehicle.getSharedService();         
        } else if(vehicle.getPassengers() == 1 && vehicle.getSharedService() != null) {
        	service = vehicle.getSharedService();       
        } else {
        	service = vehicle.getService();
        }
        int user = service.getUser().getId();
        int userIndex = findUserIndex(user);
    
        // the taxi company requests the user to rate the service, and updates its status

        this.users.get(userIndex).rateService(service);
        this.users.get(userIndex).setService(false);

        // update the counter of services
        
        this.totalServices--;    
        
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);
       
    }
        
    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer; 
    }
    
    @Override
    public void notifyObserver(String message) {
        this.observer.updateObserver(message);
    }
    
    private int findFreeVehicle(VehicleType vehicleType, ILocation origin, ILocation destination) {
        int index = -1;
//        System.out.println(vehicleType);
        if(vehicleType == VehicleType.PINK) {

        	for(int i = 0; i < this.vehicles.size(); ++i) {
        		IVehicle vehicle = vehicles.get(i);
        		if(vehicle.getPassengers() == 0 && vehicle.isFree() && vehicle.getDriver().getGender() == 'F') {
        			index = i;
        			break;
        		}
        	}

        } else if (vehicleType == VehicleType.NORMAL) {
        	for(int i = 0; i < this.vehicles.size(); ++i) {
        		IVehicle vehicle = vehicles.get(i);
        		if(vehicle.getPassengers() == 0 && vehicle.isFree()) {
        			index = i;
        			break;
        		}
        	}
        } else if (vehicleType == VehicleType.SHARED) {
            int distance = 3;
            for (int i = 0; i < this.vehicles.size(); ++i) {
                if (vehicles.get(i).getService() != null
        		&& vehicles.get(i).getService().getVehicleType() == VehicleType.SHARED
                && ApplicationLibrary.distance(origin, vehicles.get(i).getLocation()) <= distance
                && vehicles.get(i).getSharedService() == null
                && ApplicationLibrary.distance(vehicles.get(i).getService().getDropoffLocation(), origin) != 0
                && ApplicationLibrary.distance(vehicles.get(i).getLocation(), vehicles.get(i).getService().getDropoffLocation()) > 3) {
                    index = i;
                    distance = ApplicationLibrary.distance(origin, vehicles.get(i).getLocation());
                }
            }


            if(index == -1) {
            	for(int i = 0; i < this.vehicles.size(); ++i) {
            		IVehicle vehicle = vehicles.get(i);
            		if(vehicle.getPassengers() == 0 && vehicle.isFree()) {
            			index = i;
            			break;
            		}
            	}
            }            
        }
        
        
        return index;
    }

    private int findUserIndex(int id) {
        for (int i=0; i<this.users.size(); i++) {
            if (this.users.get(i).getId() == id)
                return i;
        }
        
        return -1;
    }
}