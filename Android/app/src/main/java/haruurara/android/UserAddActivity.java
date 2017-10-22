package haruurara.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class UserAddActivity extends AppCompatActivity {

    Globals globals;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        globals = (Globals)this.getApplication();

        imageView = (ImageView)findViewById(R.id.UserAdd_userImage_imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 42);
            }
        });

        final EditText new_user_name = (EditText) findViewById(R.id.UserAdd_userName_editText);



        Button UserAdd_back_button = (Button)findViewById(R.id.UserAdd_back_button);
        UserAdd_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.UserRegisterActivity");
                startActivity(intent);
                UserAddActivity.this.finish();
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
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
