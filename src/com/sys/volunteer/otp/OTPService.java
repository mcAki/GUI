package com.sys.volunteer.otp;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.OTP;
import com.sys.volunteer.pojo.Users;

public interface OTPService extends IDao {

	public OTP findById(long id);
	
	public void updateOTPDrift(OTP otp,int drift,long success);
	
	public boolean verifyOTP(Users users,String authKey);
	
}
