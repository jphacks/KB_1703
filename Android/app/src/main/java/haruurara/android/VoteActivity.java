package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoteActivity extends AppCompatActivity {

    int user_num;
    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        globals = (Globals)this.getApplication();

        Intent intent = getIntent();
        user_num = intent.getIntExtra("user_num", 0);

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
                if(next_alive_user_num != -1) {
                    Intent intent = new Intent();
                    intent.setClassName("haruurara.android", "haruurara.android.VoteActivity");
                    intent.putExtra("user_num", next_alive_user_num);
                    startActivity(intent);
                    VoteActivity.this.finish();
                } else {
                    Intent intent = new Intent();
                    intent.setClassName("haruurara.android", "haruurara.android.KillActivity");
                    startActivity(intent);
                    VoteActivity.this.finish();
                }
            }
        });


    }
}
