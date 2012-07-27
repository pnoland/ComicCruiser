package com.cs446.ComicCruiser.View;

import java.util.List;

import com.cs446.ComicCruiser.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FileArrayAdapter extends ArrayAdapter<Option>{

	private Context context;
	private int id;
	private List<Option>items;
	
	public FileArrayAdapter(Context context, int textViewResourceId,
			List<Option> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		id = textViewResourceId;
		items = objects;
	}
	public Option getItem(int i)
	 {
		 return items.get(i);
	 }
	 @Override
       public View getView(int position, View convertView, ViewGroup parent) {
               View v = convertView;
               if (v == null) {
                   LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   v = vi.inflate(id, null);
               }
               final Option option = items.get(position);
               if (option != null) {
                       TextView fileName = (TextView) v.findViewById(R.id.importFileName);
                       TextView fileData = (TextView) v.findViewById(R.id.importFileData);
                       
                       if(fileName!=null)
                       	fileName.setText(option.getName());
                       if(fileData!=null)
                       	fileData.setText(option.getData());
                       
               }
               return v;
       }

}
