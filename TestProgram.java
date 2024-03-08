package taxify;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class TestProgram {
    public static void main(String[] args) {
        List<IVehicle> vehicles = new ArrayList<>();
        List<IUser> users = new ArrayList<>();
        users.add(new User(1, "John", "Doe", 'M',  LocalDate.parse("09-14-2002", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Ali", "Smith", 'F',  LocalDate.parse("10-10-1990", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Jack", "Myers", 'M',  LocalDate.parse("11-11-1995", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Luke", "Summers", 'M',  LocalDate.parse("12-12-2001", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Lauren", "Parker", 'F',  LocalDate.parse("01-13-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Jamie", "Gooding", 'F',  LocalDate.parse("02-14-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Tom", "Ripley", 'M',  LocalDate.parse("03-15-2005", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Josh", "Leaf", 'M',  LocalDate.parse("04-17-1984", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Max", "Luigi", 'M',  LocalDate.parse("05-11-1999", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "John", "Deer", 'M',  LocalDate.parse("06-19-2002", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Alice", "Hearn", 'F',  LocalDate.parse("07-23-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Emma", "Schmidt", 'F',  LocalDate.parse("08-24-2001", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Arch", "Archington", 'M',  LocalDate.parse("09-30-1997", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Matt", "Baker", 'M',  LocalDate.parse("10-09-1989", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        users.add(new User(1, "Eli", "Toe", 'F',  LocalDate.parse("11-01-1998", DateTimeFormatter.ofPattern("MM-dd-yyyy"))));

        vehicles.add(new Taxi(1, ApplicationLibrary.randomLocation()));
        vehicles.add(new Taxi(2, ApplicationLibrary.randomLocation()));
        vehicles.add(new Taxi(3, ApplicationLibrary.randomLocation()));
        vehicles.add(new Taxi(4, ApplicationLibrary.randomLocation()));
        vehicles.add(new Taxi(5, ApplicationLibrary.randomLocation()));
        vehicles.add(new Shuttle(6, ApplicationLibrary.randomLocation()));
        vehicles.add(new Shuttle(7, ApplicationLibrary.randomLocation()));
        vehicles.add(new Shuttle(8, ApplicationLibrary.randomLocation()));
        vehicles.add(new Shuttle(9, ApplicationLibrary.randomLocation()));
        vehicles.add(new Shuttle(10, ApplicationLibrary.randomLocation()));

        TaxiCompany company = new TaxiCompany("Uber", users, vehicles);
        ApplicationSimulator app = new ApplicationSimulator(company, users, vehicles);
        company.addObserver(app);
        for(int i = 0; i < 10; i++){
        	System.out.println(i);
            app.requestService();
            app.show();
            app.update();
        }
        app.showStatistics();
    }
}