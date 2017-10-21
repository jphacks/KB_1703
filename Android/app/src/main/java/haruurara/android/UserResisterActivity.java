package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class UserResisterActivity extends AppCompatActivity {

    Globals globals;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_resister);

        globals = (Globals)this.getApplication();

        listView = (ListView) findViewById(R.id.listView);

        Button userAddButton = (Button)findViewById(R.id.userAddButton);
        userAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.UserAddActivity");
                startActivity(intent);
            }
        });

        Button UserResister_ok_button = (Button)findViewById(R.id.UserResister_ok_button);
        UserResister_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.TimeRegisterActivity");
                startActivity(intent);
                UserResisterActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        renewUsers();
    }

    public void renewUsers(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for(int i = 0; i < globals.users.size(); i++){
            adapter.add(globals.users.get(i).name);
        }
        listView.setAdapter(adapter);
    }
}
