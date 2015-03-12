package com.ftfl.icare.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.icare.R;

public class CareCenterCustomAdapter extends ArrayAdapter<String> {

	// Variable Declaration
	Activity mContext = null;
	ArrayList<String> mNameList = null;
	ArrayList<String> mAddressList = null;
	ArrayList<String> mPhoneList = null;
	ArrayList<String> mFaxList = null;
	ArrayList<String> mEmailList = null;
	ArrayList<String> mWebList = null;
	ArrayList<String> mLatList = null;
	ArrayList<String> mLongList = null;

	public CareCenterCustomAdapter(Activity context,
			ArrayList<String> eNameList, ArrayList<String> eAddressList,
			ArrayList<String> ePhoneList, ArrayList<String> eFaxList,
			ArrayList<String> eEmailList, ArrayList<String> eWebList,
			ArrayList<String> eLatList, ArrayList<String> eLongList) {
		super(context, R.layout.activity_care_cener_single_row, eNameList);
		this.mContext = context;
		this.mNameList = eNameList;
		this.mAddressList = eAddressList;
		this.mPhoneList = ePhoneList;
		this.mFaxList = eFaxList;
		this.mEmailList = eEmailList;
		this.mWebList = eWebList;
		this.mLatList = eLatList;
		this.mLongList = eLongList;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = mContext.getLayoutInflater();
		View rowView = mInflater.inflate(
				R.layout.activity_care_cener_single_row, null, true);

		// definition - gives variable a reference
		TextView mTvName = (TextView) rowView
				.findViewById(R.id.tvCareCenterName);
		TextView mTvAddress = (TextView) rowView
				.findViewById(R.id.tvCareCenterAddress);
		TextView mTvPhone = (TextView) rowView
				.findViewById(R.id.tvCareCenterTelePhone);
		TextView mTvFax = (TextView) rowView.findViewById(R.id.tvCareCenterFax);
		TextView mTvEmail = (TextView) rowView
				.findViewById(R.id.tvCareCenterEmail);
		TextView mTvWeb = (TextView) rowView.findViewById(R.id.tvCareCenterWeb);
		TextView mTvLatitude = (TextView) rowView
				.findViewById(R.id.tvCareCenterLatitude);
		TextView mTLongitude = (TextView) rowView
				.findViewById(R.id.tvCareCenterLongitude);

		// set text in view
		mTvName.setText(mNameList.get(position));
		mTvAddress.setText(mAddressList.get(position));
		mTvPhone.setText(mPhoneList.get(position));
		mTvFax.setText(mFaxList.get(position));
		mTvEmail.setText(mEmailList.get(position));
		mTvWeb.setText(mWebList.get(position));
		mTvLatitude.setText(mLatList.get(position));
		mTLongitude.setText(mLongList.get(position));

		return rowView;
	}
}
