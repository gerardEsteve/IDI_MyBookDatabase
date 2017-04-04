package com.example.pr_idi.mydatabaseexample;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gerard on 04/01/2017.
 */
public class myExpandableListAdapter extends BaseExpandableListAdapter {

    private Context cont;
    private List<String> lHead;
    private HashMap<String, List<String>> lChild;

    public myExpandableListAdapter(Context context, List<String> listHeader,
                                   HashMap<String, List<String>> listChild) {
        this.cont = context;
        this.lHead = listHeader;
        this.lChild = listChild;
    }


    @Override
    public int getGroupCount() { return this.lHead.size(); }

    @Override
    public int getChildrenCount(int groupPosition) {return this.lChild.get(this.lHead.get(groupPosition)).size(); }


    @Override
    public Object getGroup(int groupPosition) { return this.lHead.get(groupPosition);}

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.lChild.get(this.lHead.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) { return groupPosition; }

    @Override
    public long getChildId(int groupPosition, int childPosition) { return childPosition; }

    @Override
    public boolean hasStableIds() {return false;}

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.help_grup1, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.help_grup, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
