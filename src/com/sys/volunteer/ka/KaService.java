package com.sys.volunteer.ka;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.GameRechargeRequest;
import com.sys.volunteer.pojo.GameRechargeResponse;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Query91KAOrder;
import com.sys.volunteer.pojo.SupplyInterface;

public interface KaService extends IDao{

	public GameRechargeRequest addGameRechargeRequest(GameRechargeRequest request,Mainorder mainorder,SupplyInterface supplyInterface,
			String at,String gameId,String autoGameId,String atVerify,String clientIp);
	
	public GameRechargeRequest findRequestByCpOrderNo(String cpOrderNo);
	
	public int refreshGameRechargeOrder(Mainorder mainorder);

	public Query91KAOrder findqQuery91kaOrderByCpOrderNo(String cpOrderNo);

	public GameRechargeRequest findRequestByOrderId(String orderId);

	public Query91KAOrder findqQuery91kaOrderByOrderId(String orderId);

	public Query91KAOrder initQueryOrder(GameRechargeRequest request);

	public GameRechargeResponse findqGameRechargeResponseByOrderId(String orderId);
}
