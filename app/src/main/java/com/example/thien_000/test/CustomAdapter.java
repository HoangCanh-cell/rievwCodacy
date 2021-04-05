package com.example.thien_000.test;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by canh2020.
 */
public class CustomAdapter extends BaseAdapter{
    private ArrayList<Lichsu> _Contacts;
    private Activity context;
    private LayoutInflater inflater;
    public CustomAdapter(Activity context,ArrayList<Lichsu> _Contacts){
        super();
        this.context=context;
        this._Contacts=_Contacts;
        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return _Contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return _Contacts.get(position).getTime();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView tvtime, tvphanloai,tvsotien ,tvtaikhoan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;//xử lý listview, tuy chỉnh 1 view.
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_row_view, null);
            holder.tvtime = (TextView) convertView.findViewById(R.id.tvtime);
            holder.tvphanloai = (TextView) convertView.findViewById(R.id.tvphannhom);
            holder.tvsotien = (TextView) convertView.findViewById(R.id.tvsotien);
            holder.tvtaikhoan = (TextView) convertView.findViewById(R.id.tvtaikhoan);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.tvtime.setText("" + _Contacts.get(position).getTime());
        holder.tvphanloai.setText("" + "" + _Contacts.get(position).getPhanloai());
        holder.tvsotien.setText("" + "" + _Contacts.get(position).getSotien());
        holder.tvtaikhoan.setText("" + "" + _Contacts.get(position).getTaikhoan());

        return convertView;
    }
}
