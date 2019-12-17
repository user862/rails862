package com.example.task_4;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class CustomAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    public CustomAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void setStrikeText(CheckBox view, boolean set) {
        if (set) {
            view.setPaintFlags(view.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            view.setPaintFlags(view.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

    }

    ArrayList<Object> objs;

    public void addProjects(List<Project> projects) {
        objs = new ArrayList<>();
        for (Project project : projects) {
            objs.add((Object) project);
            sectionHeader.add(objs.size() - 1);
            notifyDataSetChanged();
            for (Todo todo : project.getTodos()) {
                objs.add((Object) todo);
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return objs.size();
    }


    @Override
    public String getItem(int position) {
        if (getItemViewType(position) == TYPE_ITEM)
            return ((Todo)objs.get(position)).getText();
        return ((Project)objs.get(position)).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.checkbox, null);
                    holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.snippet_item2, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        switch (rowType) {
            case TYPE_ITEM:
                Todo todo = (Todo)objs.get(position);
                Boolean status = todo.getStatus();
                setStrikeText(holder.checkBox,status);
                if(status) {
                    holder.checkBox.setChecked(true);
                }else {
                    holder.checkBox.setChecked(false);
                }
                holder.checkBox.setText(todo.getText());

                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( !v.isShown()) return;
                        CheckBox checkbox = (CheckBox) v;
                        setStrikeText(checkbox,checkbox.isChecked());
                        /*

                        send

                         */
                        if (checkbox.isChecked()) {
                            todo.changeStatus();
                        }
                        else {
                            todo.changeStatus();
                        }
                    }
                });
                break;

            case TYPE_SEPARATOR:
                holder.textView.setText(((Project)objs.get(position)).getTitle());
                break;
        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public CheckBox checkBox;
    }
}