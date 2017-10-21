package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class VoteActivity extends AppCompatActivity {

    int user_num;
    int vote_num;
    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        globals = (Globals)this.getApplication();

        Intent intent = getIntent();
        user_num = intent.getIntExtra("user_num", 0);
        vote_num = -1;

        Spinner vote_spinner = (Spinner)findViewById(R.id.vote_spinner);

        String[] user_nums = new String[globals.CountAliveUser() - 1];
        int tmp_num = 0;
        for (int i = 0; i < globals.CountAliveUser() - 1;) {
            if(tmp_num != user_num && globals.users.get(tmp_num).is_alive){
                user_nums[i] = Integer.toString(tmp_num);
                i++;
            }
            tmp_num++;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, user_nums);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vote_spinner.setAdapter(arrayAdapter);

        vote_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                String item = (String) spinner.getSelectedItem();
                vote_num = Integer.parseInt(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button Vote_ok_button = (Button)findViewById(R.id.Vote_ok_button);
        Vote_ok_button.setText(Integer.toString(user_num));
        Vote_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next_alive_user_num = -1;
                for(int i = user_num + 1; i < globals.users.size(); i++){
                    if(globals.users.get(i).is_alive){
                        next_alive_user_num = i;
                        break;
                    }
                }
                globals.votes[vote_num]++;
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
        });


    }
}
