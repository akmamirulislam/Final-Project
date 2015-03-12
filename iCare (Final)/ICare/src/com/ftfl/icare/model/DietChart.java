package com.ftfl.icare.model;

public class DietChart {

	Integer mDietId;
	String mDietType;
	String mDietTime;
	String mDietFeast;
	String mDietNote;

	public DietChart() {
	}

	public DietChart(String eDietType, String eDietTime, String eDietFeast,
			String eDietNote) {
		this.mDietType = eDietType;
		this.mDietTime = eDietTime;
		this.mDietFeast = eDietFeast;
		this.mDietNote = eDietNote;
	}

	public DietChart(Integer eDietId, String eDietType, String eDietTime,
			String eDietFeast, String eDietNote) {
		this.mDietId = eDietId;
		this.mDietType = eDietType;
		this.mDietTime = eDietTime;
		this.mDietFeast = eDietFeast;
		this.mDietNote = eDietNote;
	}

	public Integer getmDietId() {
		return mDietId;
	}

	public void setmDietId(Integer eDietId) {
		this.mDietId = eDietId;
	}

	public String getmDietType() {
		return mDietType;
	}

	public void setmDietType(String eDietType) {
		this.mDietType = eDietType;
	}

	public String getmDietTime() {
		return mDietTime;
	}

	public void setmDietTime(String eDietTime) {
		this.mDietTime = eDietTime;
	}

	public String getmDietFeast() {
		return mDietFeast;
	}

	public void setmDietFeast(String eDietFeast) {
		this.mDietFeast = eDietFeast;
	}

	public String getmDietNote() {
		return mDietNote;
	}

	public void setmDietNote(String eDietNote) {
		this.mDietNote = eDietNote;
	}

}
