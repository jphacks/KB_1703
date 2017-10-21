package haruurara.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

/**
 * Created by kentakimura on 2017/10/21.
 */

public class ListViewAdapter extends ArrayAdapter<EditText> {
    private LayoutInflater layoutInflater_;

    public ListViewAdapter(Context context, int editTextResourceId, List<EditText> objects) {
        super(context, editTextResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        EditText item = (EditText)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.edittextrow, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        EditText editText;
        editText = (EditText) convertView.findViewById(R.id.editText);

        return convertView;
    }
}