package com.cs446.ComicCruiser;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FoundItemsAdapter extends ArrayAdapter<String> {
	private final Context context;
	private ArrayList<File> values;

	public FoundItemsAdapter(Context context, ArrayList<File> values) {
		super(context, R.layout.recent_items_row_layout);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.initialization_items_row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setText(values.get(position).getPath());
		// Change the icon for Windows and iPhone
		return rowView;
	}
	
	private class IssueSearchTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... args) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}
}