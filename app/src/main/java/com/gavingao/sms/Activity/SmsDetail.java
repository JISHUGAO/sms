package com.gavingao.sms.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gavingao.sms.R;

/**
 * Created by Administrator on 2016/10/10 0010.
 */

public class SmsDetail extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String addressText = bundle.getString("address");
        String contentText = bundle.getString("body");

        TextView address = (TextView)findViewById(R.id.sms_detail_address);
        TextView content = (TextView)findViewById(R.id.sms_detail_content);

        address.setText(addressText);
        content.setText(contentText);

        TextView btnBack = (TextView)findViewById(R.id.sms_detail_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsDetail.this.finish();
            }
        });
    }
}
