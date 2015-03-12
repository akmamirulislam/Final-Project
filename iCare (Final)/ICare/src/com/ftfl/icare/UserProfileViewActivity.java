package com.ftfl.icare;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.icare.database.UserProfileDataSource;
import com.ftfl.icare.model.UserProfile;

public class UserProfileViewActivity extends Activity {

	// ID
	int mId = 1;

	// View Declaration
	TextView mTvUserName;
	TextView mTvUserWeight;
	TextView mTvUserHeight;
	TextView mTvUserBirthDate;
	TextView mTvUserGender;
	TextView mTvUserPhone;

	// UserProfileDataSource Object
	UserProfileDataSource mDataSource = null;

	// UserProfileDataSource Object
	UserProfile mProfile = null;

	// Sensor Data
	private SensorManager mSensorManager;
	private float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_view);

		// findViewById
		mTvUserName = (TextView) findViewById(R.id.tvUserNameView);
		mTvUserWeight = (TextView) findViewById(R.id.tvUserWeightView);
		mTvUserHeight = (TextView) findViewById(R.id.tvUserHeightView);
		mTvUserBirthDate = (TextView) findViewById(R.id.tvUserBirthdateView);
		mTvUserGender = (TextView) findViewById(R.id.tvUserGenderView);
		mTvUserPhone = (TextView) findViewById(R.id.tvUserEmergencyNumberView);

		// SetText
		// UserProfile DataSource Initialization
		mDataSource = new UserProfileDataSource(this);
		mProfile = mDataSource.getDetail(mId);

		int cm = Integer.parseInt(mProfile.getmHeight());

		// Calculating and Convertion to decimal format
		DecimalFormat df = new DecimalFormat("#.##");
		double value1 = cm / 2.54;
		double value2 = value1 / 12.0;
		String feet = df.format(value2);

		// Dumping Data into the EditText
		mTvUserName.setText(mProfile.getmName().toString());
		mTvUserWeight.setText(mProfile.getmWeight().toString() + " kg");
		mTvUserHeight.setText(feet + " " + "feet");
		mTvUserBirthDate.setText(mProfile.getmBirthDate().toString());
		mTvUserGender.setText(mProfile.getmGender().toString());
		mTvUserPhone.setText(mProfile.getmEmergencyPhone().toString());

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
		mAccel = 0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;
	}

	// On Shake Operation
	private final SensorEventListener mSensorListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
			float delta = mAccelCurrent - mAccelLast;
			mAccel = mAccel * 0.9f + delta; // perform low-cut filter

			if (mAccel > 15) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Calling To Emergency Number", Toast.LENGTH_LONG);
				toast.show();

				// Getting Number from Database
				String number = (mProfile.getmEmergencyPhone().toString());
				Intent callIntent = new Intent(Intent.ACTION_CALL,
						Uri.parse("tel:" + number));
				startActivity(callIntent);
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onPause();
	}

	// Main menu Operations
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.update, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.home) {
			Intent viewIntent = new Intent(UserProfileViewActivity.this,
					MainActivity.class);
			startActivity(viewIntent);

			// Finishing Current Activity
			finish();
			return true;
		}
		if (id == R.id.update) {
			Intent viewIntent = new Intent(UserProfileViewActivity.this,
					UserProfileUpdateActivity.class);
			startActivity(viewIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
