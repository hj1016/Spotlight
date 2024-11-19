package com.example.spotlight.common;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotlight.R;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private int selectedGroupPosition = -1;

    public CustomExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size() / 2 + listDataChild.get(listDataHeader.get(groupPosition)).size() % 2;

        // return listDataChild.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    public Object getChild(int groupPosition, int childPosition) {
        int childIndex = childPosition * 2;
        return listDataChild.get(listDataHeader.get(groupPosition)).get(childIndex);

        // return listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    public Object getChild(int groupPosition, int childPosition, boolean second) {
        int childIndex = childPosition * 2 + 1;
        return listDataChild.get(listDataHeader.get(groupPosition)).size() > childIndex ? listDataChild.get(listDataHeader.get(groupPosition)).get(childIndex) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        ImageView imgSelected = convertView.findViewById(R.id.imgSelected);

        lblListHeader.setText(headerTitle);
        if (groupPosition == selectedGroupPosition) {
            lblListHeader.setTextColor(Color.WHITE);
            imgSelected.setVisibility(View.VISIBLE);
        } else {
            lblListHeader.setTextColor(Color.GRAY);
            imgSelected.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        TextView txtListChild1 = convertView.findViewById(R.id.subcategory1);
        TextView txtListChild2 = convertView.findViewById(R.id.subcategory2);

        txtListChild1.setText(getChild(groupPosition, childPosition).toString());
        txtListChild1.setTextColor(Color.WHITE);

        Object child2 = getChild(groupPosition, childPosition, true);
        if (child2 != null) {
            txtListChild2.setVisibility(View.VISIBLE);
            txtListChild2.setText(child2.toString());
            txtListChild2.setTextColor(Color.WHITE);
        } else {
            txtListChild2.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public int getSelectedGroupPosition() {
        return selectedGroupPosition;
    }

    public void setSelectedGroupPosition(int selectedGroupPosition) {
        this.selectedGroupPosition = selectedGroupPosition;
    }
}
