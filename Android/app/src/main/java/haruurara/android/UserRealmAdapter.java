package haruurara.android;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by Takayuki on 2017/10/22.
 */

public class UserRealmAdapter extends RealmBaseAdapter<User> {

    private static class ViewHolder {
        TextView userName;
        Bitmap my_image;
    }

    public UserRealmAdapter(@Nullable OrderedRealmCollection<User> data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView = null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView) convertView.findViewById(R.id.new_user_name);
            viewHolder.my_image = (Bitmap) convertView.findViewById(R.id.new_user_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;

    }
}
