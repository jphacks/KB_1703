package haruurara.android;

import android.app.Application;

/**
 * Created by ian on 2017/10/21.
 */

public class Globals extends Application {

    User users[];
    int time;//分

    //誰か生き残りのカウントの実装をお願いします！！！！！！
    public int CountAliveUser(){
        int cnt = 3;
        return cnt;
    }

}
