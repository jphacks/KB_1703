package haruurara.android;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
            convertView = layoutInflater_.inflate(R.layout.picture_list, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        TextView textView = (TextView) convertView.findViewById(R.id.picture_name);
        textView.setText(item.name);
        textView.setTextColor(Color.WHITE);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.picture);
        imageView.setImageBitmap(item.my_image);
        ImageView deadView = (ImageView) convertView.findViewById(R.id.deadImageView);
        deadView.setImageResource(R.drawable.dead);
        if(!item.is_alive){
            deadView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}