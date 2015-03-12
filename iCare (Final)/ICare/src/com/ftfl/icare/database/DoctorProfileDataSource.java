package com.ftfl.icare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icare.model.DoctorProfile;

public class DoctorProfileDataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public DoctorProfileDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new
	public long addProfile(DoctorProfile eDoctor) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_DOCTOR_NAME, eDoctor.getmName());
		values.put(DBHelper.KEY_DOCTOR_DESIGNATION, eDoctor.getmDesignation());
		values.put(DBHelper.KEY_DOCTOR_PHONE, eDoctor.getmPhone());
		values.put(DBHelper.KEY_DOCTOR_EMAIL, eDoctor.getmEmail());

		long inserted = db.insert(DBHelper.DOCTOR_PROFILE_TABLE_NAME, null,
				values);
		close();
		return inserted;
	}

	// // Delete Data Form Database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.DOCTOR_PROFILE_TABLE_NAME,
					DBHelper.KEY_DOCTOR_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// // update database By Id
	public long updateProfile(Integer id, DoctorProfile eDoctor) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_DOCTOR_NAME, eDoctor.getmName());
		values.put(DBHelper.KEY_DOCTOR_DESIGNATION, eDoctor.getmDesignation());
		values.put(DBHelper.KEY_DOCTOR_PHONE, eDoctor.getmPhone());
		values.put(DBHelper.KEY_DOCTOR_EMAIL, eDoctor.getmEmail());

		long updated = 0;
		try {
			updated = db.update(DBHelper.DOCTOR_PROFILE_TABLE_NAME, values,
					DBHelper.KEY_DOCTOR_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// // Getting All Profile list
	public ArrayList<DoctorProfile> getProfileList() {
		ArrayList<DoctorProfile> profileList = new ArrayList<DoctorProfile>();
		open();
		Cursor cursor = db.query(DBHelper.DOCTOR_PROFILE_TABLE_NAME, null,
				null, null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int mId = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_DOCTOR_ID));
				String mName = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DOCTOR_NAME));
				String mDesignation = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DOCTOR_DESIGNATION));
				String mPhone = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DOCTOR_PHONE));
				String mEmail = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DOCTOR_EMAIL));

				DoctorProfile mDoctor = new DoctorProfile(mId, mName,
						mDesignation, mPhone, mEmail);
				profileList.add(mDoctor);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return profile list
		return profileList;
	}

	// // Getting All Profile list
	public ArrayList<String> getProfileNameList() {

		ArrayList<String> profileNameList = new ArrayList<String>();
		open();
		Cursor cursor = db.query(DBHelper.DOCTOR_PROFILE_TABLE_NAME, null,
				null, null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {

				String mName = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DOCTOR_NAME));

				profileNameList.add(mName);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return profile list
		return profileNameList;
	}

	// Getting All Profile list
	public DoctorProfile getDoctorDetail(int id) {
		DoctorProfile mDoctorDetail;
		open();

		String selectQuery = "SELECT  * FROM "
				+ DBHelper.DOCTOR_PROFILE_TABLE_NAME + " WHERE "
				+ DBHelper.KEY_DOCTOR_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String mName = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DOCTOR_NAME));
		String mDesignation = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DOCTOR_DESIGNATION));
		String mPhone = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DOCTOR_PHONE));
		String mEmail = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DOCTOR_EMAIL));

		mDoctorDetail = new DoctorProfile(id, mName, mDesignation, mPhone,
				mEmail);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return place detail
		return mDoctorDetail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.DOCTOR_PROFILE_TABLE_NAME,
				new String[] { DBHelper.KEY_DOCTOR_ID,
						DBHelper.KEY_DOCTOR_NAME,
						DBHelper.KEY_DOCTOR_DESIGNATION,
						DBHelper.KEY_DOCTOR_PHONE, DBHelper.KEY_DOCTOR_EMAIL },
				null, null, null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
