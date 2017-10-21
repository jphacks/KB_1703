package haruurara.android;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
    List<EditText> edittextList;

    public ListViewAdapter(Context context, int editTextResourceId, List<EditText> objects) {
        super(context, editTextResourceId, objects);
        edittextList = objects;
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        EditText item = (EditText)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.edittextrow, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        EditText editText;
        editText = (EditText) convertView.findViewById(R.id.editText);

        //ココ重要
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //EditTexitが編集し終わったときに呼ばれる関数
            @Override
            public void afterTextChanged(Editable s) {
                //editのなかにこれいれないと更新されない
                edittextList.get(position).setText(String.valueOf(s));
            }
        });

        return convertView;
    }
}