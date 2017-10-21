package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KillActivity extends AppCompatActivity {

    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill);

        globals = (Globals)this.getApplication();

        Button Kill_ok_button = (Button)findViewById(R.id.Kill_ok_button);
        Kill_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(globals.CountAliveUser() > 2) {
                    intent.setClassName("haruurara.android", "haruurara.android.RoundStartActivity");
                }else{
                    intent.setClassName("haruurara.android", "haruurara.android.FinishActivity");
                }
                startActivity(intent);
                KillActivity.this.finish();
            }
        });

    }
}
