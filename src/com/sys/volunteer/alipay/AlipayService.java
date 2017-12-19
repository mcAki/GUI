/**
 * 
 */
package com.sys.volunteer.alipay;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Alipay;
import com.sys.volunteer.pojo.AlipayBack;

/**
 * @author hanwen
 *
 * @version 创建时间：2013-1-9  下午05:48:35
 */
public interface AlipayService extends IDao {

	public Alipay addAlipay(Alipay alipay);

	public AlipayBack addAlipayBack(AlipayBack alipayBack);
	
	public Alipay updateAlipay(Alipay alipay);
	
	public List<Alipay> findAll();
	
	public Alipay findByTradeNo(String tradeNo);
	
	public AlipayBack findByTradeNoBack(String tradeNo);
	
	public List<Alipay> findByUserId(String userId);

	public List<AlipayBack> findByUserIdBack(String userId);
}
