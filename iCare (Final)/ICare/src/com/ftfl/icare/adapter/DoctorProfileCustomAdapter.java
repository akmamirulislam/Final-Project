package com.ftfl.icare.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icare.R;
import com.ftfl.icare.model.DoctorProfile;

public class DoctorProfileCustomAdapter extends ArrayAdapter<DoctorProfile> {
	Activity mContext;
	DoctorProfile mProfile;

	// Profile ArrayList
	ArrayList<DoctorProfile> mDoctorProfileArrayList;

	public DoctorProfileCustomAdapter(Activity context,
			ArrayList<DoctorProfile> eDoctorProfileArrayList) {
		super(context, R.layout.activity_doctor_single_row,
				eDoctorProfileArrayList);
		this.mContext = context;
		this.mDoctorProfileArrayList = eDoctorProfileArrayList;
	}

	// holder Class to contain inflated xml file elements
	static class ViewHolder {
		public TextView mTvDoctorName;
		public TextView mTvDoctorSpecialArea;
		public TextView mTvDoctorPhone;
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mProfile = mDoctorProfileArrayList.get(position);

		// ViewHolder Object
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {

			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();
			rowView = inflater.inflate(R.layout.activity_doctor_single_row,
					parent, false);

			// ViewHolder Object
			mVHolder = new ViewHolder();

			// findViewById
			mVHolder.mTvDoctorName = (TextView) rowView
					.findViewById(R.id.tvSingleRowDoctorName);
			mVHolder.mTvDoctorSpecialArea = (TextView) rowView
					.findViewById(R.id.tvSingleRowDoctorSpecialistArea);
			mVHolder.mTvDoctorPhone = (TextView) rowView
					.findViewById(R.id.tvSingleRowDoctorPhoneNumber);

			// Set ViewHolder
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();
		mVHolder.mTvDoctorName.setText(mProfile.getmName().toString());
		mVHolder.mTvDoctorSpecialArea.setText(mProfile.getmDesignation()
				.toString());
		mVHolder.mTvDoctorPhone.setText(mProfile.getmPhone().toString());

		return rowView;
	}

}
