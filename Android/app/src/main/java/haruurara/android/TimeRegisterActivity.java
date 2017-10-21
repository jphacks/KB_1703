package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimeRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_register);

        Button TimeRegister_ok_button = (Button)findViewById(R.id.TimeRegister_ok_button);
        TimeRegister_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.BrainStormActivity");
                startActivity(intent);
                TimeRegisterActivity.this.finish();
            }
        });

        Button TimeRegister_back_button = (Button)findViewById(R.id.TimeRegister_back_button);
        TimeRegister_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.UserResisterActivity");
                startActivity(intent);
                TimeRegisterActivity.this.finish();
            }
        });


    }
}
