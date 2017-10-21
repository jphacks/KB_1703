package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    Globals globals;
    int user_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        globals = (Globals)this.getApplication();

        Intent intent = getIntent();
        user_num = intent.getIntExtra("user_num", 0);

        TextView  textView = (TextView)findViewById(R.id.confirm_text_view);
        textView.setText(globals.users.get(user_num).name + "さん、フィードバックを見る心の準備はできましたか？");

        Button Confirm_ok_button = (Button)findViewById(R.id.Confirm_ok_button);
        Confirm_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.FeedbackActivity");
                intent.putExtra("user_num", user_num);
                startActivity(intent);
                ConfirmActivity.this.finish();
            }
        });
    }
}
