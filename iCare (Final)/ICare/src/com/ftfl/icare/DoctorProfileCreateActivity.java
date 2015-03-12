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

public class DoctorProfileCreateActivity extends Activity {

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
		setContentView(R.layout.activity_doctor_create);

		// FindView
		findView();

	}

	// Create Doctor Button Operation
	public void createDoctor(View view) {
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
			long inserted = mDataSource.addProfile(mDoctor);
			if (inserted >= 0) {
				Toast.makeText(getApplicationContext(), "Data Inserted",
						Toast.LENGTH_SHORT).show();

				// Intent to Doctor List
				Intent mIntent = new Intent(DoctorProfileCreateActivity.this,
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
		mEtDoctorName = (EditText) findViewById(R.id.etDoctorName);
		mEtDoctorDesignation = (EditText) findViewById(R.id.etDoctorDesignation);
		mEtDoctorPhone = (EditText) findViewById(R.id.etDoctorPhone);
		mEtDoctorEmail = (EditText) findViewById(R.id.etDoctorEmail);
	}

	// GetText
	public void getText() {
		mDoctorName = mEtDoctorName.getText().toString();
		mDoctorDesignation = mEtDoctorDesignation.getText().toString();
		mDoctorPhone = mEtDoctorPhone.getText().toString();
		mDoctorEmail = mEtDoctorEmail.getText().toString();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

}