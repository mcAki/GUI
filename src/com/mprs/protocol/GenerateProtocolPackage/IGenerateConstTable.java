package com.mprs.protocol.GenerateProtocolPackage;

import java.util.Map;

import com.mprs.protocol.ProtocolContainer;

/**
 * 可生成代码接口
 * @author Seraph.Yang
 *
 */
public interface IGenerateConstTable {
	
	public String getGenerateEncode();

	/**
	 * 设置编码
	 * @param generateEncode
	 */
	public void setGenerateEncode(String generateEncode);
	
	/**
	 * 生成常量表
	 * @param dir
	 * @throws Exception 
	 */
	public void GenerateConstTable(Map<Integer, ProtocolContainer> map, ProtocolSetting ps, String dir) throws Exception;
}
