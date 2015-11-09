package com.zui.kid.sharepreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText keyEt;
    private EditText valueEt;
    private Button insertBt;
    private Button deleteBt;
    private Button modifyBt;
    private Button queryBt;
    private TextView preferencesTv;
    private static final String DATABASE = "DataBase";
    private Button clearBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyEt = (EditText) findViewById(R.id.et_key);
        valueEt = (EditText) findViewById(R.id.et_value);

        insertBt = (Button) findViewById(R.id.bt_insert);
        deleteBt = (Button) findViewById(R.id.bt_delete);
        modifyBt = (Button) findViewById(R.id.bt_modify);
        queryBt = (Button) findViewById(R.id.bt_query);
        clearBt = (Button) findViewById(R.id.bt_clear);


        preferencesTv = (TextView) findViewById(R.id.tv_preferences);
        preferencesTv.setText(MainActivity.this.print());

        insertBt.setOnClickListener(new OperateOnclikListener());
        deleteBt.setOnClickListener(new OperateOnclikListener());
        modifyBt.setOnClickListener(new OperateOnclikListener());
        queryBt.setOnClickListener(new OperateOnclikListener());
        clearBt.setOnClickListener(new OperateOnclikListener());



    }


    class OperateOnclikListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            SharedPreferences sp = getSharedPreferences(DATABASE, Activity.MODE_PRIVATE);

            SharedPreferences.Editor editor = sp.edit();

            String key = keyEt.getText().toString();
            String value = valueEt.getText().toString();
            switch (v.getId()) {
                case R.id.bt_insert:
                    editor.putString(key, value);
                    editor.commit();
                    Log.e("zengrong", "已经插入");
                    preferencesTv.setText(MainActivity.this.print());
                    break;
                case R.id.bt_delete:
                    editor.remove(key);
                    editor.commit();
                    Log.e("zengrong","已经删除");
                    preferencesTv.setText(MainActivity.this.print());
                    break;
                case R.id.bt_modify:
                    editor.putString(key, value);
                    editor.commit();
                    Log.e("zengrong", "已经修改");
                    preferencesTv.setText(MainActivity.this.print());
                    break;
                case R.id.bt_query:
                    sp.getString(key, "");
                    valueEt.setText(sp.getString(key, ""));
                    Log.e("zengrong", "已经查询");
                    preferencesTv.setText(MainActivity.this.print());
                    break;
                case R.id.bt_clear:
                    editor.clear();
                    editor.commit();
                    Log.e("zengrong", "已经清除");
                    preferencesTv.setText(MainActivity.this.print());
                    break;
            }

        }
    }

    private String print() {
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/data/data/com.zui.kid.sharepreferencesdemo/shared_prefs/DataBase.xml")));
            String str;
            while ((str = reader.readLine()) != null) {
                buffer.append(str + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();

    }

}
