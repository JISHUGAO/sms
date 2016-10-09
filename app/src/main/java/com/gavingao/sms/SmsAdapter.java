package com.gavingao.sms;

import android.content.Context;
import android.util.Log;
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

    private Context mContext;
    private List<SmsBean> list;
    public SmsAdapter(Context c, List<SmsBean> list) {
        this.mContext = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView;
        if (convertView == null) {
            myView = LayoutInflater.from(mContext).inflate(R.layout.listview_item, parent, false);
            //myView = layoutInflater.inflate(R.layout.listview_item, null);
        } else {
            myView = convertView;
        }

        EditText mobile = (EditText)myView.findViewById(R.id.mobile_code);
        EditText content = (EditText)myView.findViewById(R.id.content);

        Log.d("------------", list.get(position).getAddress());
        Log.d("------------", list.get(position).getBody());
        mobile.setText(list.get(position).getAddress());
        content.setText(list.get(position).getBody());
        return myView;
    }
}
