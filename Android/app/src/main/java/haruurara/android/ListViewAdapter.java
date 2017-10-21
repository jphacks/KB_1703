package haruurara.android;

/**
 * Created by hiro on 2017/10/21.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<EditText> editTextList;

    public ListViewAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setEditTextList(ArrayList<EditText> editTextList) {
        this.editTextList = editTextList;
    }

    @Override
    public int getCount() {
        return editTextList.size();
    }

    @Override
    public Object getItem(int position) {
        return editTextList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return editTextList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.edittextrow, null);
        }
        convertView = layoutInflater.inflate(R.layout.edittextrow,parent,false);

        return convertView;
    }
}