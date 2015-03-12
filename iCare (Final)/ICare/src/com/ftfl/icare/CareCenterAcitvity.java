package com.ftfl.icare;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.icare.adapter.CareCenterCustomAdapter;

public class CareCenterAcitvity extends Activity {

	// Variable Declaration
	ListView mLvCareCenter = null;
	TextView mTvPhoneNumber;
	ImageView mIvCall;

	// String Values
	String mLatitude = "";
	String mLongitude = "";

	// Double Values
	Double mLat;
	Double mLng;

	// Care Center Custom Adapter
	CareCenterCustomAdapter mCareCenterAdapter;

	// String ArrayList
	ArrayList<String> nameList = new ArrayList<String>();
	ArrayList<String> addressList = new ArrayList<String>();
	ArrayList<String> phoneList = new ArrayList<String>();
	ArrayList<String> faxList = new ArrayList<String>();
	ArrayList<String> emailList = new ArrayList<String>();
	ArrayList<String> webList = new ArrayList<String>();
	ArrayList<String> LatiList = new ArrayList<String>();
	ArrayList<String> lngList = new ArrayList<String>();

	// care center informations
	String[] mCenterName = { "Dhaka Medical College Hospital",
			"Shamorita Hospital", "Square Hospital", "Apollo Hospital",
			"Japan Bangladesh Friendship Hospital" };

	String[] mCenterAddress = { "Bokshi Bazaar, 100 Ramna, Dhaka-1000.",
			"89/1, Panthopath, Dhaka-1215.",
			"18/F, West Panthopath, Dhaka-1205.",
			"Plot- 81, Block-E, Basundhara Residential Area, Dhaka-1229.",
			"55, Sat Masjid Road (Jhigatola Bus Stand), Dhaka-1209." };

	String[] mCenterPhone = { "8626812-19", "9131901", "8129338", "8401661",
			"9672277" };

	String[] mCenterFax = { "8615919", "9129971", "9118921", "8401679",
			"9675674" };

	String[] mCenterEmail = { "info@dmc.edu.bd", "samorita@bangla.net ",
			"info@square.net ", "info@apollodhaka.com ", "info@jbfh.org.bd " };

	String[] mCenterWeb = { "www.dmc.edu.bd", "www.samoritahospital.net",
			"www.squarehospital.com", "www.apollodhaka.com", "www.jbfh.org.bd" };

	String[] mCenterLatitude = { "23.725071", "23.752561", "23.752997",
			"23.810117", "23.739567" };

	String[] mCenterLongitude = { "90.397796", "90.385105", "90.381461",
			"90.430401", "90.375104" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_care_center);

		// FindViewById
		mLvCareCenter = (ListView) findViewById(R.id.lvCareCenter);
		mIvCall = (ImageView) findViewById(R.id.ivCareCenterCallIcon);

		// Adding String Array to the Array List
		nameList.addAll(Arrays.asList(mCenterName));
		addressList.addAll(Arrays.asList(mCenterAddress));
		phoneList.addAll(Arrays.asList(mCenterPhone));
		faxList.addAll(Arrays.asList(mCenterFax));
		emailList.addAll(Arrays.asList(mCenterEmail));
		webList.addAll(Arrays.asList(mCenterWeb));
		LatiList.addAll(Arrays.asList(mCenterLatitude));
		lngList.addAll(Arrays.asList(mCenterLongitude));

		mCareCenterAdapter = new CareCenterCustomAdapter(this, nameList,
				addressList, phoneList, faxList, emailList, webList, LatiList,
				lngList);

		mLvCareCenter.setAdapter(mCareCenterAdapter);

	}

	public void callHospital(View view) {

		mTvPhoneNumber = (TextView) findViewById(R.id.tvCareCenterTelePhone);
		String mNumber = mTvPhoneNumber.getText().toString();
		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ mNumber));
		startActivity(callIntent);
	}
}
