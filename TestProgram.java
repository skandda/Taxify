package taxify;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;


public class TestProgram {
    public static void main(String[] args) {
    	for(int p = 0; p < 1; p++) {
    		List<IVehicle> vehicles = new ArrayList<>();
            List<IUser> users = new ArrayList<>();
            List<IMicroMobility> micros = new ArrayList<>();
            users.add(new User(1, "John", "Doe", 'M',  LocalDate.parse("09-14-2002", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(2, "Ali", "Smith", 'F',  LocalDate.parse("10-10-1990", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(3, "Jack", "Myers", 'M',  LocalDate.parse("11-11-1995", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(4, "Luke", "Summers", 'M',  LocalDate.parse("12-12-2001", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(5, "Lauren", "Parker", 'F',  LocalDate.parse("01-13-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(6, "Jamie", "Gooding", 'F',  LocalDate.parse("02-14-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(7, "Tom", "Ripley", 'M',  LocalDate.parse("03-15-2005", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(8, "Josh", "Leaf", 'M',  LocalDate.parse("04-17-1984", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(9, "Max", "Luigi", 'M',  LocalDate.parse("05-11-1999", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(10, "John", "Deer", 'M',  LocalDate.parse("06-19-2002", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(11, "Alice", "Hearn", 'F',  LocalDate.parse("07-23-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(12, "Emma", "Schmidt", 'F',  LocalDate.parse("08-24-2001", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(13, "Arch", "Archington", 'M',  LocalDate.parse("09-30-1997", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(14, "Matt", "Baker", 'M',  LocalDate.parse("10-09-1989", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            users.add(new User(15, "Eli", "Toe", 'F',  LocalDate.parse("11-01-1998", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));          
            
            vehicles.add(new Taxi(1, ApplicationLibrary.randomLocation(), 
            		new Driver("Johnny", "Doe", 'M',  LocalDate.parse("09-14-2002", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 2)));
            vehicles.add(new Taxi(2, ApplicationLibrary.randomLocation(),
            		new Driver("Johnanie", "Doe", 'F',  LocalDate.parse("09-14-2003", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 3)));
            vehicles.add(new Taxi(3, ApplicationLibrary.randomLocation(),
            		new Driver("JohnBoy", "Doe", 'M',  LocalDate.parse("09-14-2004", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Taxi(4, ApplicationLibrary.randomLocation(),
            		new Driver("Johnephene", "Doe", 'F',  LocalDate.parse("09-14-2005", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Taxi(5, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doeme", 'M',  LocalDate.parse("02-14-2006", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(6, ApplicationLibrary.randomLocation(),
            		new Driver("Johnissimo", "Doe", 'M',  LocalDate.parse("09-14-2007", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(7, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doettely", 'F',  LocalDate.parse("04-14-2008", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(8, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doe", 'M',  LocalDate.parse("09-15-2009", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(9, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doette", 'F',  LocalDate.parse("09-14-2010", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(10, ApplicationLibrary.randomLocation(),
            		new Driver("Johnular", "Doe", 'M',  LocalDate.parse("09-14-2005", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            micros.add(new Bike(1, ApplicationLibrary.randomLocation()));
            micros.add(new Bike(2, ApplicationLibrary.randomLocation()));
            micros.add(new Bike(3, ApplicationLibrary.randomLocation()));
            micros.add(new Bike(4, ApplicationLibrary.randomLocation()));
            micros.add(new Bike(5, ApplicationLibrary.randomLocation()));
            micros.add(new Bike(6, ApplicationLibrary.randomLocation()));
            micros.add(new Bike(7, ApplicationLibrary.randomLocation()));
            micros.add(new Scooter(8, ApplicationLibrary.randomLocation()));
            micros.add(new Scooter(9, ApplicationLibrary.randomLocation()));
            micros.add(new Scooter(10, ApplicationLibrary.randomLocation()));
            micros.add(new Scooter(11, ApplicationLibrary.randomLocation()));
            micros.add(new Scooter(12, ApplicationLibrary.randomLocation()));
           
            TaxiCompany company = new TaxiCompany("Uber", users, vehicles, micros);
            ApplicationSimulator app = new ApplicationSimulator(company, users, vehicles, micros);
            
            int sharedRides = 0;
            int pinkRides = 0;
            int normalRides = 0;
            int microRides = 0;
            
            company.addObserver(app);
            app.show();
            for(int i = 0; i <= 5; i++){
                // Randomly add initial services.
                if(ApplicationLibrary.rand(10) < 2) {
                    app.requestService(VehicleType.SHARED, SoundType.STANDARD);
                    sharedRides += 1;
                } else if(ApplicationLibrary.rand(10) < 4) {
                	app.requestService(VehicleType.PINK, SoundType.STANDARD);
                	pinkRides += 1;
                } else if(ApplicationLibrary.rand(10) < 6){
                	app.requestService(VehicleType.NORMAL, SoundType.STANDARD);
                	normalRides += 1;
                }else {
                	app.requestMicroService();
                	microRides += 1;
                }

            }

            do {
                app.show();
                app.update();
                // Randomly add new services every update.
                if(ApplicationLibrary.rand(10) < 2) {
                    if(ApplicationLibrary.rand(10) < 2) {
                        app.requestService(VehicleType.SHARED, SoundType.STANDARD);
                        sharedRides += 1;
                    } else if(ApplicationLibrary.rand(10) < 4) {
                    	app.requestService(VehicleType.PINK, SoundType.STANDARD);
                    	pinkRides += 1;
                    } else if (ApplicationLibrary.rand(10) < 6){
                    	app.requestService(VehicleType.NORMAL, SoundType.STANDARD);
                    	normalRides += 1;
                    } else {
                    	app.requestMicroService();
                    	microRides += 1;
                    }
                }


            } while (app.getTotalServices() != 0);
            app.showStatistics();
    	}
        
}
}