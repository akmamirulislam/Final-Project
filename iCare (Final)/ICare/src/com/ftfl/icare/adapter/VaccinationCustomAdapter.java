package com.ftfl.icare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icare.R;
import com.ftfl.icare.model.Vaccination;

public class VaccinationCustomAdapter extends ArrayAdapter<Vaccination> {
	Activity mContext;
	Vaccination mVaccine;

	// Profile ArrayList
	ArrayList<Vaccination> mVaccinationArrayList;

	public VaccinationCustomAdapter(Activity context,
			ArrayList<Vaccination> eVaccinationArrayList) {
		super(context, R.layout.activity_vaccine_single_row,
				eVaccinationArrayList);
		this.mContext = context;
		this.mVaccinationArrayList = eVaccinationArrayList;
		
	}


	// holder Class to contain inflated xml file elements
	static class ViewHolder {
		public TextView mTvVaccineName;
		public TextView mTvVaccineTime;
		public TextView mTvVaccineDate;
		public TextView mTvVaccineStatus;
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mVaccine = mVaccinationArrayList.get(position);

		// ViewHolder Object
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {

			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.activity_vaccine_single_row,
					parent, false);

			// ViewHolder Object
			mVHolder = new ViewHolder();

			// findViewById
			mVHolder.mTvVaccineName = (TextView) rowView
					.findViewById(R.id.tvSingleVaccineName);
			mVHolder.mTvVaccineTime = (TextView) rowView
					.findViewById(R.id.tvSingleVaccineTime);
			mVHolder.mTvVaccineDate = (TextView) rowView
					.findViewById(R.id.tvSingleVaccineDate);
			mVHolder.mTvVaccineStatus = (TextView) rowView
					.findViewById(R.id.tvSingleVaccineStatus);

			// Set ViewHolder
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();
		mVHolder.mTvVaccineName.setText(mVaccine.getmName().toString());
		mVHolder.mTvVaccineTime.setText(mVaccine.getmTime().toString());
		mVHolder.mTvVaccineDate.setText(mVaccine.getmDate().toString());
		mVHolder.mTvVaccineStatus.setText(mVaccine.getmNote().toString());

		return rowView;
	}

}
