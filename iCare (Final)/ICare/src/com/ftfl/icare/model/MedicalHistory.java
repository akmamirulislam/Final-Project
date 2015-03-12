package com.ftfl.icare.model;

public class MedicalHistory {

	Integer mId;
	String mPrescription;
	String mPurpose;
	String mDoctorName;
	String mAppointmentDate;

	// Constructor Wirhout parameter
	public MedicalHistory() {
	}

	// Constructor without id
	public MedicalHistory(String ePrescription, String ePurpose,
			String eDoctorName, String eAppointmentDate) {
		this.mPrescription = ePrescription;
		this.mPurpose = ePurpose;
		this.mDoctorName = eDoctorName;
		this.mAppointmentDate = eAppointmentDate;
	}

	// Constructor with all parameter
	public MedicalHistory(Integer eId, String ePrescription, String ePurpose,
			String eDoctorName, String eAppointmentDate) {
		this.mId = eId;
		this.mPrescription = ePrescription;
		this.mPurpose = ePurpose;
		this.mDoctorName = eDoctorName;
		this.mAppointmentDate = eAppointmentDate;
	}

	// Getters and Setters
	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer eId) {
		this.mId = eId;
	}

	public String getmPrescription() {
		return mPrescription;
	}

	public void setmPrescription(String ePrescription) {
		this.mPrescription = ePrescription;
	}

	public String getmPurpose() {
		return mPurpose;
	}

	public void setmPurpose(String ePurpose) {
		this.mPurpose = ePurpose;
	}

	public String getmDoctorName() {
		return mDoctorName;
	}

	public void setmDoctorName(String eDoctorName) {
		this.mDoctorName = eDoctorName;
	}

	public String getmAppointmentDate() {
		return mAppointmentDate;
	}

	public void setmAppointmentDate(String eAppointmentDate) {
		this.mAppointmentDate = eAppointmentDate;
	}

}
