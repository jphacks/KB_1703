package haruurara.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BrainStormActivity extends AppCompatActivity {

    Globals globals;
    TextView mTimerText;
    MyCountDownTimer mTimer;

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long minite = millisUntilFinished / 1000 / 60;
            long second = millisUntilFinished / 1000 % 60;
            mTimerText.setText(String.format("%1d:%2$02d", minite, second));
        }

        @Override
        public void onFinish() {
            mTimerText.setText("0:00");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_storm);
        globals = (Globals)this.getApplication();
        int BrainTime = globals.time * globals.CountAliveUser();
        mTimerText = (TextView) findViewById(R.id.timer_text);
        //mTimerText.setText("3:00");
        mTimer = new MyCountDownTimer(BrainTime * 60 * 1000, 100);
        mTimer.start();

        Button BrainStorm_ok_button = (Button)findViewById(R.id.BrainStorm_ok_button);
        BrainStorm_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globals.votes = new int[globals.users.size()];
                int first_alive_user_num = 0;
                for(int i = 0; i < globals.users.size(); i++){
                    if(globals.users.get(i).is_alive){
                        first_alive_user_num = i;
                        break;
                    }
                }
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.VoteActivity");
                intent.putExtra("user_num", first_alive_user_num);
                startActivity(intent);
                BrainStormActivity.this.finish();
            }
        });

        Button BrainStorm_back_button = (Button)findViewById(R.id.BrainStorm_back_button);
        BrainStorm_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.TimeRegisterActivity");
                startActivity(intent);
                BrainStormActivity.this.finish();
            }
        });

    }
}
