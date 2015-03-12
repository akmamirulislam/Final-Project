package com.ftfl.icare.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icare.model.DietChart;

;

public class DietChartDataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public DietChartDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new Diet
	public long addProfile(DietChart eDiet) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_DIET_TYPE, eDiet.getmDietType());
		values.put(DBHelper.KEY_DIET_TIME, eDiet.getmDietTime());
		values.put(DBHelper.KEY_DIET_FEAST, eDiet.getmDietFeast());
		values.put(DBHelper.KEY_DIET_NOTE, eDiet.getmDietNote());

		long inserted = db.insert(DBHelper.DIET_CHART_TABLE_NAME, null, values);
		close();
		return inserted;
	}

	// // Delete Data Form Database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.DIET_CHART_TABLE_NAME, DBHelper.KEY_DIET_ID
					+ "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// // update database By Id
	public long updateProfile(Integer id, DietChart eDiet) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_DIET_TYPE, eDiet.getmDietType());
		values.put(DBHelper.KEY_DIET_TIME, eDiet.getmDietTime());
		values.put(DBHelper.KEY_DIET_FEAST, eDiet.getmDietFeast());
		values.put(DBHelper.KEY_DIET_NOTE, eDiet.getmDietNote());
		long updated = 0;
		try {
			updated = db.update(DBHelper.DIET_CHART_TABLE_NAME, values,
					DBHelper.KEY_DIET_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// // Getting All Diet list
	public ArrayList<DietChart> getDietList() {
		ArrayList<DietChart> mDietList = new ArrayList<DietChart>();
		open();
		Cursor cursor = db.query(DBHelper.DIET_CHART_TABLE_NAME, null, null,
				null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_DIET_ID));
				String mDietType = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DIET_TYPE));
				String mDietTime = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DIET_TIME));
				String mDietFeast = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DIET_FEAST));
				String mDietNote = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DIET_NOTE));

				DietChart mDiet = new DietChart(id, mDietType, mDietTime,
						mDietFeast, mDietNote);
				mDietList.add(mDiet);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return profile list
		return mDietList;
	}

	// Getting All Profile list
	public DietChart getDetail(int id) {
		DietChart mDietDetail;
		open();

		String selectQuery = "SELECT  * FROM " + DBHelper.DIET_CHART_TABLE_NAME
				+ " WHERE " + DBHelper.KEY_DIET_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String mDietType = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DIET_TYPE));
		String mDietTime = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DIET_TIME));
		String mDietFeast = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DIET_FEAST));
		String mDietNote = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DIET_NOTE));

		mDietDetail = new DietChart(id, mDietType, mDietTime, mDietFeast,
				mDietNote);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return place detail
		return mDietDetail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.DIET_CHART_TABLE_NAME, new String[] {
				DBHelper.KEY_DIET_ID, DBHelper.KEY_DIET_TYPE,
				DBHelper.KEY_DIET_TIME, DBHelper.KEY_DIET_FEAST,
				DBHelper.KEY_DIET_NOTE }, null, null, null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
