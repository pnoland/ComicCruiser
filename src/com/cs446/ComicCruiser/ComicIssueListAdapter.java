package com.cs446.ComicCruiser;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class ComicIssueListAdapter extends ArrayAdapter<String> {
	private Context context;
	private ArrayList<String> values;
	private ArrayList<String> original;

	private Filter filter;
	
	public ComicIssueListAdapter(Context context, ArrayList<String> values) {
		super(context, R.layout.recent_items_row_layout, values);
		this.context = context;
		this.values = values;
		this.original = (ArrayList<String>) values.clone();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.recent_items_row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		textView.setText(values.get(position));
		return rowView;
	}
    @Override
    public Filter getFilter()
    {
        if(filter == null)
            filter = new IssueNameFilter();
        return filter;
    }
    
    private class IssueNameFilter extends Filter{
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
            String prefix = constraint.toString().toLowerCase();

            if (prefix == null || prefix.length() == 0)
            {
                results.values = original;
                original = (ArrayList<String>) original.clone();
                results.count = original.size();
            }
            else
            {
                final ArrayList<String> nlist = new ArrayList<String>();

                for (int i=0; i < original.size(); i++)
                {
                    if (original.get(i).toLowerCase().contains(prefix))
                    {
                        nlist.add(original.get(i));
                    }
                }
                results.values = nlist;
                results.count = nlist.size();
            }
            return results;
		}

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        	notifyDataSetChanged();
            clear();
            values = new ArrayList<String>();
            for (int i=0; i< results.count; i++)
            {
            	values.add((String) (((ArrayList<String>)results.values).get(i)));
            	add(values.get(i));
            }
            notifyDataSetInvalidated();
        }
    	
    }
}