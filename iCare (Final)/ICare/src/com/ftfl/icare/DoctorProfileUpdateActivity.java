package com.ftfl.icare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.ftfl.icare.database.DoctorProfileDataSource;
import com.ftfl.icare.model.DoctorProfile;

public class DoctorProfileUpdateActivity extends Activity {

	// ID
	int mId = 0;

	// View Declaration
	EditText mEtDoctorName;
	EditText mEtDoctorDesignation;
	EditText mEtDoctorPhone;
	EditText mEtDoctorEmail;

	// String Values
	String mDoctorName;
	String mDoctorDesignation;
	String mDoctorPhone;
	String mDoctorEmail;

	// ArrayAdaper for Spinner
	ArrayAdapter<String> mDietAdapter;

	// Model Class Object
	DoctorProfile mDoctor;

	// DataSource Object
	DoctorProfileDataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_update);

		// get the Intent that started this Activity
		Intent mIntent = getIntent();

		// get the Bundle that stores the data of this Activity
		Bundle mBundle = mIntent.getExtras();
		mId = mBundle.getInt("id");

		// FindView
		findView();

		// Getting All place detail
		mDataSource = new DoctorProfileDataSource(this);
		mDoctor = mDataSource.getDoctorDetail(mId);

		// SetText
		mEtDoctorName.setText(mDoctor.getmName().toString());
		mEtDoctorDesignation.setText(mDoctor.getmDesignation().toString());
		mEtDoctorPhone.setText(mDoctor.getmPhone().toString());
		mEtDoctorEmail.setText(mDoctor.getmEmail().toString());

	}

	// Create Doctor Button Operation
	public void updateDoctor(View view) {
		// getText
		getText();

		if (mDoctorName.length() == 0 || mDoctorDesignation.length() == 0
				|| mDoctorPhone.length() == 0 || mDoctorEmail.length() == 0) {
			Toast.makeText(getApplicationContext(),
					"Please complete the All !", Toast.LENGTH_SHORT).show();
		} else {
			// passing data to the model class
			mDoctor = new DoctorProfile(mDoctorName, mDoctorDesignation,
					mDoctorPhone, mDoctorEmail);

			// Inserting data into the datasource
			mDataSource = new DoctorProfileDataSource(this);
			long inserted = mDataSource.updateProfile(mId, mDoctor);
			if (inserted >= 0) {
				Toast.makeText(getApplicationContext(), "Updated",
						Toast.LENGTH_SHORT).show();

				// Intent to Doctor List
				Intent mIntent = new Intent(DoctorProfileUpdateActivity.this,
						DoctorProfileListActivity.class);
				startActivity(mIntent);

				// finishing the current activity
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "Insertion failed!",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	// FindViewById
	public void findView() {
		mEtDoctorName = (EditText) findViewById(R.id.etDoctorNameUpdate);
		mEtDoctorDesignation = (EditText) findViewById(R.id.etDoctorDesignationUpdate);
		mEtDoctorPhone = (EditText) findViewById(R.id.etDoctorPhoneUpdate);
		mEtDoctorEmail = (EditText) findViewById(R.id.etDoctorEmailUpdate);
	}

	// GetText
	public void getText() {
		mDoctorName = mEtDoctorName.getText().toString();
		mDoctorDesignation = mEtDoctorDesignation.getText().toString();
		mDoctorPhone = mEtDoctorPhone.getText().toString();
		mDoctorEmail = mEtDoctorEmail.getText().toString();
	}
}