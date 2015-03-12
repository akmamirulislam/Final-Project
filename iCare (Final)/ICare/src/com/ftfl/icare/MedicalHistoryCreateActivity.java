package com.ftfl.icare;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ftfl.icare.database.DoctorProfileDataSource;
import com.ftfl.icare.database.MedicalHistoryDataSource;
import com.ftfl.icare.model.MedicalHistory;
import com.ftfl.icare.utility.SetDateOnClickDialog;
import com.ftfl.icare.utility.Validation;

public class MedicalHistoryCreateActivity extends Activity {

	// View Declaration
	EditText mEtMedicalPurpose;
	Spinner mSpinnerMedicalDoctor;
	EditText mEtMedicalAppontmentDate;

	// String Values
	String mPrescription;
	String mPurpose;
	String mDoctor;
	String mDate;

	// ArrayAdaper for Spinner
	ArrayAdapter<String> mMedicalDoctorAdapter;

	// Doctor NameArrayList
	ArrayList<String> mDoctorNameList;

	// Doctor Profile DataSource
	DoctorProfileDataSource mDoctorDataSource;

	// Medical History DataSource
	MedicalHistoryDataSource mMedicalDataSource;

	// Medical History Model object
	MedicalHistory mMedical;

	/* Camera */
	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView mIvPhotoView = null;
	String mCurrentPhotoPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medical_history_create);

		// View Declaration
		// mIvMedicalPrescription = (ImageView)
		// findViewById(R.id.ivMedicalPrescription);
		mEtMedicalPurpose = (EditText) findViewById(R.id.etMedicalPurpose);
		mSpinnerMedicalDoctor = (Spinner) findViewById(R.id.spinnerMedicalDoctor);
		mEtMedicalAppontmentDate = (EditText) findViewById(R.id.etMedicalAppointmentDate);
		mIvPhotoView = (ImageView) findViewById(R.id.ivMedicalPrescription);

		SetDateOnClickDialog datePickerObj = new SetDateOnClickDialog(
				mEtMedicalAppontmentDate, this);
		int dayOfWeek = datePickerObj.dayOfWeek;
		datePickerObj.getDayOfMonth(dayOfWeek).toString();

		mEtMedicalAppontmentDate.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				Validation.hasText(mEtMedicalAppontmentDate);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		// DoctorProfile DataSource Initialization
		mDoctorDataSource = new DoctorProfileDataSource(this);

		// Getting All Doctor Name from DoctorProfile DataSource
		mDoctorNameList = mDoctorDataSource.getProfileNameList();

		// Setting Spinner Adapter
		mMedicalDoctorAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_dropdown_item, mDoctorNameList);
		mSpinnerMedicalDoctor.setAdapter(mMedicalDoctorAdapter);
	}

	// Create Medical History Button Operation
	public void createMedicalHistory(View view) {

		mPurpose = mEtMedicalPurpose.getText().toString();
		mDoctor = mSpinnerMedicalDoctor.getSelectedItem().toString();
		mDate = mEtMedicalAppontmentDate.getText().toString();

		if (mPurpose.length() == 0 || mDoctor.length() == 0
				|| mDate.length() == 0) {
			Toast.makeText(getApplicationContext(),
					"Please complete the All !", Toast.LENGTH_SHORT).show();
		} else {
			// passing data to the model class
			mMedical = new MedicalHistory(mCurrentPhotoPath, mPurpose, mDoctor,
					mDate);

			// Inserting data into the datasource
			mMedicalDataSource = new MedicalHistoryDataSource(this);
			long inserted = mMedicalDataSource.addMedicalHistory(mMedical);
			if (inserted >= 0) {
				Toast.makeText(getApplicationContext(),
						"Medical History Added", Toast.LENGTH_SHORT).show();

				// Intent to Medical History List
				Intent mIntent = new Intent(MedicalHistoryCreateActivity.this,
						MedicalHistoryListActivity.class);
				startActivity(mIntent);

				// finishing the current activity
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "Insertion failed!",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	/* Capture Image */

	public void dispatchTakePictureIntent(View v) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException ex) {
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
						.show();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			setPic();
			galleryAddPic();
		}
	}

	/**
	 * If user wants to load photo into gallery
	 */
	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}

	@SuppressWarnings("deprecation")
	private void setPic() {
		// Get the dimensions of the View
		int targetW = mIvPhotoView.getWidth();
		int targetH = mIvPhotoView.getHeight();

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);// bmOptions
		mIvPhotoView.setImageBitmap(bitmap);
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents

		/**************************
		 * |---- You will get the image location from this variable which you
		 * will save into database
		 */
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}
}
