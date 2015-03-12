package com.ftfl.icare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icare.model.UserProfile;

public class UserProfileDataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public UserProfileDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new
	public long addProfile(UserProfile eProfile) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_USER_NAME, eProfile.getmName());
		values.put(DBHelper.KEY_USER_WEIGHT, eProfile.getmWeight());
		values.put(DBHelper.KEY_USER_HEIGHT, eProfile.getmHeight());
		values.put(DBHelper.KEY_USER_BIRTH_DATE, eProfile.getmBirthDate());
		values.put(DBHelper.KEY_USER_GENDER, eProfile.getmGender());
		values.put(DBHelper.KEY_USER_PHONE, eProfile.getmEmergencyPhone());

		long inserted = db.insert(DBHelper.USER_PROFILE_TABLE_NAME, null,
				values);
		close();
		return inserted;
	}

	// // Delete Data Form Database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.USER_PROFILE_TABLE_NAME, DBHelper.KEY_USER_ID
					+ "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// // update Batabase By Id
	public long updateProfile(Integer id, UserProfile eProfile) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_USER_NAME, eProfile.getmName());
		values.put(DBHelper.KEY_USER_WEIGHT, eProfile.getmWeight());
		values.put(DBHelper.KEY_USER_HEIGHT, eProfile.getmHeight());
		values.put(DBHelper.KEY_USER_BIRTH_DATE, eProfile.getmBirthDate());
		values.put(DBHelper.KEY_USER_GENDER, eProfile.getmGender());
		values.put(DBHelper.KEY_USER_PHONE, eProfile.getmEmergencyPhone());

		long updated = 0;
		try {
			updated = db.update(DBHelper.USER_PROFILE_TABLE_NAME, values,
					DBHelper.KEY_USER_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// // Getting All Profile list
	public ArrayList<UserProfile> getProfileList() {
		ArrayList<UserProfile> profileList = new ArrayList<UserProfile>();
		open();
		Cursor cursor = db.query(DBHelper.USER_PROFILE_TABLE_NAME, null, null,
				null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_USER_ID));
				String mName = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_USER_NAME));
				String mWeight = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_USER_WEIGHT));
				String mHeight = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_USER_HEIGHT));
				String mBirthDate = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_USER_BIRTH_DATE));
				String mGender = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_USER_GENDER));
				String mPhone = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_USER_PHONE));

				UserProfile mProfile = new UserProfile(id, mName, mWeight,
						mHeight, mBirthDate, mGender, mPhone);
				profileList.add(mProfile);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return profile list
		return profileList;
	}

	// Getting All Profile list
	public UserProfile getDetail(int id) {
		UserProfile mProfileDetail;
		open();

		String selectQuery = "SELECT  * FROM "
				+ DBHelper.USER_PROFILE_TABLE_NAME + " WHERE "
				+ DBHelper.KEY_USER_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String mName = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_USER_NAME));
		String mWeight = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_USER_WEIGHT));
		String mHeight = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_USER_HEIGHT));
		String mBirthDate = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_USER_BIRTH_DATE));
		String mGender = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_USER_GENDER));
		String mPhone = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_USER_PHONE));

		mProfileDetail = new UserProfile(id, mName, mWeight, mHeight,
				mBirthDate, mGender, mPhone);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return place detail
		return mProfileDetail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor mCursor = db
				.query(DBHelper.USER_PROFILE_TABLE_NAME, new String[] {
						DBHelper.KEY_USER_ID, DBHelper.KEY_USER_NAME,
						DBHelper.KEY_USER_WEIGHT, DBHelper.KEY_USER_HEIGHT,
						DBHelper.KEY_USER_BIRTH_DATE, DBHelper.KEY_USER_GENDER,
						DBHelper.KEY_USER_PHONE }, null, null, null, null, null);
		if (mCursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
