package com.gavingao.sms;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    TextView search;

    private Uri SMS_INBOX = Uri.parse("content://sms/");
    private String JSON = "[" +
            "{" +
            "\"body\":123," +
            "\"address\": \"的开发\"" +
            "}" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        search = (TextView)findViewById(R.id.search);
        search.setOnClickListener(this);

        List<SmsBean> appList = getSmsFromPhone();
        //Gson gson = new Gson();
        //List<SmsBean> appList = gson.fromJson(JSON, new TypeToken<List<SmsBean>>(){}.getType());
        SmsAdapter smsAdapter = new SmsAdapter(MainActivity.this, appList);

        ListView listview = (ListView)findViewById(R.id.list);
        listview.setAdapter(smsAdapter);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    public List<SmsBean> getSmsFromPhone() {
        ContentResolver cr = getContentResolver();
        String[] columns = new String[] { "body", "address" };//"_id", "address", "person",, "date", "type
        Cursor cur = cr.query(SMS_INBOX, columns, null, null, "date desc");
        List<SmsBean> smsList = new ArrayList<SmsBean>();
        if (cur.moveToFirst()) {
            do {
                SmsBean s = new SmsBean();
                s.setAddress(cur.getString(cur.getColumnIndex("address")));
                s.setBody(cur.getString(cur.getColumnIndex("body")));
                //Log.d("---------------", cur.getString(cur.getColumnIndex("body")));
                //Log.d("---------------", cur.getString(cur.getColumnIndex("address")));
                smsList.add(s);
            } while(cur.moveToNext());

        }

        return smsList;
    }
}
