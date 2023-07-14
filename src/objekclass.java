import java.util.ArrayList;

public class objekclass {
    private String UserID;
    private String UserName;
    private String FullName;

    private String ArrayList<String>

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public ArrayList<String> getUserPosts() {
        return UserPosts;
    }

    public void setUserPosts(ArrayList<String> userPosts) {
        UserPosts = userPosts;
    }

    public objekclass() { //constructor
    }

        public objekclass(String id,String usr,String name, ArrayList<String> posts){
        this.UserID = id;
        this.UserName = usr;
        this.FullName = name;
        this.UserPosts = posts;


        




        }


    }

