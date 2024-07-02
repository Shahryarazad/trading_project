package INFO;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.io.Serializable;
import java.util.ArrayList;

public class account implements Serializable {

    protected static ArrayList<account> accounts = new ArrayList<>();

    public account(String username, String password, String firstname, String lastname, String email, String phoneNumber){
        this.password = password;
        this.username = username;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
//        this.profilePic = profilePic;
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
    public ArrayList<Trade> trades = new ArrayList<>();
    public ArrayList<Request> buyRequest = new ArrayList<>();
    public ArrayList<Request> sellRequest = new ArrayList<>();

    //methods
    public static boolean signUp(account userAccount) {
        for (account a : accounts) {
            if (userAccount.username.equals(a.username))
                return false;
        }
        accounts.add(userAccount);
        return true;
    }

    public static account logIn(String username, String password) {
        for (account a : accounts) {
            if (username.equals(a.username) && password.equals(a.password))
                return a;
        }
        return null;
    }
}
