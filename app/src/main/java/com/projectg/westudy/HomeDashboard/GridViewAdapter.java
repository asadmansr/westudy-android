package com.projectg.westudy.HomeDashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projectg.westudy.R;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] gridviewValues;

    public GridViewAdapter(Context context, String[] gridviewValues) {
        this.context = context;
        this.gridviewValues = gridviewValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.gridview_elem_layout, null);

            TextView circle_title_tv = (TextView) gridView.findViewById(R.id.circle_item_tv);
            circle_title_tv.setText(gridviewValues[position]);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return gridviewValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}