/**
 * Created by jimmytran on 3/8/16.
 */
public class UserObject {
    private String user, status;
    UserObject(String user, String status){
        this.user = user;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getUser() {
        return user;
    }
}
