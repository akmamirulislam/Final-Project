package com.ftfl.icare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icare.R;
import com.ftfl.icare.model.MedicalHistory;

public class MedicalHistoryCustomAdapter extends ArrayAdapter<MedicalHistory> {
	Activity mContext;
	MedicalHistory mMedical;

	// Profile ArrayList
	ArrayList<MedicalHistory> mMedicalArrayList;

	public MedicalHistoryCustomAdapter(Activity context,
			ArrayList<MedicalHistory> eMedicalArrayList) {
		super(context, R.layout.activity_medical_history_single_row,
				eMedicalArrayList);
		this.mContext = context;
		this.mMedicalArrayList = eMedicalArrayList;
	}

	// Viewholder Class to contain inflated xml file elements
	static class ViewHolder {
		public TextView mTvMedicalPurpose;
		public TextView mTvMedicalDoctor;
		public TextView mTvMedicalDate;
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mMedical = mMedicalArrayList.get(position);

		// ViewHolder Object
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {

			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();
			rowView = inflater
					.inflate(R.layout.activity_medical_history_single_row,
							parent, false);

			// ViewHolder Object
			mVHolder = new ViewHolder();

			// findViewById
			mVHolder.mTvMedicalPurpose = (TextView) rowView
					.findViewById(R.id.tvSingleMedicalPurpose);
			mVHolder.mTvMedicalDoctor = (TextView) rowView
					.findViewById(R.id.tvSingleMedicalDoctor);
			mVHolder.mTvMedicalDate = (TextView) rowView
					.findViewById(R.id.tvSingleMedicalDate);

			// Set ViewHolder
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();
		mVHolder.mTvMedicalPurpose.setText(mMedical.getmPurpose().toString());
		mVHolder.mTvMedicalDoctor.setText(mMedical.getmDoctorName().toString());
		mVHolder.mTvMedicalDate.setText(mMedical.getmAppointmentDate()
				.toString());

		return rowView;
	}

}
