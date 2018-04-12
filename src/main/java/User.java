import org.bson.types.ObjectId;

public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String phone;
    private String email;
    private String address;
    private String fullName;

    public String getFirstName(){return this.firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return this.lastName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public String getGender(){return this.gender;}

    public void setGender(String gender){this.gender = gender;}

    public int getAge(){return this.age;}

    public void setAge(int age){this.age = age;}

    public String getPhone(){return this.phone;}

    public void setPhone(String phone){this.phone = phone;}

    public String getEmail(){return this.email;}

    public void setEmail(String email){this.email = email;}

    public String getAddress() {return this.address;}

    public void setAddress(String address){this.address = address;}

    public String getFullName(){
        return this.fullName = firstName + " " + lastName;
    }
}
