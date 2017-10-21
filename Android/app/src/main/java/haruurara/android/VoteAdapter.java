package haruurara.android;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ian on 2017/10/21.
 */

public class VoteAdapter extends ArrayAdapter<User>{

    private LayoutInflater layoutInflater;
    VoteActivity voteActivity;
    int user_num;

    public VoteAdapter(Context context, int resource, List<User> objects, int user_num) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        voteActivity = (VoteActivity)this.getContext();
        this.user_num = user_num;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        User item = (User)getItem(position);

        if(null == convertView){
            convertView = layoutInflater.inflate(R.layout.vote_list, null);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.vote_name);
        textView.setText(item.name);

        Button button = (Button)convertView.findViewById(R.id.vote_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteActivity.vote(position);
            }
        });
        if (position == user_num || !item.is_alive){
            textView.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
        }

        return convertView;
    }
}
