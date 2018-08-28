package com.amoul.ourface.studylitepal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amoul.ourface.studylitepal.R;

import org.loader.litepal.Person;

import java.util.List;

/**
 * Created by Administrator on 2018/8/24 0024.
 */

public class SqlAdapter extends BaseAdapter{
    private Context context;
    private List<Person>list;
    private final LayoutInflater inflater;

    public SqlAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (holder==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.listview_adapter,null);
            holder.name=convertView.findViewById(R.id.tv_name);
            holder.time=convertView.findViewById(R.id.tv_time);
            holder.age=convertView.findViewById(R.id.tv_age);
            holder.sex=convertView.findViewById(R.id.tv_sex);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        String name=list.get(position).getName();
        String time=list.get(position).getTime();
        String age=list.get(position).getAge();
        String sex=list.get(position).getSex();

        holder.name.setText(name+"");
        holder.time.setText(time+"");
        holder.age.setText(age+"");
        holder.sex.setText(sex+"");
        return convertView;
    }

    class ViewHolder{
        private TextView name;
        private TextView time;
        private TextView age;
        private TextView sex;

    }
}
