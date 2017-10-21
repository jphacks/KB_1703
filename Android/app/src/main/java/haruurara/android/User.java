package haruurara.android;

import java.util.ArrayList;

/**
 * Created by ian on 2017/10/21.
 */

public class User {

    public String name;
    public boolean is_alive;
    public ArrayList<String> feedbacks;

    public User(String name){
        this.name = name;
        is_alive = true;
    }

    public void addFeedback(String feedback){
        feedbacks.add(feedback);
    }

    public void clearFeedbacks(){
        feedbacks.clear();
    }


}
