package com.ftfl.icare.model;

public class UserProfile {

	Integer mId = 0;
	String mName = null;
	String mWeight = null;
	String mHeight = null;
	String mBirthDate = null;
	String mGender = null;
	String mEmergencyPhone = null;

	// Constructor Without Parameter
	public UserProfile() {
	}

	// Constructor Without ID
	public UserProfile(String eName, String eWeight, String eHeight,
			String eBirthDate, String eGender, String eEmergencyPhone) {

		this.mName = eName;
		this.mWeight = eWeight;
		this.mHeight = eHeight;
		this.mBirthDate = eBirthDate;
		this.mGender = eGender;
		this.mEmergencyPhone = eEmergencyPhone;
	}

	// Constructor With All Parameter
	public UserProfile(Integer eId, String eName, String eWeight,
			String eHeight, String eBirthDate, String eGender,
			String eEmergencyPhone) {
		this.mId = eId;
		this.mName = eName;
		this.mWeight = eWeight;
		this.mHeight = eHeight;
		this.mBirthDate = eBirthDate;
		this.mGender = eGender;
		this.mEmergencyPhone = eEmergencyPhone;
	}

	// All Setter and Getters

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

	public String getmWeight() {
		return mWeight;
	}

	public void setmWeight(String eWeight) {
		this.mWeight = eWeight;
	}

	public String getmHeight() {
		return mHeight;
	}

	public void setmHeight(String eHeight) {
		this.mHeight = eHeight;
	}

	public String getmBirthDate() {
		return mBirthDate;
	}

	public void setmBirthDate(String eBirthDate) {
		this.mBirthDate = eBirthDate;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String eGender) {
		this.mGender = eGender;
	}

	public String getmEmergencyPhone() {
		return mEmergencyPhone;
	}

	public void setmEmergencyPhone(String eEmergencyPhone) {
		this.mEmergencyPhone = eEmergencyPhone;
	}

	@Override
	public String toString() {
		return "UserProfile [mId=" + mId + ", mName=" + mName + ", mWeight="
				+ mWeight + ", mHeight=" + mHeight + ", mBirthDate="
				+ mBirthDate + ", mGender=" + mGender + ", mEmergencyPhone="
				+ mEmergencyPhone + "]";
	}

}
