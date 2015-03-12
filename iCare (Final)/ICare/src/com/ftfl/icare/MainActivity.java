package com.ftfl.icare;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ftfl.icare.database.UserProfileDataSource;
import com.ftfl.icare.model.UserProfile;

public class MainActivity extends Activity {

	// ID
	int mId = 1;

	// View Declaration
	TextView mTvUserName;
	TextView mTvUserWeight;
	TextView mTvUserHeight;

	// UserProfile DataSource Object
	UserProfileDataSource mDataSource;

	// User Profile Object
	UserProfile mUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// View Initializations
		mTvUserName = (TextView) findViewById(R.id.tvUserName);
		mTvUserWeight = (TextView) findViewById(R.id.tvUserWeight);
		mTvUserHeight = (TextView) findViewById(R.id.tvUserHeight);

		// UserProfile DataSource Initialization
		mDataSource = new UserProfileDataSource(this);
		mUser = mDataSource.getDetail(mId);

		// SetText
		mTvUserName.setText(mUser.getmName().toString());
		mTvUserWeight.setText(mUser.getmWeight().toString() + " " + "kg");

		int cm = Integer.parseInt(mUser.getmHeight());
		DecimalFormat df = new DecimalFormat("#.##");
		double value1 = cm / 2.54;
		double value2 = value1 / 12.0;
		String feet = df.format(value2);
		mTvUserHeight.setText(feet + " " + "feet");
	}

	public void dietImageEvent(View view) {
		Intent dietIntent = new Intent(MainActivity.this,
				DietChartListActivity.class);
		startActivity(dietIntent);
	}

	public void vaccineImageEvent(View view) {
		Intent dietIntent = new Intent(MainActivity.this,
				VaccinationListActivity.class);
		startActivity(dietIntent);
	}

	public void doctorImageEvent(View view) {
		Intent doctorIntent = new Intent(MainActivity.this,
				DoctorProfileListActivity.class);
		startActivity(doctorIntent);
	}

	public void medicalHistoryImageEvent(View view) {
		Intent medicalIntent = new Intent(MainActivity.this,
				MedicalHistoryListActivity.class);
		startActivity(medicalIntent);
	}

	public void careCenterImageEvent(View view) {
		Intent careIntent = new Intent(MainActivity.this,
				CareCenterAcitvity.class);
		startActivity(careIntent);
	}

	public void healthTipsImageEvent(View view) {
		Intent healthIntent = new Intent(MainActivity.this,
				HealthTipsActivity.class);
		startActivity(healthIntent);
	}

	// Main menu Operations
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.view) {
			Intent viewIntent = new Intent(MainActivity.this,
					UserProfileViewActivity.class);
			startActivity(viewIntent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
