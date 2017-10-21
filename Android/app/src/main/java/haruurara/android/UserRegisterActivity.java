package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        Button UserRegister_ok_button = (Button)findViewById(R.id.UserRegister_ok_button);
        UserRegister_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.TimeRegisterActivity");
                startActivity(intent);
                UserRegisterActivity.this.finish();
            }
        });

        Button UserRegister_back_button = (Button)findViewById(R.id.UserRegister_back_button);
        UserRegister_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.StartActivity");
                startActivity(intent);
                UserRegisterActivity.this.finish();
            }
        });

    }
}
