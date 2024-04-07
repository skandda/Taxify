package taxify;
import java.time.LocalDate;
public class Driver implements IDriver {
    private String firstName;
    private String lastName;
    private char gender;
    private LocalDate birthday;
    private double rating;
    private int experience;
    public Driver(String firstName, String lastName, char gender, LocalDate birthday, double rating, int experience){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.rating = rating;
        this.experience = experience;
    }
    public String getName(){
        return this.name;
    }
    public String getGender(){
        return this.gender;
    }
    public String getBirthday(){
        return this.birthday
    }
    public double getRating(){
        return this.rating;
    }
    public int getYearsExperience(){
        return this.experience;
    }
}