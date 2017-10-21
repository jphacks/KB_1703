package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BrainStormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_storm);

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
