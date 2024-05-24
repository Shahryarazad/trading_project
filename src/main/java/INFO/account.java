package INFO;

public class account {
    public account(String username , String password){
        this.password = password;
        this.username = username;
        tier = tier.Normal;
    }

     public enum tier{
        Normal,ADMIN,Demo
    }
    tier tier;
    String username;
    String password;
}
