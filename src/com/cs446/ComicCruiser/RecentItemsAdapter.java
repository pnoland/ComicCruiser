package com.cs446.ComicCruiser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecentItemsAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public RecentItemsAdapter(Context context, String[] values) {
		super(context, R.layout.recent_items_row_layout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.recent_items_row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setText(values[position]);
		// Change the icon for Windows and iPhone
		String s = values[position];
		return rowView;
	}
}