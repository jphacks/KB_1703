package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.id.list;

public class TimeRegisterActivity extends AppCompatActivity {

    private Spinner spinner;
    // 選択肢
    private String list[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_register);

        globals = (Globals)this.getApplication();
        //spinner
        spinner = (Spinner)findViewById(R.id.TimeRegister_spinner);
        // ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner に adapter をセット
        spinner.setAdapter(adapter);
        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                int item = Integer.parseInt((String)spinner.getSelectedItem());
                globals.time = item;
            }
            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        Button TimeRegister_ok_button = (Button)findViewById(R.id.TimeRegister_ok_button);
        TimeRegister_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.RoundStartActivity");
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
