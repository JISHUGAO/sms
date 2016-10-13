package com.gavingao.sms.Activity;

import android.content.Intent;
import android.provider.Contacts;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gavingao.sms.R;

public class SendSMS extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        //返回
        TextView back = (TextView)findViewById(R.id.sms_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 发短信
        TextView send = (TextView)findViewById(R.id.send);
        final EditText phoneCode = (EditText)findViewById(R.id.send_code);
        final EditText sendContent = (EditText)findViewById(R.id.send_content);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = phoneCode.getText().toString();
                String content = sendContent.getText().toString();
                if ("".equals(code)) {
                    Toast.makeText(SendSMS.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(content)) {
                    Toast.makeText(SendSMS.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Boolean result = send(code, content);
                if (result) {
                    Toast.makeText(SendSMS.this, "发送成功", Toast.LENGTH_SHORT).show();
                    SendSMS.this.finish();
                }
            }
        });

        //选择联系人
        TextView select = (TextView)findViewById(R.id.sms_select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Contacts.People.CONTENT_URI);
                startActivity(intent);

                /*Uri uri = Uri.parse("tel:18306447163");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);*/

                /*Intent i = new Intent();
                i.setAction("android.intent.action.CALL_BUTTON");
                startActivity(i);*/
            }
        });

    }


    private Boolean send(String phone, String content) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, content, null, null);
        return true;
    }
}
