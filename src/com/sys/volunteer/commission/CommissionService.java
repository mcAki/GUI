package com.sys.volunteer.commission;

import com.sys.volunteer.dao.IDao;

public interface CommissionService extends IDao {

	public double addCommission(String userId, Double dealMoney, int flag);

}
