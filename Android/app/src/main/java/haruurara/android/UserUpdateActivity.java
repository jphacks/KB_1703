package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserUpdateActivity extends AppCompatActivity {
    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        globals = (Globals)this.getApplication();

        final int userNum = getIntent().getIntExtra("userNum", 0);
        final EditText name = (EditText) findViewById(R.id.name);
        name.setText(globals.users.get(userNum).name);

        Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update
                globals.users.get(userNum).name = name.getText().toString();
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.UserResisterActivity");
                startActivity(intent);
            }
        });

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete
                globals.users.remove(userNum);
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.UserResisterActivity");
                startActivity(intent);
            }
        });
    }
}
