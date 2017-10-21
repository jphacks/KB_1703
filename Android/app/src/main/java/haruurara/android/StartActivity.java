package haruurara.android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button main_page_button = (Button)findViewById(R.id.main_page_button);
        main_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.UserResisterActivity");
                startActivity(intent);
                StartActivity.this.finish();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/ideas");
        Date now = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");
        String nowText = formatter.format(now);
        database.getReference("/ideas/" + nowText + "/context").setValue("Hello, World!");
        database.getReference("/ideas/" + nowText + "/keyword").setValue("田村さん");

        database.getReference("/keywords/" + "田村さん" + "/" + nowText + "/context").setValue("Hello, World!");
        database.getReference("/keywords/" + "田村さん" + "/" + nowText + "/keyword").setValue("田村さん");



    }
}
