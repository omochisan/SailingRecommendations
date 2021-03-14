package com.example.sailingrecommendations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class ListViewAdapter extends ArrayAdapter<ListData> {
    private LayoutInflater layoutInflater;
    private int number = 1;

    public ListViewAdapter(Context context, int resource, List<ListData> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListData data = (ListData)getItem(position);
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView idText;
        TextView nameText;
        TextView typeText;

        idText = (TextView)convertView.findViewById(R.id.id);
        nameText = (TextView)convertView.findViewById(R.id.name);
        typeText = (TextView)convertView.findViewById(R.id.type);

        idText.setText(data.getId() + ".");
        nameText.setText(data.getName() + "　／");
        typeText.setText(data.getType());

        return convertView;
    }
}