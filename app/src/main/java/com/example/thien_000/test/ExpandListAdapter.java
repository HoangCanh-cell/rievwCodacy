package com.example.thien_000.test;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private int tongthu;
    private int tongchi;

    private ArrayList<Nhom> groups;

    public ExpandListAdapter(Context context, ArrayList<Nhom> groups, int tongthu,int tongchi) {
        this.context = context;
        this.groups = groups;
        this.tongthu= tongthu;
        this.tongchi= tongchi;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Child child = (Child) getChild(groupPosition, childPosition);
        if (convertView == null) {
            @SuppressWarnings("static-access")
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.ListItem);
        TextView iv = (TextView) convertView.findViewById(R.id.ListItem1);

        tv.setText(child.getPhanLoai().toString());
        iv.setText(child.getKhoanThuKhoanChi().toString());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @SuppressWarnings("static-access")
    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Nhom group = (Nhom) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.list_group2, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.listCha);
        TextView tt = (TextView) convertView.findViewById(R.id.tongthuchi);

        tv.setText(group.getPhanloai());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {

        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}