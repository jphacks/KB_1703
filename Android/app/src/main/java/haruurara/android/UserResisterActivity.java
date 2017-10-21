package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class UserResisterActivity extends AppCompatActivity {

    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_resister);

        globals = (Globals)this.getApplication();

        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList<EditText> list = new ArrayList<>();
        list.add(new EditText(this));
        list.add(new EditText(this));
        list.add(new EditText(this));
        final ListViewAdapter listViewAdapter = new ListViewAdapter(this, R.layout.edittextrow, list);
        listView.setAdapter(listViewAdapter);

        Button userAddButton = (Button)findViewById(R.id.userAddButton);
        userAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(new EditText(UserResisterActivity.this));
                listViewAdapter.notifyDataSetChanged();
            }
        });

        Button userDeleteButton = (Button)findViewById(R.id.userDeleteButton);
        userDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size() > 0) {
                    list.remove(list.remove(list.size() - 1));
                    listViewAdapter.notifyDataSetChanged();
                }
            }
        });

        Button UserResister_ok_button = (Button)findViewById(R.id.UserResister_ok_button);
        UserResister_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    String name = list.get(i).getText().toString();
                    globals.users.add(new User(name));
                }
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
