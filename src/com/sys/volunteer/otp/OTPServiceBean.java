package com.sys.volunteer.otp;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.OTP;
import com.sys.volunteer.pojo.Users;

import ft.otp.verify.OTPVerify;

@Service
@Transactional
public class OTPServiceBean extends CommonDao implements
		OTPService {

	@Override
	public OTP findById(long id) {
		OTP otp = (OTP)findById(OTP.class, id);
		return otp;
	}

	@Override
	public void updateOTPDrift(OTP otp,int drift, long success) {
		otp.setCurrDrift(drift);
		otp.setCurrSuccess(success);
		save(otp);
	}

	@Override
	public boolean verifyOTP(Users users,String authKey) {
		OTP otp=this.findById(users.getOtp().getKeyId());
		if (otp==null) {
			return false;
		}
		Map hashMap = OTPVerify.ET_CheckPwdz201(
				otp.getAuthKey(),						//令牌密钥
				System.currentTimeMillis()/1000,		//调用本接口计算机的当前时间
				0,										//给0
				60,										//给60，因为每60秒变更新的动态口令
				otp.getCurrDrift(), 					//漂移值，用于调整硬件与服务器的时间偏差，见手册说明
				20,										//认证窗口，见手册说明 
				otp.getCurrSuccess(),					//成功值，用于调整硬件与服务器的时间偏差，见手册说明
				authKey);								//要认证的动态口令OTP
		long nReturn = (Long)hashMap.get("returnCode");
		
		System.out.println("nReturn:"+nReturn);
		
		if(nReturn == OTPVerify.OTP_SUCCESS)
		{
			System.out.println("check success");
			System.out.println("currentSucc = " + hashMap.get("currentUTCEpoch"));
			System.out.println("currentDrift = " + hashMap.get("currentDrift"));
			
			int iDrift = ((Long)hashMap.get("currentDrift")).intValue();
			long lSucc = ((Long)hashMap.get("currentUTCEpoch")).longValue();
			
			this.updateOTPDrift(otp, iDrift, lSucc);
			
			return true;
		}
		else
		{
			return false;
		}
	}

}
