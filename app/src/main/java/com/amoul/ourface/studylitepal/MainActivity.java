package com.amoul.ourface.studylitepal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.amoul.ourface.studylitepal.adapter.SqlAdapter;

import org.loader.litepal.Person;

import java.util.ArrayList;
import java.util.List;

import utils.SqlUtils;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private EditText et_name;
    private EditText et_time;
    private EditText et_age;
    private EditText et_sex;
    List<Person> list = new ArrayList<>();
    private ListView listview;
    private Context context;
    private SqlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        listview = (ListView) findViewById(R.id.listview);
        adapter = new SqlAdapter(context, list);
        listview.setAdapter(adapter);

        et_name = (EditText) findViewById(R.id.name);
        et_time = (EditText) findViewById(R.id.time);
        et_age = (EditText) findViewById(R.id.age);
        et_sex = (EditText) findViewById(R.id.sex);

        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);

        button1.setOnClickListener(onClick_sql);
        button2.setOnClickListener(onClick_sql);
        button3.setOnClickListener(onClick_sql);
        button4.setOnClickListener(onClick_sql);
        button5.setOnClickListener(onClick_sql);
        button6.setOnClickListener(onClick_sql);
    }

    View.OnClickListener onClick_sql = new View.OnClickListener() {
        public static final String TAG = "MainActivity";

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button://增加数据
                    String name = et_name.getText() + "";
                    String time = et_time.getText() + "";
                    String age = et_age.getText() + "";
                    String sex = et_sex.getText() + "";

                    SqlUtils.insertData(name, time, age, sex);
                    refreshData();
                    break;
                case R.id.button2://删除当前时间的数据
                    String time2 = et_time.getText() + "";
                    SqlUtils.deletePersonTime(time2);

                    refreshData();
                    break;
                case R.id.button3://修改当前时间的年龄
                    String time3 = et_time.getText() + "";
                    String age3 = et_age.getText() + "";
                    SqlUtils.updataSql(time3, age3);

                    refreshData();
                    break;
                case R.id.button4://根据时间查找
                    list.clear();
                    String time4 = et_time.getText() + "";
                    if (time4 != null) {
                        Person person = SqlUtils.timeGetlookupDataAll(time4);
                        list.add(person);
                        adapter.notifyDataSetChanged();
                        Log.e(TAG, "onClick: " + person.getName() + " " + person.getTime() + " " + person.getAge() + " " + person.getSex());
                    }
                    break;
                case R.id.button5://查找所有对象
                    Log.e(TAG, "onClick: " + list.size());
                    if (list != null) {
                        refreshData();
                    }
                    break;
                case R.id.button6://删除所有对象
                    SqlUtils.deleteDataAll();
                    refreshData();
                    break;
            }
        }
    };
    //刷新数据
    private void refreshData(){
        SqlUtils.lookupDataAll(list);
        adapter.notifyDataSetChanged();
    }
}
