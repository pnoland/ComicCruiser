package com.cs446.ComicCruiser.View;

import java.io.File;
import java.util.ArrayList;

import com.cs446.ComicCruiser.R;
import com.cs446.ComicCruiser.ComicRepository.FileSystemWorker;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//Adapter used to update the view when performing library initialization
public class FoundItemsAdapter extends ArrayAdapter<String> {
	private final Context context;
	private ArrayList<File> values;
	private Activity activity;

	public FoundItemsAdapter(Context context, ArrayList<File> values, Activity activity) {
		super(context, R.layout.found_items_row_layout);
		this.context = context;
		this.values = values;
		this.activity = activity;
		
		//Search the file system asynchronously so the view can display files as they are found
		new Thread(new Runnable() {
			public void run() {
				listFoundItems();
		    }
		}).start();
	}
	
	public void reportFoundItems(ArrayList<File> items){
		values.clear();
		values.addAll(items);
		
		//Need the UI thread to update the view
		activity.runOnUiThread(new Runnable(){
			public void run(){
				notifyDataSetChanged();
			}
		});
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.initialization_items_row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setText(values.get(position).getPath());
		return rowView;
	}
	
	private void listFoundItems() {
		ArrayList<File> allItems = FileSystemWorker.findExistingIssues(this);
		reportFoundItems(allItems);
	}
}