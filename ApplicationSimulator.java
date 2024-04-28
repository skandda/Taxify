package taxify;

import java.util.List;

public class ApplicationSimulator implements IApplicationSimulator, IObserver {
    private ITaxiCompany company;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private List<IMicroMobility> micros;
    
    public ApplicationSimulator(ITaxiCompany company, List<IUser> users, List<IVehicle> vehicles, List<IMicroMobility> micros) {
        this.company = company;
        this.users = users;
        this.vehicles = vehicles;
        this.micros = micros;
    }
    
    @Override
    public void show() {
        // show the status of the vehicles
        
        System.out.println("\n" + this.company.getName() + " status \n");

        for (IVehicle vehicle : this.vehicles) {
            System.out.println(vehicle.toString());
        }
        for (IMicroMobility mobility : this.micros) {
            System.out.println(mobility.getClass().getSimpleName() + " " + mobility.toString());
        }
    }
    
    @Override
    public void showStatistics() {
        // show the statistics of the company
        
        String s = "\n" + this.company.getName() + " statistics \n";
        
        for (IVehicle vehicle : this.vehicles) {            
            s = s + "\n" +
            
            String.format("%-8s", vehicle.getClass().getSimpleName()) +
            String.format("%2s", vehicle.getId()) + " " +
            String.format("%2s", vehicle.getStatistics().getServices()) + " services " + 
            String.format("%3s", vehicle.getStatistics().getDistance()) + " km. " +
            String.format("%3s", vehicle.getStatistics().getBilling()) + " eur. " +
            String.format("%2s", vehicle.getStatistics().getReviews()) + " reviews " +
            String.format("%-4s", vehicle.getStatistics().getStars()) + " stars";
        }
                
        System.out.println(s);        
    }    

    @Override
    public void update() {
        // move vehicles to their next location
        
        for (IVehicle vehicle : this.vehicles) {
               vehicle.move();
        }
        
        for (IMicroMobility mobility : this.micros) {
        	if(mobility.getService() == null) {
        		System.out.println(mobility.getClass().getSimpleName() + " " + mobility.getId() + " not moving");
        		continue;
        	} else {
        		System.out.println(mobility.getClass().getSimpleName() + " " + mobility.getId() + " is moving");
            	mobility.getService().getUser().move();
        	}

        }
    }
    @Override
    public void requestService(VehicleType vehicleType, SoundType soundType) {        
        // find an available user and requests a service to the Taxi Company

        int index;
        
        do {
            
            index = ApplicationLibrary.rand(this.users.size());;
            
        } while (this.users.get(index).getService());
        
        this.company.provideService(this.users.get(index).getId(), vehicleType, soundType);
    }
    
    @Override
    public void requestMicroService() {        
        // find an available user and requests a service to the Taxi Company

        int index;
        
        do {
            
            index = ApplicationLibrary.rand(this.users.size());;
            
        } while (this.users.get(index).getMicroService());
        
        this.company.provideMicroService(this.users.get(index).getId());
    }
    
    @Override
    public int getTotalServices() {
        return this.company.getTotalServices();
    }
    
    @Override
    public void updateObserver(String message) {
        System.out.println(message);
    }
}