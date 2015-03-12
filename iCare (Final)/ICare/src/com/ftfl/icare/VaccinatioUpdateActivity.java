package com.ftfl.icare;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ftfl.icare.database.VaccinationDataSource;
import com.ftfl.icare.model.Vaccination;
import com.ftfl.icare.utility.SetDateOnClickDialog;
import com.ftfl.icare.utility.Validation;
import com.ftfl.icare.utility.setTimeOnclickDialog;

public class VaccinatioUpdateActivity extends Activity {

	// ID
	int mId = 0;

	// Declaration Of View
	EditText mEtVaccineName;
	EditText mEtVaccineTime;
	EditText mEtVaccineDate;
	EditText mEtVaccineNote;
	CheckBox mCbRemainder;

	// String Values
	String mVaccineName;
	String mVaccineTime;
	String mVaccineDate;
	String mVaccineNote;
	String mVaccineRemainder;
	String mDay;

	// Time Values
	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	int mHour = 0;
	int mMinute = 0;
	final Calendar mCalendar = Calendar.getInstance();

	// Model Class Object
	Vaccination mVaccine;

	// DataSource Object
	VaccinationDataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vaccine_update);

		// get the Intent that started this Activity
		Intent mIntent = getIntent();

		// get the Bundle that stores the data of this Activity
		Bundle mBundle = mIntent.getExtras();
		mId = mBundle.getInt("id");

		// FindViewById
		findView();

		// Getting All place detail
		mDataSource = new VaccinationDataSource(this);
		mVaccine = mDataSource.getVaccineDetail(mId);

		// SetText
		mEtVaccineName.setText(mVaccine.getmName().toString());
		mEtVaccineTime.setText(mVaccine.getmTime().toString());
		mEtVaccineDate.setText(mVaccine.getmDate().toString());
		mEtVaccineNote.setText(mVaccine.getmNote().toString());
	}

	// Create Button onClick Operation
	public void updateVaccination(View view) {
		// getText
		getText();

		if (mVaccineName.length() == 0 || mVaccineTime.length() == 0
				|| mVaccineDate.length() == 0 || mVaccineNote.length() == 0) {
			Toast.makeText(getApplicationContext(),
					"Please complete the All !", Toast.LENGTH_SHORT).show();
		} else {
			// passing data to the model class
			mVaccine = new Vaccination(mVaccineName, mVaccineTime,
					mVaccineDate, mVaccineNote);

			// Inserting data into the datasource
			mDataSource = new VaccinationDataSource(this);
			long inserted = mDataSource.updateVaccine(mId, mVaccine);
			if (inserted >= 0) {
				Toast.makeText(getApplicationContext(), "Vaccine Updated",
						Toast.LENGTH_SHORT).show();
				Intent mIntent = new Intent(VaccinatioUpdateActivity.this,
						VaccinationListActivity.class);
				startActivity(mIntent);

				// finishing the current activity
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "Insertion failed!",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	// Initialization or findViewById
	public void findView() {
		mEtVaccineName = (EditText) findViewById(R.id.etVaccineNameUpdate);
		mEtVaccineTime = (EditText) findViewById(R.id.etVaccineTimeUpdate);
		mEtVaccineDate = (EditText) findViewById(R.id.etVaccineDateUpdate);
		mEtVaccineNote = (EditText) findViewById(R.id.etVaccineNoteUpdate);
		mCbRemainder = (CheckBox) findViewById(R.id.cbVaccineRemainderUpdate);

		@SuppressWarnings("unused")
		setTimeOnclickDialog timePickerObj = new setTimeOnclickDialog(
				mEtVaccineTime, this);

		mEtVaccineTime.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mEtVaccineTime);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		SetDateOnClickDialog datePickerObj = new SetDateOnClickDialog(
				mEtVaccineDate, this);
		int dayOfWeek = datePickerObj.dayOfWeek;
		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		mEtVaccineDate.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mEtVaccineDate);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

	}

	// getText
	public void getText() {
		mVaccineName = mEtVaccineName.getText().toString();
		mVaccineTime = mEtVaccineTime.getText().toString();
		mVaccineDate = mEtVaccineDate.getText().toString();
		mVaccineNote = mEtVaccineNote.getText().toString();
	}

}
