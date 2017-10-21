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
 * Created by kentakimura on 2017/10/22.
 */

public class UserAdapter extends ArrayAdapter<User> {

    private LayoutInflater layoutInflater;

    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource,objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        User item = (User)getItem(position);


        if(null == convertView){
            convertView = layoutInflater.inflate(R.layout.picture_list, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.picture_name);
        textView.setText(item.name);
        textView.setTextColor(Color.WHITE);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.picture);
        imageView.setImageBitmap(item.my_image);



        return convertView;
    }

}
