package taxify;

import java.time.LocalDate;

public interface IDriver {

    public String getFirstName();
    public String getLastName();
    public char getGender();
    public LocalDate getBirthday();
    public double getRating();
    public int getYearsExperience();
    public void setRating(double rating);
    
}