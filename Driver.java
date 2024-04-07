package taxify;

public class Driver implements IDriver {
    private String name;
    private String gender;
    private String birthday;
    private double rating;
    private int experience;
    public Driver(String name, String gender, String birthday, double rating, int experience){
        this.name = name;
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