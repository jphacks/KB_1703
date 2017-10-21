package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class VoteActivity extends AppCompatActivity {

    int user_num;
    int vote_num;
    Globals globals;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        globals = (Globals)this.getApplication();

        Intent intent = getIntent();
        user_num = intent.getIntExtra("user_num", 0);
        vote_num = -1;

        listView = (ListView)findViewById(R.id.vote_list_view);
        VoteAdapter voteAdapter = new VoteAdapter(this, R.layout.vote_list, globals.users, user_num);
        listView.setAdapter(voteAdapter);

    }

    public void vote(int selected_vote_num){
        int next_alive_user_num = -1;
        for(int i = user_num + 1; i < globals.users.size(); i++){
            if(globals.users.get(i).is_alive){
                next_alive_user_num = i;
                break;
            }
        }
        globals.votes[selected_vote_num]++;
        if(next_alive_user_num != -1) {
            Intent intent = new Intent();
            intent.setClassName("haruurara.android", "haruurara.android.VoteActivity");
            intent.putExtra("user_num", next_alive_user_num);
            startActivity(intent);
            VoteActivity.this.finish();
        } else {
            int kill_user_num = -1;
            int kill_vote_num = 0;
            for (int i = 0; i < globals.votes.length; i++){
                if(globals.votes[i] > kill_vote_num){
                    kill_vote_num = globals.votes[i];
                    kill_user_num = i;
                }
            }
            Intent intent = new Intent();
            intent.setClassName("haruurara.android", "haruurara.android.KillActivity");
            intent.putExtra("kill_user_num", kill_user_num);
            startActivity(intent);
            VoteActivity.this.finish();
        }
    }
}
