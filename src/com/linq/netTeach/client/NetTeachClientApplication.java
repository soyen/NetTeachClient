package com.linq.netTeach.client;

import android.app.Application;

public class NetTeachClientApplication extends Application {

	private int userType;

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	} 
	
}
