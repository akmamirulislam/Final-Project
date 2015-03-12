package com.ftfl.icare;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ftfl.icare.database.VaccinationDataSource;
import com.ftfl.icare.model.Vaccination;
import com.ftfl.icare.utility.SetDateOnClickDialog;
import com.ftfl.icare.utility.Validation;
import com.ftfl.icare.utility.setTimeOnclickDialog;

public class VaccinationCreateActivity extends Activity {

	// Views Declaration
	EditText mEtName;
	EditText mEtTime;
	EditText mEtDate;
	EditText mEtNote;

	// String Values
	String mName;
	String mTime;
	String mDate;
	String mNote;
	String mDay;

	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	int mHour = 0;
	int mMinute = 0;
	final Calendar mCalendar = Calendar.getInstance();

	// Vaccination Object
	Vaccination mVaccination;

	// Data Source Object
	VaccinationDataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vaccine_create);

		// Process to get Current Time
		mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
		mMinute = mCalendar.get(Calendar.MINUTE);

		// View Initialization
		mEtName = (EditText) findViewById(R.id.etVaccineName);
		mEtTime = (EditText) findViewById(R.id.etVaccineTime);
		mEtDate = (EditText) findViewById(R.id.etVaccineDate);
		mEtNote = (EditText) findViewById(R.id.etVaccineNote);

		@SuppressWarnings("unused")
		setTimeOnclickDialog timePickerObj = new setTimeOnclickDialog(mEtTime,
				this);

		mEtTime.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mEtTime);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		SetDateOnClickDialog datePickerObj = new SetDateOnClickDialog(mEtDate,
				this);
		int dayOfWeek = datePickerObj.dayOfWeek;
		mDay = datePickerObj.getDayOfMonth(dayOfWeek).toString();

		mEtDate.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mEtDate);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

	}

	// Onclick Button Operation
	public void createvaccination(View view) {

		// GetText
		mName = mEtName.getText().toString();
		mTime = mEtTime.getText().toString();
		mDate = mEtDate.getText().toString();
		mNote = mEtNote.getText().toString();

		if (mName.length() == 0 || mTime.length() == 0 || mDate.length() == 0
				|| mNote.length() == 0) {
			Toast.makeText(getApplicationContext(),
					"Please complete the All !", Toast.LENGTH_SHORT).show();
		} else {
			// passing data to the model class
			mVaccination = new Vaccination(mName, mTime, mDate, mNote);

			// Inserting data into the datasource
			mDataSource = new VaccinationDataSource(this);
			long inserted = mDataSource.addVaccine(mVaccination);
			if (inserted >= 0) {
				Toast.makeText(getApplicationContext(), "Vaccination Added",
						Toast.LENGTH_SHORT).show();

				// Intent to Vaccination List Activty
				Intent mIntent = new Intent(VaccinationCreateActivity.this,
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

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

}