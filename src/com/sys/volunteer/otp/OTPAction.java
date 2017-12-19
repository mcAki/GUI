package com.sys.volunteer.otp;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.pojo.OTP;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.users.UserService;

import ft.otp.verify.OTPVerify;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class OTPAction extends BaseAction{

	@Resource
	OTPService otpService;
	@Resource
	UserService userService;
	
	private String userId;
	
	private String authKey;
	
	public String testPage() throws Exception{
		return "do";
	}
	
	public String testOTP() throws Exception{
		if (authKey==null||authKey.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写验证码", "", "", 0);
		}
		Users users=userService.findUserById(userId);
		if (users.getOtp()==null) {
			return ShowMessage(MSG_TYPE_NORMAL, "该用户没有配置加密key", "", "", 0);
		}
		OTP otp=otpService.findById(users.getOtp().getKeyId());
		if (otp==null) {
			return ShowMessage(MSG_TYPE_NORMAL, "没有该加密key", "", "", 0);
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
		
		if(nReturn == OTPVerify.OTP_SUCCESS)
		{
			System.out.println("check success");
			System.out.println("currentSucc = " + hashMap.get("currentUTCEpoch"));
			System.out.println("currentDrift = " + hashMap.get("currentDrift"));
			
			int iDrift = ((Long)hashMap.get("currentDrift")).intValue();
			long lSucc = ((Long)hashMap.get("currentUTCEpoch")).longValue();
			
			otpService.updateOTPDrift(otp, iDrift, lSucc);
			
			return ShowMessage(MSG_TYPE_NORMAL, "验证成功!", "", "", 0);
		}
		else
		{
			return ShowMessage(MSG_TYPE_STOP, "验证失败!", "", "", 0);
		}
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
