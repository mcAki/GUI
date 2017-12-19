package com.sys.volunteer.epaybank;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.EpayBankpayRequest;
import com.sys.volunteer.pojo.EpayBankpayResponse;
import com.sys.volunteer.pojo.Users;

public interface EpayBankService extends IDao {

	public EpayBankpayRequest initEpayBankpayRequest(String payCardNo, double money,
			Users user,String summary) throws Exception;

	public EpayBankpayResponse initEpayBankpayResponse(EpayBankpayRequest request);

	public int refreshEpayBank(EpayBankpayResponse response);

}
