package com.ftfl.icare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ftfl.icare.database.MedicalHistoryDataSource;
import com.ftfl.icare.model.MedicalHistory;

public class MedicalHistoryViewActivity extends Activity {

	// ID
	int mId = 0;

	// Photo Object
	String mPhotoPath = "";
	Bitmap bitmap = null;

	// variable declaration
	TextView mTvMedicalPurpose = null;
	TextView mTvMedicaDoctor = null;
	TextView mTvMedicaAppointMentDate = null;
	TouchImageView mIvPrescription = null;

	// MedicalHistory Object
	MedicalHistory mMedical = null;

	// DataSource Object
	MedicalHistoryDataSource mDataSource = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_history_view_page);

		// get the Intent that started this Activity
		Intent mIntent = getIntent();

		// get the Bundle that stores the data of this Activity
		Bundle mBundle = mIntent.getExtras();
		mId = mBundle.getInt("id");
		// Initialization
		initialization();

		// Getting All place detail
		mDataSource = new MedicalHistoryDataSource(this);
		mMedical = mDataSource.getMedicalDetail(mId);

		// // set value in text view
		mTvMedicalPurpose.setText(mMedical.getmPurpose());
		mTvMedicaDoctor.setText(mMedical.getmDoctorName());
		mTvMedicaAppointMentDate.setText(mMedical.getmAppointmentDate());

		// preview Captured Image from database
		mPhotoPath = mMedical.getmPrescription();
		if (mPhotoPath.length() > 0) {
			previewCapturedImage();
			mIvPrescription.setImageBitmap(bitmap);
		} else {
			mIvPrescription.setImageResource(R.drawable.ic_launcher);
		}

	}

	// Display image from a path to ImageView
	private void previewCapturedImage() {
		try {
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();

			// downsizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 10;
			bitmap = BitmapFactory.decodeFile(mPhotoPath, options);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	// Initialization
	public void initialization() {
		mTvMedicalPurpose = (TextView) findViewById(R.id.tvMedicalDoctorNameView);
		mTvMedicaDoctor = (TextView) findViewById(R.id.tvMedicalDoctorNameView);
		mTvMedicaAppointMentDate = (TextView) findViewById(R.id.tvMedicalAppointmentDateView);
		mIvPrescription = (TouchImageView) findViewById(R.id.tvMedicalPrescriptionView);
	}

	// Update Button Operation
	private void update() {
		Bundle mBundle = new Bundle();
		mBundle.putInt("id", mId);
		Intent mIntent = new Intent(MedicalHistoryViewActivity.this,
				DoctorProfileUpdateActivity.class);
		mIntent.putExtras(mBundle);
		startActivity(mIntent);
	}

	// Delete Button Operation
	private void delete() {
		mDataSource = new MedicalHistoryDataSource(this);
		mDataSource.deleteData(mId);

		// Intent to Doctor Profile List
		Intent mIntent = new Intent(MedicalHistoryViewActivity.this,
				DoctorProfileListActivity.class);
		startActivity(mIntent);

	}

	// view screen menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.doctor_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.update) {
			update();
			return true;
		}

		if (id == R.id.delete) {
			delete();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
