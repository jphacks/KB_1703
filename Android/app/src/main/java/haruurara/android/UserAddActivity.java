package haruurara.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAddActivity extends AppCompatActivity {

    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        globals = (Globals)this.getApplication();

        final EditText new_user_name = (EditText) findViewById(R.id.new_user_name);

        Button new_user_button = (Button)findViewById(R.id.new_user_button);
        new_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new_user_name.length() > 0){
                    globals.users.add(new User(new_user_name.getText().toString()));
                    UserAddActivity.this.finish();
                }
            }
        });
    }
}
