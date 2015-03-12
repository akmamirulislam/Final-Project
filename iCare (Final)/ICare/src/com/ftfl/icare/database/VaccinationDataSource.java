package com.ftfl.icare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icare.model.MedicalHistory;
import com.ftfl.icare.model.Vaccination;

public class VaccinationDataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public VaccinationDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new
	public long addVaccine(Vaccination eVaccine) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_VACCINE_NAME, eVaccine.getmName());
		values.put(DBHelper.KEY_VACCINE_TIME, eVaccine.getmTime());
		values.put(DBHelper.KEY_VACCINE_DATE, eVaccine.getmDate());
		values.put(DBHelper.KEY_VACCINE_NOTE, eVaccine.getmNote());

		long inserted = db
				.insert(DBHelper.VACCINATION_TABLE_NAME, null, values);
		close();
		return inserted;
	}

	// // Delete Data Form Database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.VACCINATION_TABLE_NAME, DBHelper.KEY_VACCINE_ID
					+ "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// // update database By Id
	public long updateVaccine(Integer id, Vaccination eVaccine) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_VACCINE_NAME, eVaccine.getmName());
		values.put(DBHelper.KEY_VACCINE_TIME, eVaccine.getmTime());
		values.put(DBHelper.KEY_VACCINE_DATE, eVaccine.getmDate());
		values.put(DBHelper.KEY_VACCINE_NOTE, eVaccine.getmNote());
		long updated = 0;
		try {
			updated = db.update(DBHelper.VACCINATION_TABLE_NAME, values,
					DBHelper.KEY_VACCINE_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// // Getting All Vaccine list
	public ArrayList<Vaccination> getVaccinationList() {
		ArrayList<Vaccination> mVaccimeList = new ArrayList<Vaccination>();
		open();
		Cursor cursor = db.query(DBHelper.VACCINATION_TABLE_NAME, null, null,
				null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int mId = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_VACCINE_ID));
				String mName = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINE_NAME));
				String mTime = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINE_TIME));
				String mDate = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINE_DATE));
				String mNote = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINE_NOTE));

				Vaccination mVaccine = new Vaccination(mId,
						mName, mTime, mDate, mNote);
				mVaccimeList.add(mVaccine);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return profile list
		return mVaccimeList;
	}

	// Getting All Profile list
	public Vaccination getVaccineDetail(int id) {
		Vaccination mVaccineDetail;
		open();

		String selectQuery = "SELECT  * FROM "
				+ DBHelper.VACCINATION_TABLE_NAME + " WHERE "
				+ DBHelper.KEY_VACCINE_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String mName = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINE_NAME));
		String mTime = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINE_TIME));
		String mDate = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINE_DATE));
		String mNote = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINE_NOTE));

		mVaccineDetail = new Vaccination(id, mName, mTime, mDate, mNote);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return Vaccine detail
		return mVaccineDetail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.VACCINATION_TABLE_NAME, new String[] {
				DBHelper.KEY_VACCINE_ID, DBHelper.KEY_VACCINE_NAME,
				DBHelper.KEY_VACCINE_TIME, DBHelper.KEY_VACCINE_DATE,
				DBHelper.KEY_VACCINE_NOTE }, null, null, null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
