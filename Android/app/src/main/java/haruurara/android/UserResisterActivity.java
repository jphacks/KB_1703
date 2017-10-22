package haruurara.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import io.realm.Realm;

public class UserResisterActivity extends AppCompatActivity {

    Globals globals;
    ListView listView;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_resister);


        globals = (Globals)this.getApplication();

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.UserUpdateActivity");
                intent.putExtra("userNum", position);
                Log.d("position", String.valueOf(position));
                startActivity(intent);
            }
        });

        Button userAddButton = (Button)findViewById(R.id.UserRegister_addUser_button);
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
                if(globals.users.size() >= 3) {
                    Intent intent = new Intent();
                    intent.setClassName("haruurara.android", "haruurara.android.TimeRegisterActivity");
                    startActivity(intent);
                    UserResisterActivity.this.finish();
                }else{
                    new AlertDialog.Builder(UserResisterActivity.this)
                            .setTitle("すまん。進めへんわ。。。")
                            .setMessage("ユーザを3人以上作成してください。")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });

        mRealm = Realm.getDefaultInstance();

    }

    @Override
    protected void onResume(){
        super.onResume();
        renewUsers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    public void renewUsers(){
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for(int i = 0; i < globals.users.size(); i++){
            adapter.add(globals.users.get(i).name);
        }*/
        UserAdapter adapter = new UserAdapter(this,R.layout.picture_list,globals.users);
        listView.setAdapter(adapter);
    }
}
