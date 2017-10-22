package haruurara.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.id.list;

public class WriteIdeaActivity extends AppCompatActivity {

    EditText ideaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_idea);

        ideaEditText = (EditText) findViewById(R.id.ideaEditText);
        Button main_page_button = (Button)findViewById(R.id.finishButton);
        main_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // 入力されたideaを取得
                        String idea = ideaEditText.getText().toString();

                        // postするjsonstringを作成
                        String jsonString = "{\"app_id\":\"0a38399a4eaad5cce88db74833bd1d69fb7019649fa43f4d3bdfaa9da3df1267\",\"sentence\":\"" + idea + "\",\"info_filter\":\"form\",\"pos_filter\":\"名詞\"}";

                        // post
                        String url = "https://labs.goo.ne.jp/api/morph";
                        OkHttpClient client = new OkHttpClient();
                        MediaType MIMEType= MediaType.parse("application/json; charset=utf-8");
                        RequestBody requestBody = RequestBody.create (MIMEType, jsonString);
                        Request request = new Request.Builder().url(url).post(requestBody).build();
                        try {
                            // 一旦responseの中身を確認したい
                            Response response = client.newCall(request).execute();
                            String responseString = response.body().string();

                            try {
                                // jsonを扱えるようにする
                                JSONObject jsonObject = new JSONObject(responseString);
                                JSONArray jsonArray = jsonObject.getJSONArray("word_list");

                                // set firebase
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                Date now = new Date(System.currentTimeMillis());
                                DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");
                                String nowText = formatter.format(now);

                                // get data
                                ArrayList<String> list1 = new ArrayList<String>();
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONArray ja = jsonArray.getJSONArray(i);
                                    for(int j = 0; j < ja.length(); j++){
                                        list1.add(ja.getJSONArray(j).get(0).toString());
                                    }
                                }
                                Set<String> set = new HashSet<>(list1);
                                List<String> list2 = new ArrayList<>(set);

                                StringBuilder builder = new StringBuilder();
                                for(String str : list2) {
                                    builder.append(str).append(",");
                                }
                                String keyword = builder.substring(0, builder.length() - 1);

                                // send data to firebase
                                for(int i = 0; i < list2.size(); i++){
                                    database.getReference("/keywords/" + list2.get(i) + "/" + nowText + "/content").setValue(idea);
                                    database.getReference("/keywords/" + list2.get(i) + "/" + nowText + "/keyword").setValue(keyword);
                                }
                                database.getReference("/ideas/" + nowText + "/content").setValue(idea);
                                database.getReference("/ideas/" + nowText + "/keyword").setValue(keyword);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            Intent intent = new Intent();
                            intent.setClassName("haruurara.android", "haruurara.android.StartActivity");
                            startActivity(intent);
                            WriteIdeaActivity.this.finish();
                        }


                    }
                }).start();
            }
        });


        Button no_send_idea_finish_button = (Button)findViewById(R.id.noSendIdeaFinishButton);
        no_send_idea_finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("haruurara.android", "haruurara.android.StartActivity");
                startActivity(intent);
                WriteIdeaActivity.this.finish();
            }
        });

    }
}
