package com.example.task_4;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TodosAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    Context context;

    private ArrayList<String> todos = new ArrayList<String>();

    public TodosAdapter(Context context,ArrayList<String> todos) {
        this.todos=new ArrayList<>(todos);
        this.context=context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final String item){
        todos.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.checkbox, null);
            holder = new ViewHolder();
            //holder.textView = (TextView) convertView.findViewById(R.id.text123);
            holder.check = (CheckBox) convertView.findViewById(R.id.text321);
            //holder.check.
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Object item = this.getItem(position);
        holder.check.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //if (isChecked) {
                    Log.d("mytag","click");
                    //remove(item); // This somehow calls onCheckedChanged again
               // }
            }
        });*/
        return convertView;
    }
    /*public static class ViewHolder {

        public TextView textView;
        public CompoundButton check;
    }*/
}
