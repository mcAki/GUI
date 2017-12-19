package com.sys.volunteer.feiyue;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Feiyue;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.SupplyInterface;

public interface FeiyueService extends IDao{

	public Feiyue initFeiyue(Mainorder mainorder,SupplyInterface supplyInterface);

	public Feiyue findFeiyueByOrderId(String orderId);

	public Feiyue updateFeiyue(Mainorder mainorder, Feiyue reFy);

	public int reverseOrder(Feiyue feiyue);

	public int updateDealingOrders(Mainorder mainorder) throws Exception;
}
