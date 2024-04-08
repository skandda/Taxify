package taxify;
import java.time.LocalDate;
public class Driver implements IDriver {
    private String firstName;
    private String lastName;
    private char gender;
    private LocalDate birthday;
    private double rating;
    private int experience;

    public Driver(String firstName, String lastName, char gender, LocalDate birthday, int experience){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.rating = 0;
        this.experience = experience;
    }
	public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public char getGender(){
        return this.gender;
    }
    public LocalDate getBirthday(){
        return this.birthday;
    }
    public double getRating(){
        return this.rating;
    }
    public int getYearsExperience(){
        return this.experience;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}