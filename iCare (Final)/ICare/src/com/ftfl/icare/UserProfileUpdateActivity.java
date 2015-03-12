package com.ftfl.icare;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ftfl.icare.database.UserProfileDataSource;
import com.ftfl.icare.model.UserProfile;
import com.ftfl.icare.utility.SetDateOnClickDialog;
import com.ftfl.icare.utility.Validation;

public class UserProfileUpdateActivity extends Activity {

	// ID
	int mId = 1;

	// View Declaration
	EditText mEtUserName;
	EditText mEtUserWeight;
	EditText mEtUserHeight;
	EditText mEtUserBirthDate;
	Spinner spinnerUserGender;
	EditText mEtUserPhone;

	// String Values
	String mName = null;
	String mWeight = null;
	String mHeight = null;
	String mBirthDate = null;
	String mGender = null;
	String mPhone = null;
	String mDay;

	// Calendar Instance
	final Calendar mCalendar = Calendar.getInstance();

	// UserProfileDataSource Object
	UserProfileDataSource mDataSource = null;

	// UserProfileDataSource Object
	UserProfile mProfile = null;

	// ArrayAdaper for Spinner
	ArrayAdapter<String> mGenderAdapter;

	// Spinner DataSource for Spinner
	private String[] mGenderType = { "Select Your Gender", "Male", "Female" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_update);

		// findViewById
		mEtUserName = (EditText) findViewById(R.id.etUserNameUpdate);
		mEtUserWeight = (EditText) findViewById(R.id.etUserWeightUpdate);
		mEtUserHeight = (EditText) findViewById(R.id.etUserHeightUpdate);
		spinnerUserGender = (Spinner) findViewById(R.id.spinnerUserGenderUpdate);
		mEtUserPhone = (EditText) findViewById(R.id.etUserEmergencyPhoneUpdate);

		mEtUserBirthDate = (EditText) findViewById(R.id.etUserBirthdateUpdate);
		SetDateOnClickDialog datePickerObj = new SetDateOnClickDialog(
				mEtUserBirthDate, this);
		int dayOfWeek = datePickerObj.dayOfWeek;
		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		mEtUserBirthDate.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mEtUserBirthDate);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		// Setting Spinner Adapter
		mGenderAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_dropdown_item, mGenderType);
		spinnerUserGender.setAdapter(mGenderAdapter);

		// SetText
		// UserProfile DataSource Initialization
		mDataSource = new UserProfileDataSource(this);
		mProfile = mDataSource.getDetail(mId);

		// Dumping Data into the EditText
		mEtUserName.setText(mProfile.getmName().toString());
		mEtUserWeight.setText(mProfile.getmWeight().toString());
		mEtUserHeight.setText(mProfile.getmHeight().toString());
		mEtUserBirthDate.setText(mProfile.getmBirthDate().toString());
		mEtUserPhone.setText(mProfile.getmEmergencyPhone().toString());
	}

	// Create Button Operation
	public void updateUserProfile(View view) {
		// GetText
		mName = mEtUserName.getText().toString();
		mWeight = mEtUserWeight.getText().toString();
		mHeight = mEtUserHeight.getText().toString();
		mBirthDate = mEtUserBirthDate.getText().toString();
		mGender = spinnerUserGender.getSelectedItem().toString();
		mPhone = mEtUserPhone.getText().toString();

		if (mName.length() == 0 || mWeight.length() == 0
				|| mHeight.length() == 0 || mBirthDate.length() == 0
				|| mGender.length() == 0 && mPhone.length() == 0) {
			Toast.makeText(getApplicationContext(),
					"Please complete the All !", Toast.LENGTH_SHORT).show();
		} else {
			// Passing to the model Class
			mProfile = new UserProfile(mName, mWeight, mHeight, mBirthDate,
					mGender, mPhone);

			// Insert into the Database
			mDataSource = new UserProfileDataSource(this);
			long inserted = mDataSource.updateProfile(mId, mProfile);
			if (inserted >= 0) {
				Toast.makeText(getApplicationContext(), "Profile Updated",
						Toast.LENGTH_SHORT).show();

				// Intent to MainActivity
				Intent mMainIntent = new Intent(UserProfileUpdateActivity.this,
						UserProfileViewActivity.class);
				startActivity(mMainIntent);

				// finishing the current activity
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "Insertion failed!",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

}
