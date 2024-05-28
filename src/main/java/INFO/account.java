package INFO;

public class account {
    public account(String username, String password, String firstname, String lastname, String email, String phoneNumber){
        this.password = password;
        this.username = username;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        tier = tier.Normal;
    }

     public enum tier{
        Normal,ADMIN,Demo
    }
    tier tier;
    public String username;
    public String password;
    String firstName;
    String lastName;
    public String email;
    String phoneNumber;
}
