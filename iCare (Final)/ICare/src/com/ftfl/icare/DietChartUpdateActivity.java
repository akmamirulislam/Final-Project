package com.ftfl.icare;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ftfl.icare.database.DietChartDataSource;
import com.ftfl.icare.model.DietChart;
import com.ftfl.icare.utility.Validation;

public class DietChartUpdateActivity extends Activity {

	// ID
	int mId = 0;

	// Declaration Of View
	Spinner mSpinnerDiet;
	EditText mEtDietTime;
	EditText mEtDietFeast;
	EditText mEtDietNote;
	CheckBox mCbDietAlarm;
	CheckBox mCbDietWeeklyAlarm;

	// String Values
	String mDietType;
	String mDietTime;
	String mDietFeast;
	String mDietNote;

	// Time Values
	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	int mHour = 0;
	int mMinute = 0;
	final Calendar mCalendar = Calendar.getInstance();

	// ArrayAdaper for Spinner
	ArrayAdapter<String> mDietAdapter;

	// Spinner DataSource for Spinner
	private String[] mDietChartType = { "Select Diet Type", "Breakfast",
			"Snack", "Launch", "Dinner" };

	// Model Class Object
	DietChart mDietChart;

	// DataSource Object
	DietChartDataSource mDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diet_chart_update);

		// get the Intent that started this Activity
		Intent mIntent = getIntent();

		// get the Bundle that stores the data of this Activity
		Bundle mBundle = mIntent.getExtras();
		mId = mBundle.getInt("id");

		// FindViewById
		findView();

		// Getting All place detail
		mDataSource = new DietChartDataSource(this);
		mDietChart = mDataSource.getDetail(mId);

		// SetText
		mEtDietTime.setText(mDietChart.getmDietTime().toString());
		mEtDietFeast.setText(mDietChart.getmDietFeast().toString());
		mEtDietNote.setText(mDietChart.getmDietNote().toString());

		// Setting Spinner Adapter
		mDietAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_dropdown_item, mDietChartType);
		mSpinnerDiet.setAdapter(mDietAdapter);
	}

	// Create Button onClick Operation
	public void updateDiet(View view) {
		// getText
		getText();

		if (mDietType.length() == 0 || mDietTime.length() == 0
				|| mDietFeast.length() == 0 || mDietNote.length() == 0) {
			Toast.makeText(getApplicationContext(),
					"Please complete the All !", Toast.LENGTH_SHORT).show();
		} else {
			// passing data to the model class
			mDietChart = new DietChart(mDietType, mDietTime, mDietFeast,
					mDietNote);

			// Inserting data into the datasource
			mDataSource = new DietChartDataSource(this);
			long inserted = mDataSource.updateProfile(mId, mDietChart);
			if (inserted >= 0) {
				Toast.makeText(getApplicationContext(), "Diet Updated",
						Toast.LENGTH_SHORT).show();
				Intent mIntent = new Intent(DietChartUpdateActivity.this,
						DietChartListActivity.class);
				startActivity(mIntent);

				// finishing the current activity
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "Insertion failed!",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	// getText
	public void findView() {
		mSpinnerDiet = (Spinner) findViewById(R.id.spinnerDietTypeUpdate);
		mEtDietTime = (EditText) findViewById(R.id.etDietTimeUpdate);
		mEtDietFeast = (EditText) findViewById(R.id.etDietFeastNameUpdate);
		mEtDietNote = (EditText) findViewById(R.id.etDietNoteUpdate);
		mCbDietAlarm = (CheckBox) findViewById(R.id.cbDietAlarmUpdate);
		mCbDietWeeklyAlarm = (CheckBox) findViewById(R.id.cbDietWeeklyAlarmUpdate);

		mEtDietTime.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mEtDietTime);
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
		mDietType = mSpinnerDiet.getSelectedItem().toString();
		mDietTime = mEtDietTime.getText().toString();
		mDietFeast = mEtDietFeast.getText().toString();
		mDietNote = mEtDietNote.getText().toString();
	}

}
