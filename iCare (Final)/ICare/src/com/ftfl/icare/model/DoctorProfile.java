package com.ftfl.icare.model;

public class DoctorProfile {

	Integer mId;
	String mName;
	String mDesignation;
	String mPhone;
	String mEmail;

	public DoctorProfile() {
	}

	public DoctorProfile(String eName, String eDesignation, String ePhone,
			String eEmail) {
		this.mName = eName;
		this.mDesignation = eDesignation;
		this.mPhone = ePhone;
		this.mEmail = eEmail;
	}

	public DoctorProfile(Integer eId, String eName, String eDesignation,
			String ePhone, String eEmail) {
		this.mId = eId;
		this.mName = eName;
		this.mDesignation = eDesignation;
		this.mPhone = ePhone;
		this.mEmail = eEmail;
	}

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

	public String getmDesignation() {
		return mDesignation;
	}

	public void setmDesignation(String eDesignation) {
		this.mDesignation = eDesignation;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String ePhone) {
		this.mPhone = ePhone;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String eEmail) {
		this.mEmail = eEmail;
	}

	@Override
	public String toString() {
		return "DoctorProfile [mId=" + mId + ", mName=" + mName
				+ ", mDesignation=" + mDesignation + ", mPhone=" + mPhone
				+ ", mEmail=" + mEmail + "]";
	}

}
