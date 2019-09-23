package com.maroclance.PackageDB;

import java.util.List;

import com.maroclance.guessnumber.R;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Player> {

	Activity context;
	List<Player> list;
	
	public MyAdapter(Activity context, List<Player> list) {
		super(context, R.layout.rowhistory_ac, list);
		this.context = context;
		this.list = list;
	}
	
	public class viewHolder {
	
		public  TextView nameHtv;
		private TextView levelHtv;
		public  TextView scoreHtv;
		public  TextView timerHtv;
		public  ImageView imageHv;
		public  TextView timeClockHtv;
		public  TextView dateHtv;
		
		public viewHolder(View v) {
			
			nameHtv = (TextView)v.findViewById(R.id.nameHtv);
			levelHtv = (TextView)v.findViewById(R.id.levelHtv);
			scoreHtv = (TextView)v.findViewById(R.id.scoreHtv);
			timerHtv = (TextView)v.findViewById(R.id.timerHtv);
			imageHv = (ImageView)v.findViewById(R.id.smileHV);
			timeClockHtv = (TextView)v.findViewById(R.id.timeClockHtv);
			dateHtv = (TextView)v.findViewById(R.id.dateHtv);
			System.out.println("date in my adapter ---------------  "+ timeClockHtv);
		}
		
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		viewHolder holder = null;
		if(convertView == null)
		{
		convertView = context.getLayoutInflater().inflate(R.layout.rowhistory_ac, null);
		
		holder = new viewHolder(convertView);
		convertView.setTag(holder);
		}
		
		holder = (viewHolder)convertView.getTag();
		
		final Player item = list.get(position);
		
		holder.nameHtv.setText(item.getName());
		holder.levelHtv.setText(item.getLevel());
		holder.scoreHtv.setText(item.getAttempts());
		holder.timerHtv.setText(item.getTimer());
		holder.imageHv.setImageResource(item.getImage());
		holder.timeClockHtv.setText(item.getTime());
		holder.dateHtv.setText(item.getDate());

		
		return convertView;
	}
	
	
	
	

	
}
