package haruurara.android;

import android.graphics.Bitmap;

import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by ian on 2017/10/21.
 */

public class User extends RealmObject {
    public String name;
    public boolean is_alive;
    public ArrayList<String> feedbacks;
    public Bitmap my_image;

    public User(String name, Bitmap bitmap){
        this.name = name;
        is_alive = true;
        my_image = bitmap;
        feedbacks = new ArrayList<String>();
    }

    public void addFeedback(String feedback){
        feedbacks.add(feedback);
    }

    public void clearFeedbacks(){
        feedbacks.clear();
    }



}
