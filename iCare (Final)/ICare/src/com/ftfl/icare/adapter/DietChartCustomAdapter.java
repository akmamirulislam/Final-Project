package com.ftfl.icare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icare.R;
import com.ftfl.icare.model.DietChart;

public class DietChartCustomAdapter extends ArrayAdapter<DietChart> {
	Activity mContext;
	DietChart mDiet;

	// Profile ArrayList
	ArrayList<DietChart> mDietChartArrayList;

	public DietChartCustomAdapter(Activity context, ArrayList<DietChart> eDietChartArrayList) {
		super(context, R.layout.activity_diet_chart_single_row, eDietChartArrayList);
		this.mContext = context;
		this.mDietChartArrayList = eDietChartArrayList;
	}

	// holder Class to contain inflated xml file elements
	static class ViewHolder {
		public TextView mTvDietType;
		public TextView mTvDietTime;
		public TextView mTvDietFeast;
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mDiet = mDietChartArrayList.get(position);

		// ViewHolder Object
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {

			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.activity_diet_chart_single_row,	parent, false);

			// ViewHolder Object
			mVHolder = new ViewHolder();

			// findViewById
			mVHolder.mTvDietType = (TextView) rowView.findViewById(R.id.tvSingleDirtType);
			mVHolder.mTvDietTime = (TextView) rowView.findViewById(R.id.tvSingleDirtTime);
			mVHolder.mTvDietFeast = (TextView) rowView.findViewById(R.id.tvSingleDirtFeast);

			// Set ViewHolder
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();
		mVHolder.mTvDietType.setText(mDiet.getmDietType().toString());
		mVHolder.mTvDietTime.setText(mDiet.getmDietTime().toString());
		mVHolder.mTvDietFeast.setText(mDiet.getmDietFeast().toString());

		return rowView;
	}

}
