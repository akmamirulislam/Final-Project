package com.ftfl.icare.model;

public class Vaccination {

	Integer mId;
	String mName;
	String mTime;
	String mDate;
	String mNote;

	// Constructor Wirhout parameter
	public Vaccination() {
	}

	// Constructor without id
	public Vaccination(String eName, String eTime, String eDate, String eNote) {
		this.mName = eName;
		this.mTime = eTime;
		this.mDate = eDate;
		this.mNote = eNote;
	}

	// Constructor with all parameter
	public Vaccination(Integer eId, String eName, String eTime, String eDate,
			String eNote) {
		this.mId = eId;
		this.mName = eName;
		this.mTime = eTime;
		this.mDate = eDate;
		this.mNote = eNote;
	}

	// Getters and Setters
	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer eId) {
		this.mId = eId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String eName) {
		this.mName = eName;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String eTime) {
		this.mTime = eTime;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String eDate) {
		this.mDate = eDate;
	}

	public String getmNote() {
		return mNote;
	}

	public void setmNote(String eNote) {
		this.mNote = eNote;
	}

	@Override
	public String toString() {
		return "Vaccination [mId=" + mId + ", mName=" + mName + ", mTime="
				+ mTime + ", mDate=" + mDate + ", mNote=" + mNote + "]";
	}

}
