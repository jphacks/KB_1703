package haruurara.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BrainStormActivity extends AppCompatActivity {

    TextView mTimerText;
    MyCountDownTimer mTimer;

    public class MyCountDownTimer extends CountDownTimer {
        public boolean isRunning = false;

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
            Intent intent = new Intent();
            intent.setClassName("haruurara.android", "haruurara.android.VoteActivity");
            startActivity(intent);
            BrainStormActivity.this.finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_storm);
        mTimerText = (TextView) findViewById(R.id.timer_text);
        mTimerText.setText("1:00");
        mTimer = new MyCountDownTimer(1 * 60 * 1000, 100);
        mTimer.isRunning = true;
        mTimer.start();

        Button BrainStorm_ok_button = (Button)findViewById(R.id.BrainStorm_ok_button);
        BrainStorm_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.VoteActivity");
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
