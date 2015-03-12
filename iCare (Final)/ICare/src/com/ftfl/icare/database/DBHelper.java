package com.ftfl.icare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	// //////////// Database Creation Area //////////////////////

	// All Static variables
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "iCare";

	// Create Database
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// //////////// User Profile Table Area //////////////////////

	// User Profile Table Name
	public static final String USER_PROFILE_TABLE_NAME = "userProfile";

	// User Profile Table Columns names
	public static final String KEY_USER_ID = "userId";
	public static final String KEY_USER_NAME = "userName";
	public static final String KEY_USER_WEIGHT = "userWeightt";
	public static final String KEY_USER_HEIGHT = "userHeight";
	public static final String KEY_USER_BIRTH_DATE = "userBirthDate";
	public static final String KEY_USER_GENDER = "userGender";
	public static final String KEY_USER_PHONE = "userPhone";

	// User Profile Table Information
	public String CREATE_USER_PROFILE_TABLE = "create table "
			+ USER_PROFILE_TABLE_NAME + "(" + KEY_USER_ID
			+ " integer primary key autoincrement, " + KEY_USER_NAME
			+ " text not null, " + KEY_USER_WEIGHT + " text not null, "
			+ KEY_USER_HEIGHT + " text not null, " + KEY_USER_BIRTH_DATE
			+ " text not null, " + KEY_USER_GENDER + " text not null, "
			+ KEY_USER_PHONE + " text not null);";

	// //////////// Doctor Profile Table Area //////////////////////

	// Doctor Profile Table Name
	public static final String DOCTOR_PROFILE_TABLE_NAME = "doctorProfile";

	// Doctor Profile Table Columns names
	public static final String KEY_DOCTOR_ID = "doctorId";
	public static final String KEY_DOCTOR_NAME = "doctorName";
	public static final String KEY_DOCTOR_DESIGNATION = "doctorDesignation";
	public static final String KEY_DOCTOR_PHONE = "doctorPhone";
	public static final String KEY_DOCTOR_EMAIL = "doctorEmail";

	// Doctor Profile Table Information
	public String CREATE_DOCTOR_PROFILE_TABLE = "create table "
			+ DOCTOR_PROFILE_TABLE_NAME + "(" + KEY_DOCTOR_ID
			+ " integer primary key autoincrement, " + KEY_DOCTOR_NAME
			+ " text not null, " + KEY_DOCTOR_DESIGNATION + " text not null, "
			+ KEY_DOCTOR_PHONE + " text not null, " + KEY_DOCTOR_EMAIL
			+ " text not null);";

	// //////////// Diet Chart Table Area //////////////////////

	// Diet Chart Table Name
	public static final String DIET_CHART_TABLE_NAME = "userDietChart";

	// Diet Chart Table Columns names
	public static final String KEY_DIET_ID = "dietId";
	public static final String KEY_DIET_TYPE = "dietType";
	public static final String KEY_DIET_TIME = "dietTime";
	public static final String KEY_DIET_FEAST = "dietFeast";
	public static final String KEY_DIET_NOTE = "dietNote";

	// Diet Chart Table Information
	public String CREATE_DIET_CHART_TABLE = "create table "
			+ DIET_CHART_TABLE_NAME + "(" + KEY_DIET_ID
			+ " integer primary key autoincrement, " + KEY_DIET_TYPE
			+ " text not null, " + KEY_DIET_TIME + " text not null, "
			+ KEY_DIET_FEAST + " text not null, " + KEY_DIET_NOTE
			+ " text not null);";

	// //////////// Vaccination Chart Table Area //////////////////////

	// Vaccination Chart Table Name
	public static final String VACCINATION_TABLE_NAME = "vaccinationChart";

	// VaccinationTable Columns names
	public static final String KEY_VACCINE_ID = "vaccineId";
	public static final String KEY_VACCINE_NAME = "vaccineName";
	public static final String KEY_VACCINE_TIME = "vaccineTime";
	public static final String KEY_VACCINE_DATE = "vaccineDate";
	public static final String KEY_VACCINE_NOTE = "vaccineNote";

	// Vaccination Chart Table Information
	public String CREATE_VACCINATION_TABLE = "create table "
			+ VACCINATION_TABLE_NAME + "(" + KEY_VACCINE_ID
			+ " integer primary key autoincrement, " + KEY_VACCINE_NAME
			+ " text not null, " + KEY_VACCINE_TIME + " text not null, "
			+ KEY_VACCINE_DATE + " text not null, " + KEY_VACCINE_NOTE
			+ " text not null);";

	// //////////// Medical History Table Area //////////////////////

	// Medical History Table Name
	public static final String MEDICAL_HISTORY_TABLE_NAME = "medicalHistory";

	// VaccinationTable Columns names
	public static final String KEY_MEDICAL_ID = "medicalId";
	public static final String KEY_MEDICAL_PRESCRIPTION = "medicalPrescription";
	public static final String KEY_MEDICAL_PURPOSE = "medicalPurpose";
	public static final String KEY_MEDICAL_DOCTOR_NAME = "medicalDoctorName";
	public static final String KEY_MEDICAL_APPOINTMENT_DATE = "medicalAppointmentDate";

	// Medical History Table Information
	public String CREATE_MEDICAL_HISTORY_TABLE = "create table "
			+ MEDICAL_HISTORY_TABLE_NAME + "(" + KEY_MEDICAL_ID
			+ " integer primary key autoincrement, " + KEY_MEDICAL_PRESCRIPTION
			+ " text not null, " + KEY_MEDICAL_PURPOSE + " text not null, "
			+ KEY_MEDICAL_DOCTOR_NAME + " text not null, "
			+ KEY_MEDICAL_APPOINTMENT_DATE + " text not null);";

	// //////////// Database Table Creation Statement //////////////////////
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER_PROFILE_TABLE); // User Profile
		db.execSQL(CREATE_DOCTOR_PROFILE_TABLE); // Doctor Profile
		db.execSQL(CREATE_DIET_CHART_TABLE); // Diet Chart
		db.execSQL(CREATE_VACCINATION_TABLE); // Vaccination Chart
		db.execSQL(CREATE_MEDICAL_HISTORY_TABLE); // Medical History
	}

	// //////////// Database Table Updating Statement //////////////////////
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + CREATE_USER_PROFILE_TABLE); // UserProfile
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_DOCTOR_PROFILE_TABLE); // DoctorProfile
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_DIET_CHART_TABLE); // DietChart
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_VACCINATION_TABLE); // VaccinationChart
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_MEDICAL_HISTORY_TABLE); // MedicalHistory
		onCreate(db);
	}

}