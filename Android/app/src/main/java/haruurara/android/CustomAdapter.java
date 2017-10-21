package haruurara.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kentakimura on 2017/10/21.
 */

public class CustomAdapter extends ArrayAdapter<User> {
    private LayoutInflater layoutInflater_;

    public CustomAdapter(Context context, int textViewResourceId, List<User> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        User item = (User)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.user_list, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        TextView textView;
        textView = (TextView)convertView.findViewById(R.id.name);
        textView.setText(item.name);

        TextView textView2;
        textView = (TextView)convertView.findViewById(R.id.alive);
        textView.setText(String.valueOf(item.is_alive));

        return convertView;
    }
}