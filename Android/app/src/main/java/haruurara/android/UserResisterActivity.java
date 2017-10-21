package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserResisterActivity extends AppCompatActivity {

    Globals globals;
    private int userNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_resister);
        TextView textView = (TextView) findViewById(R.id.textview);

        globals = (Globals)this.getApplication();
        globals.time = 4;

        Button userAddButton = (Button)findViewById(R.id.userAddButton);
        userAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ユーザー追加
            }
        });

        Button UserResister_ok_button = (Button)findViewById(R.id.UserResister_ok_button);
        UserResister_ok_button.setText(Integer.toString(globals.time));
        UserResister_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.TimeRegisterActivity");
                startActivity(intent);
                UserResisterActivity.this.finish();
            }
        });

        Button UserResister_back_button = (Button)findViewById(R.id.UserResister_back_button);
        UserResister_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.StartActivity");
                startActivity(intent);
                UserResisterActivity.this.finish();
            }
        });

    }
}
