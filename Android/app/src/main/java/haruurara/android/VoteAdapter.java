package haruurara.android;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ian on 2017/10/21.
 */

public class VoteAdapter extends ArrayAdapter<User>{

    private LayoutInflater layoutInflater;
    VoteActivity voteActivity;
    int user_num;
    List<User> userList;
    String feedback = "";

    public VoteAdapter(Context context, int resource, List<User> objects, int user_num) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        voteActivity = (VoteActivity)this.getContext();
        userList = objects;
        this.user_num = user_num;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        User item = (User)getItem(position);


        if(null == convertView){
            convertView = layoutInflater.inflate(R.layout.vote_list, null);
        }

        final TextView textView = (TextView)convertView.findViewById(R.id.vote_name);
        textView.setText(item.name);

        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.feedback_list);

        final Button button = (Button)convertView.findViewById(R.id.vote_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
            }
        });

        Button feedbackButton = (Button) convertView.findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userList.get(position).addFeedback(feedback);
                voteActivity.vote(position);
            }
        });
        if (position == user_num || !item.is_alive){
            textView.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
        }

        final EditText feedbackText = (EditText) convertView.findViewById(R.id.feedback_text);

        feedbackText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //EditTextが編集し終わったときに呼ばれる関数
            @Override
            public void afterTextChanged(Editable s) {
                //editのなかにこれいれないと更新されない
                feedback = String.valueOf(s);
            }
        });

        return convertView;
    }
}
