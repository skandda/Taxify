package taxify;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;


public class TestProgram {
    public static void main(String[] args) {
    	for(int p = 0; p < 500; p++) {
    		List<IVehicle> vehicles = new ArrayList<>();
            List<IUser> users = new ArrayList<>();
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
//            user 7 5,9 (8,8), the ride is assigned to Taxi 1 at location (5,0)
//            User 14 requests a service from (4,6) to (3,9), the ride is assigned to Taxi 2 at location (9,1)
//            User 5 requests a service from (2,0) to (4,4), the ride is assigned to Taxi 4 at location (8,0)
//            User 4 requests a service from (1,2) to (1,8), the ride is assigned to Taxi 3 at location (9,0)
//            User 1 requests a service from (4,3) to (3,8), the ride is assigned to Taxi 5 at location (8,3)
//            User 8 requests a service from (1,9) to (2,0), the ride is assigned to Shuttle 6 at location (7,2)
//            public Driver(String firstName, String lastName, char gender, LocalDate birthday, double rating, int experience)
            
            
            
            vehicles.add(new Taxi(1, ApplicationLibrary.randomLocation(), 
            		new Driver("Johnny", "Doe", 'M',  LocalDate.parse("09-14-2002", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Taxi(2, ApplicationLibrary.randomLocation(),
            		new Driver("Johnanie", "Doe", 'F',  LocalDate.parse("09-14-2003", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Taxi(3, ApplicationLibrary.randomLocation(),
            		new Driver("JohnBoy", "Doe", 'M',  LocalDate.parse("09-14-2004", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Taxi(4, ApplicationLibrary.randomLocation(),
            		new Driver("Johnephene", "Doe", 'F',  LocalDate.parse("09-14-2005", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Taxi(5, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doeme", 'M',  LocalDate.parse("09-14-2006", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(6, ApplicationLibrary.randomLocation(),
            		new Driver("Johnissimo", "Doe", 'M',  LocalDate.parse("09-14-2007", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(7, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doettely", 'F',  LocalDate.parse("09-14-2008", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(8, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doe", 'M',  LocalDate.parse("09-14-2009", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(9, ApplicationLibrary.randomLocation(),
            		new Driver("John", "Doette", 'F',  LocalDate.parse("09-14-2010", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));
            vehicles.add(new Shuttle(10, ApplicationLibrary.randomLocation(),
            		new Driver("Johnular", "Doe", 'M',  LocalDate.parse("09-14-2005", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 0)));

            TaxiCompany company = new TaxiCompany("Uber", users, vehicles);
            ApplicationSimulator app = new ApplicationSimulator(company, users, vehicles);
            
            int sharedRides = 0;
            int pinkRides = 0;
            int normalRides = 0;
            
            company.addObserver(app);
            app.show();
            for(int i = 0; i <= 5; i++){
                if(ApplicationLibrary.rand(10) < 2) {
//                	System.out.println("adding shared passenger");
                    app.requestService(VehicleType.SHARED, SoundType.STANDARD);
                    sharedRides += 1;
                } else if(ApplicationLibrary.rand(10) < 4) {
//                	System.out.println("adding pink passenger");
                	app.requestService(VehicleType.PINK, SoundType.STANDARD);
                	pinkRides += 1;
                } else {
//                	System.out.println("adding normal passenger");
                	app.requestService(VehicleType.NORMAL, SoundType.STANDARD);
                	normalRides += 1;
                }

            }

            do {
                app.show();
                app.update();
                
                if(ApplicationLibrary.rand(10) < 2) {
                    if(ApplicationLibrary.rand(10) < 2) {
//                    	System.out.println("adding shared passenger");
                        app.requestService(VehicleType.SHARED, SoundType.STANDARD);
                        sharedRides += 1;
                    } else if(ApplicationLibrary.rand(10) < 4) {
//                    	System.out.println("adding pink passenger");
                    	app.requestService(VehicleType.PINK, SoundType.STANDARD);
                    	pinkRides += 1;
                    } else {
//                    	System.out.println("adding normal passenger");
                    	app.requestService(VehicleType.NORMAL, SoundType.STANDARD);
                    	normalRides += 1;
                    }
                }


            } while (app.getTotalServices() != 0);
            app.showStatistics();
            System.out.println("Shared Rides: " + sharedRides);
            System.out.println("Pink Rides: " + pinkRides);
            System.out.println("Normal Rides: " + normalRides);
    	}
        
}
}