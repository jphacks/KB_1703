package haruurara.android;

import android.app.Application;
import java.util.ArrayList;

/**
 * Created by ian on 2017/10/21.
 */

public class Globals extends Application {

    ArrayList<User> users = new ArrayList<User>();
    int time;//分

    public int CountAliveUser(){
        int cnt = 0;
        for (User user: users) { if(user.is_alive) cnt++; }
        return cnt;
    }

}
