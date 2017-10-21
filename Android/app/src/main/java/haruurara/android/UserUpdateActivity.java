package haruurara.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class UserUpdateActivity extends AppCompatActivity {
    Globals globals;

    ImageView imageView;
    int userNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        globals = (Globals)this.getApplication();


        userNum = getIntent().getIntExtra("userNum", 0);
        final EditText name = (EditText) findViewById(R.id.name);
        name.setText(globals.users.get(userNum).name);

        imageView = (ImageView)findViewById(R.id.user_image);
        imageView.setImageBitmap(globals.users.get(userNum).my_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 42);
            }
        });

        Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update
                globals.users.get(userNum).name = name.getText().toString();
                UserUpdateActivity.this.finish();
            }
        });

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete
                globals.users.remove(userNum);
                UserUpdateActivity.this.finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                    globals.users.get(userNum).my_image = bitmap;
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
