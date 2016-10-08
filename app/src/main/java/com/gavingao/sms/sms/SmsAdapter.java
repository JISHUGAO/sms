package com.gavingao.sms.sms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

public class SmsAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private View myView;
    private List<Object> data;
    public SmsAdapter(Context c, List<Object> data) {
        layoutInflater = LayoutInflater.from(c);
        this.data = data;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            myView = layoutInflater.inflate(R.layout.listview_item, null);
        }

        EditText mobile = (EditText)myView.findViewById(R.id.mobile_code);
        EditText content = (EditText)myView.findViewById(R.id.content);
        mobile.setText(data.get(position).address);
        content.setText(data.get(position).body);
        return myView;
    }
}
