package INFO;

import javafx.scene.image.Image;

public class account {
    public account(String username, String password, String firstname, String lastname, String email, String phoneNumber , Image profilePic){
        this.password = password;
        this.username = username;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePic = profilePic;
        tier = tier.Normal;
    }

     public enum tier{
        Normal,ADMIN,Demo
    }
    tier tier;
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public Image profilePic;
}
