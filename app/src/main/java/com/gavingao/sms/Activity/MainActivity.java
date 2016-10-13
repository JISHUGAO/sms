package com.gavingao.sms.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gavingao.sms.R;
import com.gavingao.sms.Adapter.SmsAdapter;
import com.gavingao.sms.Bean.SmsBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_main);
      //  search = (TextView)findViewById(R.id.search);
        //    search.setOnClickListener(this);

        setListView();
    }

    @Override
    public void onStart() {
        super.onStart();
        //setListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        setListView();
    }

    private void setListView() {
        final List<SmsBean> appList = getSmsFromPhone();
        //Gson gson = new Gson();
        //List<SmsBean> appList = gson.fromJson(JSON, new TypeToken<List<SmsBean>>(){}.getType());

        SmsAdapter smsAdapter = new SmsAdapter(MainActivity.this, appList);

        //Log.d("--------", m.g);
        ListView listview = (ListView)findViewById(R.id.list);
        listview.setAdapter(smsAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SmsDetail.class);
                intent.putExtra("address", appList.get(position).getAddress());
                intent.putExtra("body", appList.get(position).getBody());
                startActivity(intent);
                //Toast.makeText(MainActivity.this, appList.get(position).getAddress(), Toast.LENGTH_SHORT).show();
            }
        });


        TextView smsSend = (TextView)findViewById(R.id.sms_send);
        smsSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SendSMS.class);
                startActivity(i);
            }
        });
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
        String[] columns = new String[] { "body", "address", "date" };//"_id", "address", "person",, "date", "type
        Cursor cur = cr.query(SMS_INBOX, columns, null, null, "date desc");
        List<SmsBean> smsList = new ArrayList<SmsBean>();
        if (cur.moveToFirst()) {
            do {
                SmsBean s = new SmsBean();
                s.setAddress(cur.getString(cur.getColumnIndex("address")));
                s.setBody(cur.getString(cur.getColumnIndex("body")));
                String date = cur.getString(cur.getColumnIndex("date"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = format.format(Long.parseLong(date));
                s.setDate(date);
                //Log.d("---------------", cur.getString(cur.getColumnIndex("body")));
                //Log.d("---------------", cur.getString(cur.getColumnIndex("address")));
                smsList.add(s);
            } while(cur.moveToNext());
            cur.close();
        }

        return smsList;
    }


}
