package com.ftfl.icare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icare.model.MedicalHistory;

;

public class MedicalHistoryDataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public MedicalHistoryDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new History
	public long addMedicalHistory(MedicalHistory eMedical) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_MEDICAL_PRESCRIPTION,
				eMedical.getmPrescription());
		values.put(DBHelper.KEY_MEDICAL_PURPOSE, eMedical.getmPurpose());
		values.put(DBHelper.KEY_MEDICAL_DOCTOR_NAME, eMedical.getmDoctorName());
		values.put(DBHelper.KEY_MEDICAL_APPOINTMENT_DATE,
				eMedical.getmAppointmentDate());

		long inserted = db.insert(DBHelper.MEDICAL_HISTORY_TABLE_NAME, null,
				values);
		close();
		return inserted;
	}

	// // Delete Data Form Database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.MEDICAL_HISTORY_TABLE_NAME,
					DBHelper.KEY_MEDICAL_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// // update database By Id
	public long updateProfile(Integer id, MedicalHistory eMedical) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_MEDICAL_PRESCRIPTION,
				eMedical.getmPrescription());
		values.put(DBHelper.KEY_MEDICAL_PURPOSE, eMedical.getmPurpose());
		values.put(DBHelper.KEY_MEDICAL_DOCTOR_NAME, eMedical.getmDoctorName());
		values.put(DBHelper.KEY_MEDICAL_APPOINTMENT_DATE,
				eMedical.getmAppointmentDate());
		long updated = 0;
		try {
			updated = db.update(DBHelper.MEDICAL_HISTORY_TABLE_NAME, values,
					DBHelper.KEY_MEDICAL_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// // Getting All Diet list
	public ArrayList<MedicalHistory> getMedicalHistoryList() {
		ArrayList<MedicalHistory> mMedicalHistoryList = new ArrayList<MedicalHistory>();
		open();
		Cursor cursor = db.query(DBHelper.MEDICAL_HISTORY_TABLE_NAME, null,
				null, null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int mId = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_MEDICAL_ID));
				String mPrescription = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_MEDICAL_PRESCRIPTION));
				String mPurpose = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_MEDICAL_PURPOSE));
				String mDoctorName = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_MEDICAL_DOCTOR_NAME));
				String mAppointmentDate = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_MEDICAL_APPOINTMENT_DATE));

				MedicalHistory mMedical = new MedicalHistory(mId,
						mPrescription, mPurpose, mDoctorName, mAppointmentDate);
				mMedicalHistoryList.add(mMedical);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return profile list
		return mMedicalHistoryList;
	}

	// Getting All Profile list
	public MedicalHistory getMedicalDetail(int id) {
		MedicalHistory mMedicalDetail;
		open();

		String selectQuery = "SELECT  * FROM "
				+ DBHelper.MEDICAL_HISTORY_TABLE_NAME + " WHERE "
				+ DBHelper.KEY_MEDICAL_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String mPrescription = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_MEDICAL_PRESCRIPTION));
		String mPurpose = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_MEDICAL_PURPOSE));
		String mDoctorName = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_MEDICAL_DOCTOR_NAME));
		String mAppointmentDate = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_MEDICAL_APPOINTMENT_DATE));

		mMedicalDetail = new MedicalHistory(id, mPrescription, mPurpose,
				mDoctorName, mAppointmentDate);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return place detail
		return mMedicalDetail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.MEDICAL_HISTORY_TABLE_NAME,
				new String[] { DBHelper.KEY_MEDICAL_ID,
						DBHelper.KEY_MEDICAL_PRESCRIPTION,
						DBHelper.KEY_MEDICAL_PURPOSE,
						DBHelper.KEY_MEDICAL_DOCTOR_NAME,
						DBHelper.KEY_MEDICAL_APPOINTMENT_DATE }, null, null,
				null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
